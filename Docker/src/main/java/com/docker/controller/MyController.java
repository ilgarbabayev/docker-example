package com.docker.controller;

import com.docker.model.ReqModel;
import com.docker.service.FileSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/feedback")
@Slf4j
public class MyController {
    @Autowired
    FileSystemService service;
    @Autowired
    RestTemplate template;

    @GetMapping("/{save}")
    public String getHelloPage(@PathVariable String save) throws IOException {
        log.info("LogFj from REST: " + save);
        service.saveToFile(save);

        String returnValue = service.getContent();
        log.info(returnValue);

        return returnValue;
    }


    @PostMapping()
    public void postModel(@RequestBody ReqModel model){
        log.info(model.toString());
    }

    @GetMapping()
    public ResponseEntity<String> makeGetRequest() {
        log.info("inside get request of cont04");

        return template.getForEntity("http://cont05:8080/feedback", String.class);
    }
}
