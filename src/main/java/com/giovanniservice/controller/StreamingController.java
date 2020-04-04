package com.giovanniservice.controller;

import com.giovanniservice.service.BlobStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * Controller to handle streaming of data.
 */
@Controller
@RequestMapping("stream")
public class StreamingController {
    private BlobStorageService blobStorageService;

    public StreamingController(BlobStorageService blobStorageService) {
        this.blobStorageService = blobStorageService;
    }

    /**
     * Return a stream of the file from S3.
     * @param blobKey id of the file.
     * @return
     */
    @GetMapping("/{blobKey}")
    public StreamingResponseBody download(@PathVariable String blobKey) {
        return blobStorageService.streamFileFromS3(blobKey);
    }
}
