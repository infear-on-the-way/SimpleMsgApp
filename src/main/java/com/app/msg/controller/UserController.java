package com.app.msg.controller;

import com.app.msg.WebSecurityConfig;
import com.app.msg.domain.entity.User;
import com.app.msg.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by infear on 2017/5/25.
 */
@Controller
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/loginPost")
    public
    @ResponseBody
    Map<String, Object> loginPost(@RequestBody User user, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (!userInfoService.login(user.getName(), user.getPassword())) {
            result.put("success", false);
            return result;
        }
        result.put("success", true);
        result.put("message", "Login Successful!");
        session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getName());
        return result;
    }
}
