package com.gymproject.userservice.sampleDataPackage;

import com.github.javafaker.Faker;
import com.gymproject.userservice.dao.User;
import com.gymproject.userservice.enums.Status;
import com.gymproject.userservice.enums.UserType;
import com.gymproject.userservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private static final SecureRandom random = new SecureRandom();
    private final UserRepository userRepository;
    private final Faker faker;

    public SampleDataLoader(UserRepository userRepository, Faker faker) {
        this.userRepository = userRepository;
        this.faker = faker;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    @Override
    public void run(String... args) {


        List<User> sampleOrders = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new User(
                        faker.name().nameWithMiddle(),
                        faker.internet().emailAddress(),
                        randomEnum(UserType.class),
                        randomEnum(Status.class)
                        )).collect(Collectors.toList());

        userRepository.saveAll(sampleOrders);
    }
}