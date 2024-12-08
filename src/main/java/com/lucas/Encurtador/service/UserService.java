package com.lucas.Encurtador.service;


import com.lucas.Encurtador.dto.CreateUserDto;
import com.lucas.Encurtador.dto.LoginUserDto;
import com.lucas.Encurtador.dto.RecoveryJwtTokenDto;
import com.lucas.Encurtador.entity.Role;
import com.lucas.Encurtador.entity.User;
import com.lucas.Encurtador.repository.UserRepository;
import com.lucas.Encurtador.security.JwtTokenService;
import com.lucas.Encurtador.security.SecurityConfiguration;
import com.lucas.Encurtador.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public User getUser(LoginUserDto loginUserDto) {
        if  (!userRepository.findByEmail(loginUserDto.email()).isPresent()) {
           throw new NoSuchElementException("Usuário não encontrado !");
        }
        return  userRepository.findByEmail(loginUserDto.email()).get();
    }

    // Método responsável por criar um usuário
    public void createUser(CreateUserDto createUserDto) {

        // Cria um novo usuário com os dados fornecidos
        User newUser = User.builder()
                .email(createUserDto.email())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
    }
}
