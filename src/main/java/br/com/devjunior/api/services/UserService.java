package br.com.devjunior.api.services;

import br.com.devjunior.api.models.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
