package flud.necan.api.v1;

import flud.necan.Dtos.IdeaInDto;
import flud.necan.Dtos.IdeaOutDto;
import flud.necan.Models.IdeaDocument;
import flud.necan.Service.IdeasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
//        ArrayList<IdeaOutDto> ideasList = new ArrayList<>();
//        ideasList.add(new IdeaOutDto("Test name", "Description", "http:dupa", null));
        return ideasService.getAllIdeas(pageable);
    }

}
