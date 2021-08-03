package com.vaibhav.parkingReservation.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.TargetType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hibernate.Hibernate.isInitialized;

@Mapper(componentModel = "spring")
public interface HibernateAwareMapper {
    @BeforeMapping
    default <T> Set<T> fixLazyLoadingSet(Collection<?> c, @TargetType Class<?> targetType) {
        if (!isInitialized(c)) {
            return Collections.emptySet();
        }
        return null;
    }

    @BeforeMapping
    default <T> List<T> fixLazyLoadingList(Collection<?> c, @TargetType Class<?> targetType) {
        if (!isInitialized(c)) {
            return Collections.emptyList();
        }
        return null;
    }
}
