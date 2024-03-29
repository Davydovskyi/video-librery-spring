package edu.jcourse.mapper.user;

import edu.jcourse.database.entity.User;
import edu.jcourse.dto.user.UserReadDto;
import edu.jcourse.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User from) {
        return UserReadDto.builder()
                .id(from.getId())
                .userName(from.getUserName())
                .email(from.getEmail())
                .birthDate(from.getBirthDate())
                .role(from.getRole())
                .gender(from.getGender())
                .image(from.getUserImage())
                .build();
    }
}