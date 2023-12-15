package com.iba.fertilizersmanager.dto.core.mapper;


import com.iba.fertilizersmanager.core.BaseEntity;
import com.iba.fertilizersmanager.dto.core.BaseDto;
import com.iba.fertilizersmanager.dto.core.CompactDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.util.Objects;

public abstract class AbstractDoubleToDtoMapper <E extends BaseEntity<?>, D extends BaseDto, C extends CompactDto> extends AbstractDoubleMapper<E, D>
        implements DoubleToDtoMapper<E, D, C>{

    private final Class<C> compactClass;

    protected final ModelMapper mapper;

    public AbstractDoubleToDtoMapper(Class<E> entityClass, Class<D> dtoClass, Class<C> compactClass, ModelMapper mapper) {
        super(entityClass, dtoClass, mapper);
        this.compactClass = compactClass;
        this.mapper = mapper;
    }

      @Override
    public C toCompactFromEntity(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, compactClass);
    }

    protected Converter<E, C> toCompactDtoConverter() {
        return context -> {
            E source = context.getSource();
            C destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }
    protected void mapSpecificFields(final E source, final C destination) {
    }
}
