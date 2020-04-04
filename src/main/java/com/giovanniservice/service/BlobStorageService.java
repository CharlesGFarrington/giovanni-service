package com.giovanniservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class BlobStorageService {
    private static String EMPTY_FILE = "File with key %s is an empty file.";
    private AmazonS3 s3Client;

    @Value("${s3.bucket-name}")
    private String bucketName;

    public BlobStorageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    /**
     * Upload a file to the s3 bucket.
     * @param blobKey key to save the file to in S3.
     * @param file file to upload.
     * @return generated key for the file.
     */
    public void addFileToS3(String blobKey, MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(EMPTY_FILE, blobKey));
        }
        try {
            File fileToSave = new File(blobKey);
            FileOutputStream stream = new FileOutputStream(fileToSave);
            stream.write(file.getBytes());
            stream.close();
            addFileToS3(blobKey, fileToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Upload a file to the s3 bucket.
     * @param blobKey key to save the file to in S3.
     * @param file file to upload.
     * @return generated key for the file.
     */
    public void addFileToS3(String blobKey, File file) {
        s3Client.putObject(bucketName, blobKey, file);
    }

    /**
     * Stream the file from S3 with the given blobKey.
     * @param blobKey file blobKey.
     * @return the file stream.
     */
    public StreamingResponseBody streamFileFromS3(String blobKey) {
        S3Object object = s3Client.getObject(bucketName, blobKey);
        S3ObjectInputStream inputStream = object.getObjectContent();
        StreamingResponseBody stream = out -> out.write(inputStream.readAllBytes());
        return stream;
    }
}
