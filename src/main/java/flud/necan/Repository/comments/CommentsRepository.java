package flud.necan.Repository.comments;

import flud.necan.Models.CommentDocument;
import flud.necan.Models.IdeaDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsRepository extends MongoRepository<CommentDocument, String>, CommentsRepositoryCustom {
    Page<CommentDocument> findByIdeaId(String ideaId, Pageable pageable);
    CommentDocument findByIdeaIdAndCommentId(String ideaId, String commentId);
}
