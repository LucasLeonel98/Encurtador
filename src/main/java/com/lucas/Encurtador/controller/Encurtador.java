package com.lucas.Encurtador.controller;

import com.lucas.Encurtador.dto.CreateUrlReq;
import com.lucas.Encurtador.entity.Link;
import com.lucas.Encurtador.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Encurtador {
    @Autowired
    LinkService linkService;
    @GetMapping("/teste")
    public ResponseEntity<String> teste(){
      return  ResponseEntity.ok("Encurtador online !!!");
    }

    @PostMapping("/encurta")
    public ResponseEntity<Link> encurtaLink(@RequestBody CreateUrlReq data){
        Link link = new Link();
        link = linkService.createLink(data);
        return  ResponseEntity.ok(link);
    }
}
