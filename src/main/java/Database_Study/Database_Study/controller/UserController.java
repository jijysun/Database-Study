package Database_Study.Database_Study.controller;

import Database_Study.Database_Study.Repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private UserRepository userRepository;


    @GetMapping ("/login-cookie")
    public String login (HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("username", req.getParameter("username"));
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);

        resp.addCookie(cookie);
        return "ok";
    }

    @GetMapping ("/logout-cookie")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        return "logout";
    }

    @GetMapping("/login-session")
    public String loginSession(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        session.setAttribute("username", req.getParameter("username"));
        session.setMaxInactiveInterval(3600);
        return "login";
    }

    @GetMapping("/logout-session")
    public String  logoutSession(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        session.invalidate();
        return "logout";
    }

}
