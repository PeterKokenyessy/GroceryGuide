package com.codecool.backend.seed;

import com.codecool.backend.model.Category;
import com.codecool.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class CategoryAutoSeed implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CategoryAutoSeed.class);

    private final CategoryRepository repository;

    // Name of the file inside src/main/resources
    private static final String FILE_NAME = "finished_categories.txt";

    @Override
    public void run(String... args) throws Exception {

        if (repository.count() > 0) {
            log.info("Categories table already has data → skipping seed");
            return;
        }

        log.info("Starting category auto-seed from classpath: {}", FILE_NAME);

        Resource resource = new ClassPathResource(FILE_NAME);

        if (!resource.exists() || !resource.isReadable()) {
            log.error("Seeding file not found or not readable on classpath: {}", FILE_NAME);
            return;
        }

        long count = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
             Stream<String> lines = reader.lines()) {

            count = lines
                    .map(String::trim)
                    .filter(line -> line.startsWith("en:") && !line.isEmpty())
                    .filter(name -> !repository.existsByName(name))
                    .peek(name -> {
                        Category cat = new Category(name);
                        repository.save(cat);
                    })
                    .count();
        }

        log.info("Seeding finished → added {} categories", count);
    }
}