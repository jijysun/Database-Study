package Database_Study.Database_Study.domain.user.controller;

import Database_Study.Database_Study.domain.user.Repository.UserRepository;
import Database_Study.Database_Study.domain.user.dto.SignUpDto;
import Database_Study.Database_Study.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller @RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login (SignUpDto signUpDto) {
        userService.signUpService(signUpDto);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping ("/register")
    public String register (SignUpDto signUpDto) {
        userService.signUpService(signUpDto);
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
