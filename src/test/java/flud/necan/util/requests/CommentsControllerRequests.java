package flud.necan.util.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

public class CommentsControllerRequests {
    private final MockMvc mvc;
    private final ObjectMapper objectMapper;


    public CommentsControllerRequests(MockMvc mvc) {
        this.objectMapper = new ObjectMapper();
        this.mvc = mvc;
    }
}
