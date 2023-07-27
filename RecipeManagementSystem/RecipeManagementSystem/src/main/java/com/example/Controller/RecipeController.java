package com.example.Controller;

import com.example.Entity.Recipe;
import com.example.Service.AuthenticationTokenService;
import com.example.Service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @PostMapping("recipe")
    public String addRecipe(@RequestBody @Valid Recipe recipe , @RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam @NotBlank String token ){
        if(authenticationTokenService.authenticate(email , token)){
           return recipeService.addRecipe(recipe);
        }
        return "Not a authorized user";
    }

    @GetMapping("recipes/email/{email}/token/{token}")
    public List<Recipe> getAllRecipes(@PathVariable @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @PathVariable @NotBlank String token){
        if(authenticationTokenService.authenticate(email , token)){
            return recipeService.getAllRecipes();
        }
        return null;
    }


    @GetMapping("recipe/name/{name}")
    public Recipe getRecipeByName(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam @NotBlank String token , @PathVariable @NotBlank String name){
        if(authenticationTokenService.authenticate(email , token)){
            return recipeService.getRecipeByName(name);
        }
        return null;
    }

    @GetMapping("recipe/id/{id}")
    public Recipe getRecipeById(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam @NotBlank String token , @PathVariable @NotNull Integer id){
      if(authenticationTokenService.authenticate(email , token)){
          return recipeService.getRecipeById(id);
      }
      return null;
    }

    @PutMapping("recipe/id/{id}/ingredients/{ingredients}")
    public String updateIngredients(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam @NotBlank String token ,@PathVariable @NotNull Integer id, @PathVariable @NotNull String ingredients){
        if(authenticationTokenService.authenticate(email , token)){
            return recipeService.updateIngredients( email , id , ingredients);
        }
        return "Not a authorized user";
    }


    @PutMapping("recipe/id/{id}/instructions/{instructions}")
    public String updateInstructions(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam @NotBlank String token ,@PathVariable @NotNull Integer id, @PathVariable @NotNull String instructions){
        if(authenticationTokenService.authenticate(email , token)){
            return recipeService.updateInstructions(email , id , instructions);
        }
        return "Not a authorized user";
    }


    @DeleteMapping("recipe/id/{id}")
    public String deleteRecipe(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam @NotBlank String token , @PathVariable @NotNull Integer id){
        if(authenticationTokenService.authenticate(email , token)){
            return recipeService.deleteRecipe(email , id);
        }
        return "Not a authorized user";
    }
}
