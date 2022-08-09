package br.com.devjunior.api.configs;

import br.com.devjunior.api.models.User;
import br.com.devjunior.api.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
@Profile("local")
public class LocalConfig {

    private final UserRepository userRepository;

    public LocalConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public void startDb() {
        User user1 = new User(null, "Italo", "italosantiagoo22@gmail.com", "123");
        User user2 = new User(null, "Felipe", "felipe@gmail.com", "123");

        userRepository.saveAll(Stream.of(user1, user2).collect(Collectors.toList()));
    }
}
