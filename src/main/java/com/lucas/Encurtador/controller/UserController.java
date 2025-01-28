package com.lucas.Encurtador.controller;


import com.lucas.Encurtador.dto.*;
import com.lucas.Encurtador.entity.User;
import com.lucas.Encurtador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<RespUSerDto> getUser(@RequestBody LoginUserDto loginUserDto) {
         User user = userService.getUser(loginUserDto);

        RespUSerDto respUSerDto = new RespUSerDto(user.getId(),user.getEmail());
        return new ResponseEntity<RespUSerDto>(respUSerDto, HttpStatus.OK);
    }

    @PostMapping("/newuser")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<Void> changePassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        userService.changePassword(resetPasswordDTO.id(), resetPasswordDTO.newPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
    }
}
