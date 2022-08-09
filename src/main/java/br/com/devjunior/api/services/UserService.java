package br.com.devjunior.api.services;

import br.com.devjunior.api.models.User;
import br.com.devjunior.api.models.dtos.UserDto;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDto dto);

    User update(Integer id, UserDto userDto);

    void delete(Integer id);
}
