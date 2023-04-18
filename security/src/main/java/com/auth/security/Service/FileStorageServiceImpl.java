package com.auth.security.Service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path root = Paths.get("C:\\Users\\pc\\Desktop\\security\\src\\main\\webapp\\Images");

    @Override
    public ResponseEntity <Resource>  load(String filename) throws IOException {
        // Récupérer le chemin du fichier
        Path filePath = root.resolve(filename);
        // Vérifier si le fichier existe
        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            // Renvoyer la ressource
            Resource resource = new UrlResource(filePath.toUri());
            MediaType mediaType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM);
            return ResponseEntity.ok().contentType(mediaType).body(resource);
        } else {
            // Renvoyer une réponse HTTP 404 si le fichier n'existe pas ou n'est pas lisible
            return ResponseEntity.notFound().build();
        }


    }}

