package flud.necan.api.v1;

import flud.necan.util.TestFixtures;
import flud.necan.util.requests.IdeasControllerRequests;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IdeasControllerTest {

    // TODO: Remove base API Test, due to AutoConfigureMock

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestFixtures testFixtures;

    private IdeasControllerRequests ideasControllerRequests;

    @BeforeEach // TODO: Enable beforeEach in configuration
    void setUp() {
        testFixtures.cleanCollections();
    }

    @Test
    public void it_should_return_400_if_no_name_was_specified() throws Exception{
        this.ideasControllerRequests = new IdeasControllerRequests(this.mvc);

        ideasControllerRequests.postIdeaWithCustomPayload(null, "Test description", "http://example.com", new ArrayList<>(Arrays.asList("tag", "tag2", "tag3")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void it_should_correctly_add_a_new_idea() throws Exception {
        this.ideasControllerRequests = new IdeasControllerRequests(this.mvc);

        ideasControllerRequests.postIdea()
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_return_200_when_listing_ideas() throws Exception {
        this.ideasControllerRequests = new IdeasControllerRequests(this.mvc);

        ideasControllerRequests.getIdeasList()
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_correctly_return_paginated_list_of_ideas() throws Exception {
        this.ideasControllerRequests = new IdeasControllerRequests(this.mvc);

        ideasControllerRequests.postIdea()
                .andExpect(status().isOk());
        ideasControllerRequests.postIdea()
                .andExpect(status().isOk());
        ideasControllerRequests.postIdea()
                .andExpect(status().isOk());

        ideasControllerRequests.getIdeasList()
                .andExpect(status().isOk())
                .andDo(print());
    }
}
