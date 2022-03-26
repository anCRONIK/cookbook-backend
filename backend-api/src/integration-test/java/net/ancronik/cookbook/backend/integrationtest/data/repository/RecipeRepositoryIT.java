package net.ancronik.cookbook.backend.integrationtest.data.repository;

import net.ancronik.cookbook.backend.api.CookbookBackendApiSpringBootApp;
import net.ancronik.cookbook.backend.api.TestTypes;
import net.ancronik.cookbook.backend.api.data.model.Recipe;
import net.ancronik.cookbook.backend.api.data.model.RecipeCategory;
import net.ancronik.cookbook.backend.api.data.model.RecipeMockData;
import net.ancronik.cookbook.backend.api.data.repository.RecipeRepository;
import net.ancronik.cookbook.backend.integrationtest.CassandraTestContainersExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = CookbookBackendApiSpringBootApp.class)
@ExtendWith({SpringExtension.class, CassandraTestContainersExtension.class})
@Tag(TestTypes.INTEGRATION)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeRepositoryIT {

    @Autowired
    RecipeRepository recipeRepository;


    @Order(1)
    @Test
    public void createMultipleRecipes() {
        recipeRepository.saveAll(RecipeMockData.generateRandomMockData(32));
    }

    @Order(2)
    @Test
    public void readAllSavedCommentsInDatabase_TestPageable() {
        int counter = 0;
        Slice<Recipe> recipes = recipeRepository.findAll(Pageable.ofSize(18));
        counter += recipes.getNumberOfElements();

        do {
            recipes = recipeRepository.findAll(recipes.nextPageable());
            counter += recipes.getNumberOfElements();
        } while (recipes.hasNext());

        assertEquals(32, counter);
    }

    @Order(3)
    @Test
    public void InvalidPrimaryKey_DatabaseThrowsException() {
        Recipe recipe = RecipeMockData.generateRandomMockData(1).get(0);
        recipe.setId(null);

        assertThrows(DataAccessException.class, () -> recipeRepository.save(recipe));
    }

    @Order(4)
    @Test
    public void deleteSpecificRecipe() {
        recipeRepository.deleteById(recipeRepository.findAll().get(0).getId());
        assertEquals(31, recipeRepository.count());
    }

    @Order(5)
    @Test
    public void findAllByRecipeByCategorz() {
        RecipeCategory category = recipeRepository.findAll().get(0).getCategory();

        Slice<Recipe> recipes = recipeRepository.findAllByCategory(category.getCategory(), Pageable.ofSize(10));

        assertTrue(recipes.getNumberOfElements() > 0);
    }

    @Order(6)
    @Test
    public void testThatDeleteAllRecipesIsNotSupported() {
        assertThrows(UnsupportedOperationException.class, () -> recipeRepository.deleteAll());
    }

}