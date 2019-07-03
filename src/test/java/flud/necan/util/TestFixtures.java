package flud.necan.util;

import flud.necan.Models.IdeaDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class TestFixtures {
    private final MongoTemplate mongoTemplate;

    public TestFixtures(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void cleanCollections() {
        mongoTemplate.remove(new Query(), IdeaDocument.class);
    }
}
