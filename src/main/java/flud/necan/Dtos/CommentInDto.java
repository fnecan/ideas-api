package flud.necan.Dtos;

import flud.necan.Models.CommentDocument;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentInDto {
    @NotNull
    @NotEmpty
    private String content;  // TODO: Add length constraint

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentInDto(String content) {
        this.content = content;
    }

    public CommentDocument toDocument(String ideaId) {
        return new CommentDocument(ideaId, this.content, 0);
    }
}
