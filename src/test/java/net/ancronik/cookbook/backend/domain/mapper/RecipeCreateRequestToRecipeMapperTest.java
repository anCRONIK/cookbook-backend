package net.ancronik.cookbook.backend.domain.mapper;

import net.ancronik.cookbook.backend.data.model.Recipe;
import net.ancronik.cookbook.backend.web.dto.DtoMockData;
import net.ancronik.cookbook.backend.web.dto.RecipeCreateRequest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
public class RecipeCreateRequestToRecipeMapperTest {

    RecipeCreateRequestToRecipeMapper mapper = new RecipeCreateRequestToRecipeMapper(new ModelMapper());

    @Test
    public void map_NullGiven_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> mapper.map(null));
    }

    @Test
    public void map_DtoGiven_ReturnPopulatedModel() {
        RecipeCreateRequest request = DtoMockData.generateRandomMockDataForRecipeCreateRequest();

        Recipe recipe = mapper.map(request);

        assertNull(recipe.getId());
        assertEquals(request.getTitle(), recipe.getTitle());
        assertEquals(request.getShortDescription(), recipe.getShortDescription());
        assertEquals(request.getCoverImageUrl(), recipe.getCoverImageUrl());
        assertEquals(request.getIngredientList().size(), recipe.getIngredientList().size());
        for (int i = 0; i < request.getIngredientList().size(); ++i) {
            assertEquals(request.getIngredientList().get(i).getName(), recipe.getIngredientList().get(i).getName());
            assertEquals(request.getIngredientList().get(i).getQuantity(), recipe.getIngredientList().get(i).getQuantity().getQuantity());
            assertEquals(request.getIngredientList().get(i).getMeasurementUnit(), recipe.getIngredientList().get(i).getQuantity().getMeasurementUnit().getUnit());
        }
        assertEquals(request.getPreparationInstructions(), recipe.getPreparationInstructions());
        assertEquals(request.getPreparationTime(), recipe.getPreparationTime());
        assertEquals(request.getCookingTime(), recipe.getCookingTime());
        assertEquals(request.getCookingInstructions(), recipe.getCookingInstructions());
        assertNull(recipe.getDateCreated());
        assertNull(recipe.getDateLastUpdated());
        assertEquals(request.getDifficulty(), recipe.getDifficulty().getDifficulty());
        assertEquals(request.getCategory(), recipe.getCategory().getCategory());
        assertNull(recipe.getAuthorId());

    }
}
