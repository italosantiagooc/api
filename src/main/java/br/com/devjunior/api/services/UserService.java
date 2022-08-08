package br.com.devjunior.api.services;

import br.com.devjunior.api.models.User;

public interface UserService {

    User findById(Integer id);
}
