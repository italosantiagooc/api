package br.com.devjunior.api.services.impl;

import br.com.devjunior.api.models.User;
import br.com.devjunior.api.repositories.UserRepository;
import br.com.devjunior.api.services.UserService;
import br.com.devjunior.api.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("object not found"));
    }
}
