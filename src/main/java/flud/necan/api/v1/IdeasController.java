package flud.necan.api.v1;

import flud.necan.Dtos.IdeaInDto;
import flud.necan.Models.IdeaDocument;
import flud.necan.Service.IdeasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/ideas")
public class IdeasController {

    @Autowired
    private IdeasService ideasService;

    public IdeasController(IdeasService ideasService) {
        this.ideasService = ideasService;
    }

    @PostMapping
    public IdeaDocument addIdea(@Valid @RequestBody IdeaInDto ideaInDto) {
        return ideasService.addIdea(ideaInDto);
    }

    @GetMapping
    public Page<IdeaDocument> getIdeasList(Pageable pageable) {
        return ideasService.getAllIdeas(pageable);
    }

    // TODO: Add post mapping
    // TODO: Add get mapping

}
