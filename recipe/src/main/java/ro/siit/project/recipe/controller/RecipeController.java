package ro.siit.project.recipe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.project.recipe.model.Category;
import ro.siit.project.recipe.model.Recipe;
import ro.siit.project.recipe.model.RecipeRepository;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("recipe")
public class RecipeController {


    private final RecipeRepository repo;

    @Autowired
    public RecipeController(RecipeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("")
    public ModelAndView getRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipe-table");
        List<Recipe> recipeList = repo.findAllByOrderByLastModifiedDesc();
        modelAndView.addObject("recipes_name", recipeList);
        modelAndView.addObject("category", "Home");
        return modelAndView;
    }

    @GetMapping("/soup")
    public ModelAndView getRecipesForSoup() {
        ModelAndView modelAndView = new ModelAndView("recipe-table");
        List<Recipe> recipeList = repo.findByCategoryOrderByLastModifiedDesc(Category.SOUP);
        modelAndView.addObject("recipes_name", recipeList);
        modelAndView.addObject("category", "Soup");

        return modelAndView;
    }

    @GetMapping("/maindish")
    public ModelAndView getRecipesForMainDish() {
        ModelAndView modelAndView = new ModelAndView("recipe-table");
        List<Recipe> recipeList = repo.findByCategoryOrderByLastModifiedDesc(Category.MAINDISH);
        modelAndView.addObject("recipes_name", recipeList);
        modelAndView.addObject("category", "Main Dish");
        return modelAndView;
    }

    @GetMapping("/salad")
    public ModelAndView getRecipesForSalad() {
        ModelAndView modelAndView = new ModelAndView("recipe-table");
        List<Recipe> recipeList = repo.findByCategoryOrderByLastModifiedDesc(Category.SALAD);
        modelAndView.addObject("recipes_name", recipeList);
        modelAndView.addObject("category", "Salad");
        return modelAndView;
    }

    @GetMapping("/dessert")
    public ModelAndView getRecipesForDessert() {
        ModelAndView modelAndView = new ModelAndView("recipe-table");
        List<Recipe> recipeList = repo.findByCategoryOrderByLastModifiedDesc(Category.DESSERT);
        modelAndView.addObject("recipes_name", recipeList);
        modelAndView.addObject("category", "Dessert");
        return modelAndView;
    }

    @GetMapping("/miscellaneous")
    public ModelAndView getRecipesForMiscellaneous() {
        ModelAndView modelAndView = new ModelAndView("recipe-table");
        List<Recipe> recipeList = repo.findByCategoryOrderByLastModifiedDesc(Category.MISCELLANEOUS);
        modelAndView.addObject("recipes_name", recipeList);
        modelAndView.addObject("category", "Miscellaneous");
        return modelAndView;
    }

    @GetMapping(path = "add")
    public ModelAndView recipeAddForm() {
        ModelAndView modelAndView = new ModelAndView("recipe-form");
        modelAndView.addObject("recipeAdd", new Recipe());
        return modelAndView;
    }

    @PostMapping(path = "add")
    public ModelAndView recipeSubmit(@Valid @ModelAttribute(name = "recipeAdd") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("recipe-form");
        }
        repo.save(recipe);
        return new ModelAndView("redirect:/recipe");
    }


    @PostMapping("delete")
    public String deletePostRecipeById(@RequestParam("idRecipe") Long id) {
        repo.deleteById(id);
        return "redirect:/recipe";
    }


    @GetMapping(path = "/edit/{id}")
    public ModelAndView viewEditRecipe(@PathVariable("id") Long id) {
        Recipe recipe = repo.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("edit-form");
        modelAndView.addObject("editRecipe", recipe);
        return modelAndView;
    }

    @PostMapping(path = "/update")
    public String updateRecipe(@Valid @ModelAttribute(name = "editRecipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-form";
        }
        repo.save(recipe);
        return "redirect:/recipe";
    }

    @GetMapping(path = "/details/{id}")
    public ModelAndView recipeDetails(@PathVariable("id") Long id) {
        Recipe recipe = repo.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("details-form");
        modelAndView.addObject("detailsRecipe", recipe);
        return modelAndView;
    }

    @GetMapping("/aboutme")
    public ModelAndView aboutMe() {
        return new ModelAndView("about-me");
    }

}
