package ru.javamentor.preproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.preproject.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserLoginController {
    
    @GetMapping("/")
    public ModelAndView homePage(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView getLogin(Authentication auth,
                                 HttpServletRequest req,
                                 ModelAndView mav,
                                 HttpServletResponse resp)
            throws IOException, ServletException {
        if (auth != null) {
            if (auth.getAuthorities().contains(Role.ADMIN)) {
                resp.sendRedirect("/admin");
            } else {
                resp.sendRedirect("/user");
            }
        }
        mav.setViewName("login");
        return mav;
    }
}
