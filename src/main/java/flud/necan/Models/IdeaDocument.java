package flud.necan.Models;

import flud.necan.Dtos.IdeaOutDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="ideas")
public class IdeaDocument {
    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private List<String> tags;

    public static IdeaOutDto toDto(IdeaDocument ideaDocument) {
        return new IdeaOutDto(ideaDocument.getName(), ideaDocument.getDescription(), ideaDocument.getImageUrl(), ideaDocument.getTags());
    }

    public IdeaDocument(String name, String description, String imageUrl, List<String> tags) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}

