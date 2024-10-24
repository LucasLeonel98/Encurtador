package com.lucas.Encurtador.controller;

import com.lucas.Encurtador.dto.CreateUrlReq;
import com.lucas.Encurtador.entity.Link;
import com.lucas.Encurtador.service.LinkService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    @GetMapping("/redirect/{urlEncurted}")
    public RedirectView urlRedirect(@PathVariable(name = "urlEncurted") String urlEncurted)  {
        Link link = new Link();
        link = linkService.findUrl(urlEncurted);

        return new RedirectView(link.getRedirectUrl());

    }
}
