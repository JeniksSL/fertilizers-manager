package com.iba.authservice.dto.mapper;


import com.iba.authservice.domain.User;
import com.iba.authservice.dto.UserCompact;
import com.iba.authservice.dto.UserDto;
import com.iba.fertilizersmanager.dto.core.mapper.AbstractDoubleToEntityMapper;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMapper extends AbstractDoubleToEntityMapper<User, UserDto, UserCompact> {


    public UserMapper(ModelMapper modelMapper) {
        super(User.class, UserDto.class, modelMapper);
    }



    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UserCompact.class, User.class)
                .setPostConverter(toEntityFromCompactConverter());
    }



}
