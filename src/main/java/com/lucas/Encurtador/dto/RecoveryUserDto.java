package com.lucas.Encurtador.dto;



import com.lucas.Encurtador.entity.Role;

import java.util.List;

public record RecoveryUserDto(Long id,
                              String email,
                              List<Role> roles) {
}
