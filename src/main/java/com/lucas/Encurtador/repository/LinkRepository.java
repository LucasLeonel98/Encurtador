package com.lucas.Encurtador.repository;

import com.lucas.Encurtador.entity.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LinkRepository extends JpaRepository<Link, UUID> {

    Link findByEncurtedUrl(String url );
   Link findByUuid(UUID uuid);
    Page<Link> findByUserId(PageRequest page, Long id);
   @Query(value="SELECT l.* FROM link l WHERE (l.redirect_url LIKE %:search%) OR (l.encurted_url LIKE %:buscaencurl%)",nativeQuery = true)
   Page<Link> buscaEncurtedUrlOuEncurtedUrl(PageRequest page, @Param("search") String busca, @Param("buscaencurl") String buscaEncUrl);

}
