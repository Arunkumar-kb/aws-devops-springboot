package com.aws.devops.controller;

import com.aws.devops.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private AwsService awsService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {

        try {
            String key = file.getOriginalFilename();

            awsService.uploadFile(
                    key,
                    file.getInputStream(),
                    file.getSize()
            );

            return ResponseEntity.ok("File uploaded successfully!");

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/fetch-file")
    public ResponseEntity<?> fetchFile(@RequestParam String key) {
        try {
            InputStream inputStream = awsService.fetchFile(key);

            InputStreamResource resource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not found: " + key);
        }
    }
}
