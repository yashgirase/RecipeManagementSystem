package com.example.Service;

import com.example.Entity.Recipe;
import com.example.Entity.User;
import com.example.Repository.IRecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService {
    @Autowired
    IRecipeRepo recipeRepo;

    @Autowired
    UserService userService;

    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }

    public Recipe getRecipeByName(String name) {
        return recipeRepo.findByName(name);
    }

    public Recipe getRecipeById(Integer id) {
        return recipeRepo.findById(id).orElse(null);
    }

    public String addRecipe(Recipe recipe) {
        recipe.setRecipeCreationTimeStamp(LocalDateTime.now());
        recipeRepo.save(recipe);
        return "Recipe added successfully..";
    }

    public String updateIngredients( String email , Integer id, String ingredients) {
        User user = userService.findFirstEmail(email);
        Recipe recipe = recipeRepo.findById(id).orElse(null);

        if(recipe != null){
            if(recipe.getUser().equals(user)){ // means if they are equal then that user is owner of that post or recipe.
                recipe.setIngredients(ingredients);
                recipeRepo.save(recipe);
                return "Ingredients updated successfully for the recipe "+recipe.getName();
            }else{
                return "You are not authorized user to update recipe only owner can update it.";
            }
        }
        return "Recipe not found with id "+ id;
    }

    public String updateInstructions( String email ,Integer id, String instructions) {
        User user = userService.findFirstEmail(email);
        Recipe recipe = recipeRepo.findById(id).orElse(null);

        if (recipe != null) {
        if(recipe.getUser().equals(user)) {
                recipe.setInstructions(instructions);
                 recipeRepo.save(recipe);
                return "Instructions updated successfully for the recipe " + recipe.getName();
            } else {
            return "You are not authorized user to update recipe only owner can update it.";
            }
        }
        return "Recipe not found with id " + id;
    }

    public String deleteRecipe( String email ,Integer id) {
        User user = userService.findFirstEmail(email);
        Recipe recipe = recipeRepo.findById(id).orElse(null);

        if (recipe != null) {
            if (recipe.getUser().equals(user)) {
                recipeRepo.delete(recipe);
                return "Recipe for id " + id + " deleted successfully..";
            } else {
                return "You are not authorized user to update recipe only owner can update it.";
            }
        }
        return "Recipe not found with id " + id;
    }
}
