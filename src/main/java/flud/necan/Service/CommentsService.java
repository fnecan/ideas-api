package flud.necan.Service;

import flud.necan.Dtos.CommentInDto;
import flud.necan.Exceptions.MissingIdeaDocumentException;
import flud.necan.Models.CommentDocument;
import flud.necan.Repository.comments.CommentsRepository;
import flud.necan.Repository.ideas.IdeasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private IdeasRepository ideasRepository;
    private CommentsRepository commentsRepository;

    @Autowired
    public CommentsService(IdeasRepository ideasRepository, CommentsRepository commentsRepository) {
        this.ideasRepository = ideasRepository;
        this.commentsRepository = commentsRepository;
    }

    public CommentDocument addComment(String ideaId, CommentInDto commentInDto) throws RuntimeException {
        if(!ideasRepository.findById(ideaId).isPresent()) throw new MissingIdeaDocumentException("Unable to find idea " + ideaId);
        return commentsRepository.save(commentInDto.toDocument(ideaId));
    }

    public CommentDocument getCommentById(String ideaId, String commentId) {
        return commentsRepository.findByIdeaIdAndId(ideaId, commentId);
    }

    public Page<CommentDocument> getCommentsForIdea(String ideaId, Pageable pageable) {
        return commentsRepository.findByIdeaId(ideaId, pageable);
    }
}
