package dev.daniel.compo.piece;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class PieceJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(PieceJsonDataLoader.class);
    private final PieceRepository pieceRepository;
    private final ObjectMapper objectMapper;

    public PieceJsonDataLoader(PieceRepository pieceRepository, ObjectMapper objectMapper){
        this.pieceRepository = pieceRepository;
        this.objectMapper = objectMapper;
    }

    public void run(String... args) throws Exception{
        if(pieceRepository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/piece.json")) {
                Pieces allPieces = objectMapper.readValue(inputStream, Pieces.class);
                log.info("Reading Pieces from JSON data and saving to an in-memory collection!", allPieces.pieces().size());
                pieceRepository.saveAll(allPieces.pieces());
            }catch(IOException e){
                throw new RuntimeException("Failed to read JSON data!", e);
            }
        } else {
            log.info("Not loading Pieces from JSON data because the collection contains data.");
        }
    }
}
