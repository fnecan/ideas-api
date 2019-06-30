package flud.necan.util.requests;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class IdeasControllerRequests {

    private final MockMvc mvc;

    public IdeasControllerRequests(MockMvc mvc) {
        this.mvc = mvc;
    }

    public ResultActions getIdeasList() throws Exception {
        return mvc.perform(get("/api/v1/ideas"));
    }

    public ResultActions postIdea() throws Exception {
        ArrayList<String> tags = new ArrayList<>(Arrays.asList("tag", "tag2", "tag3"));
        return postIdeaWithCustomPayload("Test note", "Test Description", "http://example.com", tags);
    }

    public ResultActions postIdeaWithCustomPayload(String name, String description, String imageUrl, List<String> tags) throws Exception {
        return mvc.perform(post("/api/v1/ideas")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content("{\n" +
                    " \"name\": " + name + ",\n" +
                    " \"description\": " + description + ",\n" +
                    " \"imageUrl\": " + imageUrl + ",\n" +
                    " \"tags\": " + tags.toString() + "\n" +
                    "}")
            );
    }
}
