package flud.necan.Dtos;

import java.util.List;

public class IdeaOutDto {
    private final String name;
    private final String description;
    private final String imageUrl;
    private final List<String> tags;

    public IdeaOutDto(String name, String description, String imageUrl, List<String> tags) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tags = tags;
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
