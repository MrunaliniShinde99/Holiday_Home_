package com.holiday.home.controllers;


import com.holiday.home.services.FilesStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URLConnection;


@RestController
@CrossOrigin
@RequestMapping("api/upload")
public class FilesController {
    @Autowired
    FilesStorageService storageService;

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        String mimeType = URLConnection.guessContentTypeFromName(file.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION ).contentType(MediaType.parseMediaType(mimeType)).body(file);
    }
}
