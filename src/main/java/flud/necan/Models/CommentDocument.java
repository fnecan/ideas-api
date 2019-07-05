package flud.necan.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection="comments")
public class CommentDocument {
    @Id
    private String id;
    @NotNull
    @NotEmpty
    private String ideaId;
    private String content;
    private Integer score;

    public CommentDocument(@NotNull @NotEmpty String ideaId, String content, Integer score) {
        this.ideaId = ideaId;
        this.content = content;
        this.score = score;
    }

    public String getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(String ideaId) {
        this.ideaId = ideaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
