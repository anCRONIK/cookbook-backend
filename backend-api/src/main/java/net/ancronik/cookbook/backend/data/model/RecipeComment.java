package net.ancronik.cookbook.backend.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Database model for comments that users leave on the recipes.
 *
 * @author Nikola Presecki
 */
@Table("recipe_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeComment implements Serializable {

    @NotNull
    @PrimaryKey
    RecipeCommentPK recipeCommentPK;

    @NotBlank
    @CodePointLength(max = 10000)
    private String text;

}
