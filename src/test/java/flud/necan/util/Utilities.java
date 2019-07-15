package flud.necan.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;

public class Utilities {
    public static String ideaIdFromRequest(ResultActions postIdeaResultActions) throws IOException {
         ObjectMapper objectMapper = new ObjectMapper();
         JsonNode mappedResponse = objectMapper.readTree(postIdeaResultActions.andReturn().getResponse().getContentAsString());
         return mappedResponse.get("id").asText();
    }
}
