package net.ancronik.cookbook.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO representing {@link net.ancronik.cookbook.backend.data.model.Recipe}.
 *
 * @author Nikola Presecki
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RecipeDto extends EntityModel<RecipeDto> {

    private Long id;

    private String title;

    private String shortDescription;

    private String coverImageUrl;

    //FIXME json should have key named "ingredients"
    private List<IngredientDto> ingredientList;

    private Integer preparationTime;

    private String preparationInstructions;

    private Integer cookTime;

    private String cookingInstructions;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;

    private Integer difficulty;

    private String category;

    private String authorId;
}
