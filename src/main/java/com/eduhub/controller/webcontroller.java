package com.eduhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eduhub.entity.user;
import com.eduhub.service.userservice;

import jakarta.validation.Valid;

@Controller
public class webcontroller {
	
	public userservice userservice;
	public webcontroller(userservice userservice) {
		this.userservice=userservice;
	}

    // 👉 Register page open
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new user());
        return "auth/register";
    }
    
    // 👉 Register form submit
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") user user, BindingResult result) {

        if (result.hasErrors()) {
            return "auth/register";
        }
        userservice.saveusers(user);
        // save logic later (DB)
        return "auth/home";
    }

  
    @GetMapping("/login")
    private String display1() {
    	return "auth/login";
    }
    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("user") user user, BindingResult result) {

        if (result.hasErrors()) {
            return "auth/login";
        }
        userservice.saveusers(user);
        // save logic later (DB)
        return "auth/home";
    }
    @GetMapping("/showrec")
    private String show_records() {
    	return "auth/showrecords";
    }
    
    @GetMapping("/displayrec")
    private String showdata(Model model) {
    	model.addAttribute("listUsers",userservice.getAllUsers());
    	return "auth/displayrec";
    }
    
    @GetMapping("/showformforupdate/{id}")
    public String showformforupdate(@PathVariable Long id, Model model) {
    	model.addAttribute("user",userservice.getUserById(id));
    	return "auth/updateuser";
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") user user) {
        userservice.saveusers(user);
        return "redirect:/displayrec";
    }
    @GetMapping("/deleteuser/{id}")
    public String deleteuser(@PathVariable Long id) {
    	userservice.deleteuser(id);
    	return "redirect:/displayrec";
    }
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model) {

        user user = userservice.getUserById(id);
        model.addAttribute("user", user);

        return "auth/profile";
    }
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("user") user user) {

        userservice.saveusers(user);

        return "redirect:/profile/" + user.getId();    
        
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model) 
    {

        user user = userservice.getUserById(1L);

        if(!user.getPassword().equals(oldPassword)) {
            model.addAttribute("error","Old password incorrect");
            return "auth/profile";
        }

        if(!newPassword.equals(confirmPassword)) {
            model.addAttribute("error","Passwords do not match");
            return "auth/profile";
        }

        user.setPassword(newPassword);
        userservice.saveusers(user);

        return "redirect:/profile";
    }
    @PostMapping("/saveFeedback")
    public String saveFeedback(@RequestParam String productName,
                               @RequestParam String message,
                               @RequestParam int rating,
                               Model model) {

        model.addAttribute("msg","Feedback submitted successfully!");

        return "auth/profile";
    }
}
