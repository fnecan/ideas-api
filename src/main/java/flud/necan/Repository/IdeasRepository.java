package flud.necan.Repository;

import flud.necan.Models.IdeaDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IdeasRepository extends MongoRepository<IdeaDocument, String> {
    Page<IdeaDocument> findAll(Pageable pageable);
    Optional<IdeaDocument> findById(String ideaId);
}
