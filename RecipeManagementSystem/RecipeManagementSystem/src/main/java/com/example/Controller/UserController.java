package com.example.Controller;

import com.example.Entity.User;
import com.example.Service.AuthenticationTokenService;
import com.example.Service.RecipeService;
import com.example.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    RecipeService recipeService;


    @PostMapping("user/signUp")
    public String signUpUser(@RequestBody @Valid User user){
        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String signInUser(@RequestParam  @Pattern(regexp = "^\\w+@gmail\\.com$") String userEmail , @RequestParam @NotBlank String password ){
        return userService.signInUser(userEmail , password);
    }

    @DeleteMapping("user/signOut")
    public String signOut(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam @NotBlank String authToken){
        if(authenticationTokenService.authenticate(email , authToken)){
            return userService.signOut(email);
        }else{
            return "Invalid credentials or you are already sign out";
        }
    }







}
