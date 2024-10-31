package com.lucas.Encurtador.dto;


import com.lucas.Encurtador.entity.RoleName;

public record CreateUserDto(String email,
                            String password,
                            RoleName role) {

}
