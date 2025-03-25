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
import org.springframework.web.bind.annotation.RequestParam;

@Controller @RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login (SignUpDto signUpDto, Model model) {
        userService.signUpService(signUpDto);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
