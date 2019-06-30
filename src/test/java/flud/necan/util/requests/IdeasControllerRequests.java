package flud.necan.util.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class IdeasControllerRequests {

    private final MockMvc mvc;
    private final ObjectMapper objectMapper;


    public IdeasControllerRequests(MockMvc mvc) {
        this.objectMapper = new ObjectMapper();
        this.mvc = mvc;
    }

    public ResultActions getIdeasList() throws Exception {
        return mvc.perform(get("/api/v1/ideas"));
    }

    public ResultActions updateIdea(String ideaId, String name, String description, String imageUrl, List<String> tags) throws Exception {
        return mvc.perform(patch("/api/v1/ideas/" + ideaId)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content("{\n" +
                    " \"name\": " + objectMapper.writeValueAsString(name)+ ",\n" +
                    " \"description\": " + objectMapper.writeValueAsString(description) + ",\n" +
                    " \"imageUrl\": " + objectMapper.writeValueAsString(imageUrl) + ",\n" +
                    " \"tags\": " + objectMapper.writeValueAsString(tags) + "\n" +
                    "}"));
    }

    public ResultActions postIdea() throws Exception {
        ArrayList<String> tags = new ArrayList<>(Arrays.asList("tag", "tag2", "tag3"));
        return postIdeaWithCustomPayload("Test note", "Test Description", "http://example.com", tags);
    }

    public ResultActions getIdea(String ideaId) throws Exception {
        return mvc.perform(get("/api/v1/ideas/" + ideaId));
    }

    public ResultActions postIdeaWithCustomPayload(String name, String description, String imageUrl, List<String> tags) throws Exception {
        return mvc.perform(post("/api/v1/ideas")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content("{\n" +
                    " \"name\": " + objectMapper.writeValueAsString(name)+ ",\n" +
                    " \"description\": " + objectMapper.writeValueAsString(description) + ",\n" +
                    " \"imageUrl\": " + objectMapper.writeValueAsString(imageUrl) + ",\n" +
                    " \"tags\": " + objectMapper.writeValueAsString(tags) + "\n" +
                    "}"
            ));
    }
}
