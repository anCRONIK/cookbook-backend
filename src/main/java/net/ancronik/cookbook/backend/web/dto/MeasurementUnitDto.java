package net.ancronik.cookbook.backend.web.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "units", itemRelation = "unit")
@JsonRootName("unit")
public class MeasurementUnitDto extends RepresentationModel<RecipeDto> {

    private String code;

    private String category;

    private boolean isImperial;

    private boolean isMetric;

}
