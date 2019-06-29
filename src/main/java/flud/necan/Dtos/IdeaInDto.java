package flud.necan.Dtos;

import flud.necan.Models.IdeaDocument;

import javax.validation.constraints.NotNull;
import java.util.List;

public class IdeaInDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    private String imageUrl;
    private List<String> tags;

    public IdeaInDto(String name, String description, String imageUrl, List<String> tags) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tags = tags;
    }

    public IdeaInDto() {
    }

    public static IdeaDocument toDocument(IdeaInDto ideaInDto) {
        return new IdeaDocument(ideaInDto.getName(), ideaInDto.getDescription(), ideaInDto.getImageUrl(), ideaInDto.getTags());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getTags() {
        return tags;
    }
}
