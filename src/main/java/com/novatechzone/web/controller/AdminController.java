package com.novatechzone.web.controller;

import com.novatechzone.web.model.UserRole;
import com.novatechzone.web.service.PromptService;
import com.novatechzone.web.service.PromptTypeService;
import com.novatechzone.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/view")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    PromptService promptService;
    @Autowired
    PromptTypeService promptTypeService;

    @GetMapping("/dashboard")    public String dashboard(Model model) {
        model.addAttribute("users", userService.recentUsers());
        model.addAttribute("userCount", userService.getUserCount());
        model.addAttribute("promptCount", promptService.getPromptCount());
        model.addAttribute("promptTypeCount", promptTypeService.getPromptTypeCount());
        return "AdminPanel";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "Users";
    }

    @GetMapping("/prompts")
    public String prompts(Model model) {
        model.addAttribute("prompts", promptService.readPrompts());
        return "Prompts";
    }

    @GetMapping("/prompt-types")
    public String promptTypes(Model model) {
        model.addAttribute("promptTypes", promptTypeService.readPromptTypes());
        return "PromptTypes";
    }

    @GetMapping("/admins")
    public String admins(Model model) {
        model.addAttribute("admins", userService.getAllAdmins(UserRole.ADMIN));
        return "Admins";
    }

    @GetMapping("/update-user")
    public String updateUser() {
        return "UpdateUser";
    }
}
