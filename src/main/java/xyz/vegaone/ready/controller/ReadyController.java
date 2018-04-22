package xyz.vegaone.ready.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.ready.service.FileService;

@Slf4j
@RestController
@RequestMapping(value = "/filediscovery")
public class ReadyController {

    private FileService fileService;

    @Autowired
    public ReadyController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer discoverAndIndexFiles(@RequestBody String discoveryPath){
        return fileService.discoverAndIndexFiles(discoveryPath);
    }
}
