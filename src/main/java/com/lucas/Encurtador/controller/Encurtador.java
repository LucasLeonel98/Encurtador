package com.lucas.Encurtador.controller;

import com.lucas.Encurtador.dto.CreateUrlReq;
import com.lucas.Encurtador.entity.Link;
import com.lucas.Encurtador.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import java.util.UUID;

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

        return  ResponseEntity.status(HttpStatusCode.valueOf(201)).body(link);
    }

    @GetMapping("/redirect/{urlEncurted}")
    public RedirectView urlRedirect(@PathVariable(name = "urlEncurted") String urlEncurted)  {
        Link link = new Link();
        link = linkService.findUrl(urlEncurted);

        return new RedirectView(link.getRedirectUrl());

    }

    @GetMapping("/links")
    public Page<Link> getLinks(@RequestParam(name = "size", defaultValue = "10") int size, @RequestParam(name = "page", defaultValue = "0") int page){

        return  linkService.getLinkPages(PageRequest.of(page,size));
    }

    @GetMapping("/{user_id}/links")
    public Page<Link>  getLinksUser(@PathVariable("user_id") Long id, @RequestParam(name = "size", defaultValue = "10") int size, @RequestParam(name = "page", defaultValue = "0") int page){

        return  linkService.getLinkPagesUser(PageRequest.of(page,size), id);
    }

    @GetMapping("/links/search/")
    public Page<Link> getSearchLink(@RequestParam(name = "size", defaultValue = "10") int size, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam (name = "busca") String busca){
        return  linkService.getLink(PageRequest.of(page,size), busca);
    }
    @PutMapping("/encurta/{uuid}")
    public ResponseEntity<Link> changeLink(@PathVariable(name = "uuid") UUID id, @RequestBody CreateUrlReq data ){
       return  ResponseEntity.status(201).body(linkService.changeLink(id, data));
    }

    @DeleteMapping("/encurta/{uuid}")
    public ResponseEntity<Link> deleteLink(@PathVariable(name = "uuid") UUID uuid){
        return  ResponseEntity.status(201).body(linkService.deleteLink(uuid));
    }
}
