package br.com.devjunior.api.services.impl;

import br.com.devjunior.api.models.User;
import br.com.devjunior.api.models.dtos.UserDto;
import br.com.devjunior.api.repositories.UserRepository;
import br.com.devjunior.api.services.UserService;
import br.com.devjunior.api.services.exceptions.DataIntegrityViolationException;
import br.com.devjunior.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("object not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto dto) {
        findByEmail(dto);
        return userRepository.save(modelMapper.map(dto, User.class));
    }

    @Override
    public User update(Integer id, UserDto userDto) {
        User user = findById(id);

        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new DataIntegrityViolationException("E-mail already registered");
        }
    }
}
