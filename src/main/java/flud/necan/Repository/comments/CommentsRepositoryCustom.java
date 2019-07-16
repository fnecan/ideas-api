package flud.necan.Repository.comments;

import com.mongodb.client.result.UpdateResult;

public interface CommentsRepositoryCustom {
    UpdateResult upvoteComment(String ideaId, String commentId);
}
