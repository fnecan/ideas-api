package flud.necan.Repository.comments;

import com.mongodb.client.result.UpdateResult;
import flud.necan.Models.CommentDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CommentsRepositoryCustomImpl implements CommentsRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CommentsRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public UpdateResult upvoteComment(String ideaId, String commentId) {
        Query query = new Query(Criteria.where("id").is(commentId).and("ideaId").is(ideaId));
        Update update = new Update();
        update.inc("score");
        return mongoTemplate.updateFirst(query, update, CommentDocument.class);
    }
}
