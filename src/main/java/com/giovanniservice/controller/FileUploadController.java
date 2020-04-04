package com.giovanniservice.controller;

import com.giovanniservice.service.BlobStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("upload")
public class FileUploadController {
    private BlobStorageService blobStorageService;

    public FileUploadController(BlobStorageService blobStorageService) {
        this.blobStorageService = blobStorageService;
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<Void> uploadFile(@PathVariable String fileId, @RequestParam("file") MultipartFile file) {
        this.blobStorageService.addFileToS3(fileId, file);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
