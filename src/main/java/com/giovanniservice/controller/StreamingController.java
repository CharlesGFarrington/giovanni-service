package com.giovanniservice.controller;

import com.giovanniservice.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    /**
     * Return a stream of the file.
     * @param fileId id of the file.
     * @return
     */
    @GetMapping("/{fileId}")
    @ResponseBody
    public StreamingResponseBody download(@PathVariable String fileId) {
        return streamingService.downloadFile(fileId);
    }
}
