package ru.javamentor.preproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javamentor.preproject.service.UserService;
import ru.javamentor.preproject.model.Role;
import ru.javamentor.preproject.model.User;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ModelAndView getUserPage(Authentication auth, ModelAndView mav) {
        User user = userService.findUserByName(auth.getName());
        mav.addObject("user", user);
        mav.setViewName("user");
        return mav;
    }

    @GetMapping("/admin")
    public ModelAndView allUsers(ModelAndView mav) {
        List<User> allUser = userService.findAll();
        mav.setViewName("admin");
        mav.addObject("listUser", allUser);
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/admin/new")
    public ModelAndView addUser(ModelAndView mav) {
        User user = new User();
        mav.addObject("user", user);
        mav.setViewName("edit");
        return mav;
    }

    @GetMapping("/admin/edit")
    public ModelAndView editPage(@RequestParam("id") Long id, ModelAndView mav) {
        User user = userService.findById(id);
        mav.addObject("user", user);
        mav.setViewName("edit");
        return mav;
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user, HttpServletRequest req) {
        Set<Role> roles = user.getRoles();
        String RoleUser = req.getParameter("user");
        String RoleAdmin = req.getParameter("admin");
        if (RoleUser != null) {
            roles.add(Role.USER);
        }
        if (RoleAdmin != null) {
            roles.add(Role.ADMIN);
        }
        user.setRoles(roles);
        userService.saveOrUpdate(user);
        return "redirect: /admin";
    }

   @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect: /admin";
    }
}
