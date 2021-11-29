package ru.job4j.urlshortcut.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.model.UserDTO;
import ru.job4j.urlshortcut.service.UserService;

import java.util.Map;

@RestController
public class RegisterController {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterController(UserService userService,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = "/registration")
    public UserDTO register(@RequestBody Map<String, String> siteName) {
        String site = siteName.get("site");
        String pwd = site + RandomStringUtils.randomAscii(10);
        User user = User.of(site, bCryptPasswordEncoder.encode(pwd));
        user.getRoles().add(userService.findByRole("ROLE_USER"));
        try {
            userService.addNewUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Site is already registered");
        }
        return UserDTO.of(site, pwd);
    }
}
