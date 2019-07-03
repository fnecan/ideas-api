package flud.necan.Service;

import flud.necan.Dtos.IdeaInDto;
import flud.necan.Dtos.IdeaOutDto;
import flud.necan.Models.IdeaDocument;
import flud.necan.Repository.IdeasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class IdeasService {

    @Autowired
    public IdeasRepository ideasRepository;

    public IdeasService(IdeasRepository ideasRepository) {
        this.ideasRepository = ideasRepository;
    }

    public Page<IdeaDocument> getAllIdeas(Pageable pageable) {
        return ideasRepository.findAll(pageable);
    }

    public IdeaDocument addIdea(IdeaInDto ideaInDto) {
        return ideasRepository.save(new IdeaDocument(ideaInDto.getName(), ideaInDto.getDescription(), ideaInDto.getImageUrl(), ideaInDto.getTags()));
    }

    public IdeaDocument getIdea(String ideaId) {
        return ideasRepository.findById(ideaId).orElse(null);
    }

    public Boolean updateIdea(String ideaId, IdeaInDto ideaInDto) {
        Update update = new Update();
        if(ideaInDto.getName() != null) update.set("name", ideaInDto.getName());
        if(ideaInDto.getDescription() != null) update.set("description", ideaInDto.getDescription());
        if(ideaInDto.getImageUrl() != null) update.set("imageUrl", ideaInDto.getImageUrl());
        if(ideaInDto.getTags() != null) update.set("tags", ideaInDto.getTags());

        return ideasRepository.updateIdea(ideaId, update);
    }
}
