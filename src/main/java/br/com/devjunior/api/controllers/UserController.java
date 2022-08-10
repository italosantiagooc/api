package br.com.devjunior.api.controllers;

import br.com.devjunior.api.models.dtos.UserDto;
import br.com.devjunior.api.services.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final String ID = "/{id}";
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = ID)
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(modelMapper.map(userService.findById(id), UserDto.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok().body(userService.findAll().stream().map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userService.create(userDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        return ResponseEntity.ok().body(modelMapper.map(userService.update(userDto), UserDto.class));
    }
    @DeleteMapping(value = ID)
    public ResponseEntity<UserDto> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
