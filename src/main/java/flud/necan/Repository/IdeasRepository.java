package flud.necan.Repository;

import flud.necan.Models.IdeaDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IdeasRepository extends MongoRepository<IdeaDocument, String>, IdeasRepositoryCustom {
    Page<IdeaDocument> findAll(Pageable pageable);
    Optional<IdeaDocument> findById(String ideaId);
}
