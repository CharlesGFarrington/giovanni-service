package com.giovanniservice.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;

/**
 * Service to handle streaming files.
 */
@Service
public class StreamingService {

    /**
     * Streams a file to allow the download to being immediately.
     * @param fileId the id of the file
     * @return the file stream
     */
    public StreamingResponseBody downloadFile(String fileId) {
        StreamingResponseBody stream = out -> {
            ClassPathResource resource = new ClassPathResource("/Ballast.mpeg");
            InputStream inputStream = resource.getInputStream();
            out.write(inputStream.readAllBytes());
        };
        return stream;
    }
}
