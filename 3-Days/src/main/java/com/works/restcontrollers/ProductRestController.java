package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.repositories.ProdcutRepository;
import com.works.utils.REnum;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    final ProdcutRepository repo;
    public ProductRestController(ProdcutRepository repo) {
        this.repo = repo;
    }


    @PostMapping("/add")
    public Map<REnum, Object> add( @RequestBody Product obj ) {
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
