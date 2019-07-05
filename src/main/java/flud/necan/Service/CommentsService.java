package flud.necan.Service;

import flud.necan.Dtos.CommentInDto;
import flud.necan.Models.CommentDocument;
import flud.necan.Repository.comments.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public CommentDocument addComment(String ideaId, CommentInDto commentInDto) {
        return commentsRepository.save(commentInDto.toDocument(ideaId));
    }

    public CommentDocument getCommentById(String ideaId, String commentId) {
        return commentsRepository.findByIdeaIdAndCommentId(ideaId, commentId);
    }

    public Page<CommentDocument> getCommentsForIdea(String ideaId, Pageable pageable) {
        return commentsRepository.findByIdeaId(ideaId, pageable);
    }
}
