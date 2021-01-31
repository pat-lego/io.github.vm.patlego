package io.github.vm.patlego.aws.s3.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import io.github.vm.patlego.aws.s3.BucketService;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Component(service = BucketService.class, immediate = true)
public class SimpleBucketService implements BucketService {

    private S3Client s3;

    @Override
    public PutObjectResponse putObject(String key, RequestBody body) {
        PutObjectRequest objectRequest = PutObjectRequest.builder().bucket("io.github.vm.patlego.test.bucket").key(key)
                .build();
        return s3.putObject(objectRequest, RequestBody.fromString("Pat was here"));
    }

    @Activate
    protected void activate() {
        Region region = Region.CA_CENTRAL_1;
        SdkHttpClient httpClient = ApacheHttpClient.builder().build();
        s3 = S3Client.builder().region(region).httpClient(httpClient).build();
        putObject("test", null);
    }

}