package org.eduardomaravill.sdd_catalogo.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.eduardomaravill.sdd_catalogo.dtos.user_car_dtos.FilterCar;
import org.eduardomaravill.sdd_catalogo.models.cars_models.CarConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CarConfigurationSpecification {

    private CarConfigurationSpecification(){}
    public static Specification<CarConfiguration> filterByCriteria(FilterCar filter){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = getPredicates(filter, root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @NotNull
    private static List<Predicate> getPredicates(FilterCar filter, Root<CarConfiguration> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getMakerFilterId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("car").get("maker").get("id"), filter.getMakerFilterId()));
        }
        if (filter.getCarFilterId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("car").get("id"), filter.getCarFilterId()));
        }
        if (filter.getClassesFilterId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("classes").get("id"), filter.getClassesFilterId()));
        }
        if (filter.getEngineFilterId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("engine").get("id"), filter.getEngineFilterId()));
        }
        if (filter.getStreetTypeFilterId() != null){
            predicates.add(criteriaBuilder.equal(root.get("tire").get("streetType").get("id"), filter.getStreetTypeFilterId()));
            if (Arrays.asList(1, 5, 6).contains(filter.getStreetTypeFilterId())) {
                predicates.add(criteriaBuilder.equal(root.get("suspension").get("streetType").get("id"), filter.getStreetTypeFilterId()));
            }
        }
        if (filter.getTurboTypeFilterId() != null){
            predicates.add(criteriaBuilder.equal(root.get("turbo").get("turboType").get("id"), filter.getTurboTypeFilterId()));
        }
        if (filter.getGearFilterId() != null){
            predicates.add(criteriaBuilder.equal(root.get("gear").get("id"), filter.getGearFilterId()));
        }
        if (filter.getExcludeIds() != null && !filter.getExcludeIds().isEmpty()){
            predicates.add(criteriaBuilder.not(root.get("id").in(filter.getExcludeIds())));
        }
        return predicates;
    }
}
