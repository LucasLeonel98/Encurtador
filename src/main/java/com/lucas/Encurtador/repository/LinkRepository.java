package com.lucas.Encurtador.repository;

import com.lucas.Encurtador.entity.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LinkRepository extends JpaRepository<Link, UUID> {

    Link findByEncurtedUrl(String url );
   Link findByUuid(UUID uuid);


}
