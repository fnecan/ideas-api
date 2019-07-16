package flud.necan.api.v1;

import flud.necan.Dtos.CommentInDto;
import flud.necan.Models.CommentDocument;
import flud.necan.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/ideas")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/{ideaId}/comments")
    public Page<CommentDocument> getCommentsForIdea(@PathVariable String ideaId, Pageable pageable) { // TODO: Add sort support
        return commentsService.getCommentsForIdea(ideaId, pageable);
    }

    @GetMapping("/{ideaId}/comments/{commentId}")
    public CommentDocument getCommentById(@PathVariable String ideaId, @PathVariable String commentId) {
        return commentsService.getCommentById(ideaId, commentId);
    }

    @PostMapping("/{ideaId}/comments")
    public CommentDocument addComment(@PathVariable String ideaId, @RequestBody @Valid CommentInDto commentInDto) {
        return commentsService.addComment(ideaId, commentInDto);
    }

    @PostMapping("/{ideaId}/comments/{commentId}/upvote")
    public CommentDocument upvoteComment(@PathVariable String ideaId, @PathVariable String commentId) {
        return commentsService.upvoteComment(ideaId, commentId);
    }
}
