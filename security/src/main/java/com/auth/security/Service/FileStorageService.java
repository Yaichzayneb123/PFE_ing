package com.auth.security.Service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface FileStorageService {


     ResponseEntity<Resource> load(String filename) throws IOException;






}
