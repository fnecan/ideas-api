package flud.necan.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import flud.necan.util.TestFixtures;
import flud.necan.util.requests.CommentsControllerRequests;
import flud.necan.util.requests.IdeasControllerRequests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentsControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestFixtures testFixtures;

    private ObjectMapper objectMapper;
    private IdeasControllerRequests ideasControllerRequests;
    private CommentsControllerRequests commentsControllerRequests;


    @BeforeEach
    public void init() {
        this.ideasControllerRequests = new IdeasControllerRequests(this.mvc);
        this.commentsControllerRequests = new CommentsControllerRequests(this.mvc);
        objectMapper = new ObjectMapper();
        testFixtures.cleanCollections();
    }

    @Test
    public void it_should_return_a_list_of_comments_for_idea() throws Exception {
        ResultActions res = commentsControllerRequests.postCommentForIdea()
    }

}
