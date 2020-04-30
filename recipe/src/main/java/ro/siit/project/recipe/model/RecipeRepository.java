package ro.siit.project.recipe.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Locale;


public interface RecipeRepository  extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByOrderByLastModifiedDesc();

    List<Recipe> findByCategoryOrderByLastModifiedDesc(Category category);





}
