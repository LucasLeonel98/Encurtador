package com.lucas.Encurtador.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class StringAleatoria {

    public String criaLink(int tamanho){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String frase = "";
        Random random = new Random();
        for (int i = 0; i<tamanho; i++){
           frase = frase + caracteres.charAt( random.nextInt(0, caracteres.length()));
        }
        return frase;
    }
}
