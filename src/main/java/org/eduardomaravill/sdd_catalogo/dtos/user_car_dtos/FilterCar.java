package org.eduardomaravill.sdd_catalogo.dtos.user_car_dtos;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterCar {

    @Min(0)
    private Integer makerFilterId;

    @Min(0)
    private Integer carFilterId;

    @Min(0)
    private Integer classesFilterId;

    @Min(0)
    private Integer engineFilterId;

    @Min(0)
    private Integer streetTypeFilterId;

    @Min(0)
    private Integer turboTypeFilterId;

    @Min(0)
    private Integer gearFilterId;

    private List<Long> excludeIds;
}
