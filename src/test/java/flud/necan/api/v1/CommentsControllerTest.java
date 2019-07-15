package flud.necan.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import flud.necan.util.TestFixtures;
import flud.necan.util.Utilities;
import flud.necan.util.requests.CommentsControllerRequests;
import flud.necan.util.requests.IdeasControllerRequests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        String ideaId = Utilities.ideaIdFromRequest(ideasControllerRequests.postIdea());
        String ideaIdTwo = Utilities.ideaIdFromRequest(ideasControllerRequests.postIdea());
        commentsControllerRequests.postCommentForIdea(ideaId, "comment1")
                .andExpect(status().isOk());
        commentsControllerRequests.postCommentForIdea(ideaId, "comment2")
                .andExpect(status().isOk());
        commentsControllerRequests.postCommentForIdea(ideaIdTwo, "comment3")
                .andExpect(status().isOk());

        commentsControllerRequests.getCommentsForIdea(ideaId)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", equalTo(2)));
    }

    @Test
    public void when_posting_comment_it_should_return_400_when_idea_is_not_found() throws Exception {
        String ideaId = "testId";
        commentsControllerRequests.postCommentForIdea(ideaId, "Content")
                .andExpect(status().isBadRequest());
    }
    @Test
    public void it_should_return_400_when_no_content_is_passed() throws Exception {
        String ideaId = Utilities.ideaIdFromRequest(ideasControllerRequests.postIdea());
        commentsControllerRequests.postCommentForIdea(ideaId, "")
                .andExpect(status().isBadRequest());
        commentsControllerRequests.postCommentForIdea(ideaId, null)
                .andExpect(status().isBadRequest());
        commentsControllerRequests.postCommentForIdea(ideaId, "Test comment")
                .andExpect(status().isOk());
    }
}
