package com.iba.fertilizersmanager.dto.core.mapper;






import com.iba.fertilizersmanager.core.BaseEntity;
import com.iba.fertilizersmanager.dto.core.BaseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import java.util.Objects;

@RequiredArgsConstructor
public abstract class AbstractDoubleMapper<E extends BaseEntity<?>, D extends BaseDto>
        implements DoubleMapper<E, D> {
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    protected final ModelMapper mapper;



    @Override
    public E toEntity(final D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(final E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }


    protected Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected void mapSpecificFields(final E source, final D destination) {
    }

    protected void mapSpecificFields(final D source, final E destination) {
    }


}
