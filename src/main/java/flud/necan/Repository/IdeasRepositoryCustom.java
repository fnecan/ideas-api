package flud.necan.Repository;

import flud.necan.Models.IdeaDocument;
import org.springframework.data.mongodb.core.query.Update;

public interface IdeasRepositoryCustom {
    Boolean updateIdea(String ideaId, Update update);
}
