package flud.necan.Repository;

import com.mongodb.client.result.UpdateResult;
import flud.necan.Models.IdeaDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class IdeasRepositoryCustomImpl implements IdeasRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public IdeasRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Boolean updateIdea(String ideaId, Update update) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(ideaId));

        System.out.println("Query");
        System.out.println(query.toString());
        System.out.println("Update");
        System.out.println(update.toString());
        UpdateResult res = mongoTemplate.updateFirst(query, update, IdeaDocument.class);

        return res.isModifiedCountAvailable();
    }
}
