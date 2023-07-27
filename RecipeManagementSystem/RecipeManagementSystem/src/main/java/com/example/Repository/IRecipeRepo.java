package com.example.Repository;

import com.example.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // not needed as those classes which implemented jpa repository already has @Repository on them so here it is optional
public interface IRecipeRepo extends JpaRepository<Recipe , Integer> {
    Recipe findByName(String name);
}
