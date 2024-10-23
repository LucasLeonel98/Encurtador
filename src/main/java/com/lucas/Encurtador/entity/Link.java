package com.lucas.Encurtador.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="link")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private Long creationDateTime ;

    @Column(nullable = false, length = 255)
    private String redirectUrl;

    @Column(nullable = false,length = 255)
    private String encurtedUrl;
}
