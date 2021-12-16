package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import com.works.utils.REnum;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/note")
public class NoteRestController {

    final NoteRepository repo;
    public NoteRestController(NoteRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/add")
    public Map<REnum, Object> add(@RequestBody Note obj ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, repo.save(obj) );
        return hm;
    }

    @GetMapping("/list")
    public Map<REnum, Object> add() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, repo.findAll() );
        return hm;
    }
}
