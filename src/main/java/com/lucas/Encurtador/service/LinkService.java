package com.lucas.Encurtador.service;

import com.lucas.Encurtador.dto.CreateUrlReq;
import com.lucas.Encurtador.entity.Link;
import com.lucas.Encurtador.repository.LinkRepository;
import com.lucas.Encurtador.util.StringAleatoria;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class LinkService {
    @Autowired
    StringAleatoria stringAleatoria;

    @Autowired
    LinkRepository linkRepository;

    Logger logger = LoggerFactory.getLogger(LinkService.class);
    public Link createLink(CreateUrlReq data){
        String urlEncurtada = stringAleatoria.criaLink(10);

        while(true){
            if (linkRepository.findByEncurtedUrl(urlEncurtada) != null){
                logger.error("frase já gravada gerando nova ...");
                urlEncurtada = stringAleatoria.criaLink(10);
            }else {
                break;
            }
        }

        Link linkEntity = new Link();
        linkEntity.setCreationDateTime(Instant.now().getEpochSecond());
        linkEntity.setEncurtedUrl(urlEncurtada);
        linkEntity.setRedirectUrl(data.urlEncurtar());
        linkRepository.save(linkEntity);
        logger.info("Link gravado com sucesso !");
      return linkEntity;
    }

    public Link findUrl(String urlEncurted) {
        logger.info("Link para busca: " + urlEncurted);
        Link link = linkRepository.findByEncurtedUrl(urlEncurted);
        if(link == null){
              throw new IllegalArgumentException("Link não encontrado");
        }
        logger.info(link.getRedirectUrl());
        return link;
    }
}
