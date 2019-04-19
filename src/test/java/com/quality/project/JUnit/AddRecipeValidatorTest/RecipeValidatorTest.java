package com.quality.project.JUnit.AddRecipeValidatorTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.quality.project.recipe.IRecipe;
import com.quality.project.recipe.Recipe;
import com.quality.project.recipe.RecipeValidator;

public class RecipeValidatorTest {

	private IRecipeMockFactory recipeMock;
	private RecipeValidator validator;

	@Before
	public void setUp() throws Exception {
		recipeMock = new RecipeMockFactory();
		validator = new RecipeValidator();
	}

	@Test
	public void emptyRecipeName() {

		IRecipe recipe = new Recipe();
		recipe = recipeMock.getRecipes("emptyRecipeName");
		assertTrue(false == validator.isValidRecipe(recipe));
	}

	@Test
	public void emptyCusine() {
		IRecipe recipe = new Recipe();
		recipe = recipeMock.getRecipes("emptyCusine");
		assertTrue(true == validator.isValidRecipe(recipe));
	}

	@Test
	public void emptyIngredients() {
		IRecipe recipe = new Recipe();
		recipe = recipeMock.getRecipes("emptyIngredients");
		assertTrue(false == validator.isValidRecipe(recipe));
	}

	@Test
	public void validRecipe() {
		IRecipe recipe = new Recipe();
		recipe = recipeMock.getRecipes("validRecipe");
		assertTrue(true == validator.isValidRecipe(recipe));
	}

	@Test
	public void invalidRecipe() {
		IRecipe recipe = new Recipe();
		recipe = recipeMock.getRecipes("invalidRecipe");
		assertTrue(false == validator.isValidRecipe(recipe));
	}

}
