package io.github.vm.patlego.aws.s3.impl;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.aws.s3.BucketService;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component(service = BucketService.class, immediate = true, configurationPid = "io.github.vm.patlego.aws.s3.bucket")
public class SimpleBucketService implements BucketService {

    private S3Client s3;
  
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void putObject(String key, RequestBody body) {
        PutObjectRequest objectRequest = PutObjectRequest.builder().bucket("io.github.vm.patlego.test.bucket").key(key)
                .build();
    }

    @Activate
    protected void activate(ComponentContext context) {
        logger.info("Bucket name is " + context.getProperties().get("bucket.name").toString());
        Region region = Region.CA_CENTRAL_1;
        SdkHttpClient httpClient = ApacheHttpClient.builder().build();
        //s3 = S3Client.builder().region(region).httpClient(httpClient).build();
        
    }
}
