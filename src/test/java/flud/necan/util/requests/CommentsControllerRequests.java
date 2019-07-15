package flud.necan.util.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class CommentsControllerRequests {
    private final MockMvc mvc;
    private final ObjectMapper objectMapper;


    public CommentsControllerRequests(MockMvc mvc) {
        this.objectMapper = new ObjectMapper();
        this.mvc = mvc;
    }

    public ResultActions getComment(String ideaId, String commentId) throws Exception {
        return mvc.perform(get("/api/v1/ideas/" + ideaId + "/comments/" + commentId));
    }

    public ResultActions getCommentsForIdea(String ideaId) throws Exception {
        return mvc.perform(get("/api/v1/ideas/" + ideaId + "/comments"));
    }
    public ResultActions postCommentForIdea(String ideaId, String content) throws Exception {
        return mvc.perform(post("/api/v1/ideas/" +ideaId+ "/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        " \"content\": " + objectMapper.writeValueAsString(content) + "\n" +
                        "}"));
    }
}
