package com.iba.fertilizersmanager.dto.core.mapper;






import com.iba.fertilizersmanager.core.BaseEntity;
import com.iba.fertilizersmanager.dto.core.BaseDto;
import com.iba.fertilizersmanager.dto.core.CompactDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.util.Objects;


public abstract class AbstractDoubleToEntityMapper<E extends BaseEntity<?>, D extends BaseDto, C extends CompactDto> extends AbstractDoubleMapper<E, D>
        implements DoubleToEntityMapper<E, D, C>{
    private final Class<E> entityClass;

   // private final Class<C> compactClass;

    protected final ModelMapper mapper;

    public AbstractDoubleToEntityMapper(Class<E> entityClass, Class<D> dtoClass, ModelMapper mapper) {
        super(entityClass, dtoClass, mapper);
        this.entityClass = entityClass;
        this.mapper = mapper;
    }

    @Override
    public E toEntityFromCompact(final C compact) {
        return Objects.isNull(compact)
                ? null
                : mapper.map(compact, entityClass);
    }


    protected Converter<C, E> toEntityFromCompactConverter() {
        return context -> {
            C source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }


    protected void mapSpecificFields(final C source, final E destination) {
    }

}