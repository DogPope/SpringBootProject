package dev.daniel.compo.composer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ComposerJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(ComposerJsonDataLoader.class);
    private final ComposerRepository composerRepository;
    private final ObjectMapper objectMapper;
    public ComposerJsonDataLoader(ComposerRepository composerRepository, ObjectMapper objectMapper){
        this.composerRepository = composerRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public void run(String... args) throws Exception{
        if(composerRepository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/composer.json")) {
                Composers allComposers = objectMapper.readValue(inputStream, Composers.class);
                log.info("Reading Composers from JSON data and saving to an in-memory collection!", allComposers.composers().size());
                composerRepository.saveAll(allComposers.composers());
            }catch(IOException e){
                throw new RuntimeException("Failed to read JSON data!",e);
            }
        } else {
            log.info("Not loading Composers from JSON data because the collection contains data.");
        }
    }
}
