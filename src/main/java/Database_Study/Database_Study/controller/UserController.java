package Database_Study.Database_Study.controller;

import Database_Study.Database_Study.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller @RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
}
