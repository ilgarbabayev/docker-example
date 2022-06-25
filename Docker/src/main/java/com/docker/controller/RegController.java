package com.docker.controller;

import com.docker.model.ReqModel;
import com.docker.service.RegModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/req")
@Slf4j
@CrossOrigin
public class RegController {

    @Autowired
    RegModelService service;

    @PostMapping
    public Long saveModel(@RequestBody ReqModel reqModel) {
        return service.saveRegModel(reqModel);
    }

    @GetMapping("/{name}")
    public ReqModel getByName(@PathVariable String name){
        return service.findModelByName(name);
//        return new ReqModel(2L, "test", 15);
    }

    @GetMapping
    public List<ReqModel> getAllModels() {
        log.info("returning req model 3L");
        return service.getAllModels();
//        return List.of(new ReqModel(3L, "test 03", 25));
    }
}
