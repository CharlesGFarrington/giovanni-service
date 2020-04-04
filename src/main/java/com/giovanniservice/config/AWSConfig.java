package com.giovanniservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AWSConfig {

    @Autowired
    private Environment env;

    @Bean
    public AmazonS3 getAmazonS3Client() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(getCredentials()))
                .withRegion(Regions.EU_WEST_2)
                .build();
    }

    private AWSCredentials getCredentials() {
        String accessKey = env.getProperty("aws-access-key");
        String secretKey = env.getProperty("aws-secret-key");
        return new BasicAWSCredentials(accessKey, secretKey);
    }
}
