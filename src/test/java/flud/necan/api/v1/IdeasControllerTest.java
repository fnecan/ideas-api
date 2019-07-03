package flud.necan.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import flud.necan.util.TestFixtures;
import flud.necan.util.requests.IdeasControllerRequests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class IdeasControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestFixtures testFixtures;

    private ObjectMapper objectMapper;
    private IdeasControllerRequests ideasControllerRequests;

    @BeforeEach
    public void init() {
        this.ideasControllerRequests = new IdeasControllerRequests(this.mvc);
        objectMapper = new ObjectMapper();
        testFixtures.cleanCollections();
    }

    @Test
    public void it_should_return_null_if_not_found() throws Exception {
        ideasControllerRequests.getIdea("test")
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").doesNotHaveJsonPath());
    }

    @Test
    public void it_should_return_details_on_idea() throws Exception {
        ResultActions res = ideasControllerRequests.postIdea()
                .andExpect(status().is2xxSuccessful());

        String responseString = res.andReturn().getResponse().getContentAsString();
        String ideaId = objectMapper.readTree(responseString).get("id").asText();

        ideasControllerRequests.getIdea(ideaId)
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", equalTo(ideaId)));
    }

    @Test
    public void it_should_succesfully_partially_update_idea() throws Exception {
        ResultActions res = ideasControllerRequests.postIdea()
                .andExpect(status().is2xxSuccessful());

        String responseString = res.andReturn().getResponse().getContentAsString();
        String ideaId = objectMapper.readTree(responseString).get("id").asText();

        ideasControllerRequests.getIdea(ideaId).andExpect(jsonPath("$.name", equalTo("Test note")));
        ideasControllerRequests.getIdea(ideaId).andExpect(jsonPath("$.description", equalTo("Test Description")));

        ideasControllerRequests.updateIdea(ideaId, "New name", null, null, null)
                .andExpect(status().is2xxSuccessful());

        ideasControllerRequests.getIdea(ideaId).andExpect(jsonPath("$.name", equalTo("New name")));
        ideasControllerRequests.getIdea(ideaId).andExpect(jsonPath("$.description", equalTo("Test Description")));
    }

    @Test
    public void it_should_succesfully_update_idea() throws Exception {

        ResultActions res = ideasControllerRequests.postIdea()
                .andExpect(status().is2xxSuccessful());

        String responseString = res.andReturn().getResponse().getContentAsString();
        String ideaId = objectMapper.readTree(responseString).get("id").asText();
        ideasControllerRequests.updateIdea(ideaId, "new name", "new description", "http://example.com", new ArrayList<>(Arrays.asList("tagA","tagB")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", equalTo("new name")));
    }

    @Test
    public void it_should_return_400_if_no_name_was_specified() throws Exception{
        ideasControllerRequests.postIdeaWithCustomPayload(null, "Test description", "http://example.com", new ArrayList<>(Arrays.asList("tag", "tag2", "tag3")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void it_should_correctly_add_a_new_idea() throws Exception {
        ideasControllerRequests.postIdea()
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_return_200_when_listing_ideas() throws Exception {
        ideasControllerRequests.getIdeasList()
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_correctly_return_paginated_list_of_ideas() throws Exception {
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
