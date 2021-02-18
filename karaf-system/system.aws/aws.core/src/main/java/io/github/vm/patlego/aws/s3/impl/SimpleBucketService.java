package io.github.vm.patlego.aws.s3.impl;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.aws.s3.BucketService;
import io.github.vm.patlego.aws.s3.constants.S3Constants;
import io.github.vm.patlego.enc.Security;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Component(service = BucketService.class, immediate = true, configurationPid = "io.github.vm.patlego.aws.s3.bucket")
public class SimpleBucketService implements BucketService {

    private S3Client s3;

    private Region region;
    private String bucketName;

    @Reference
    Security security;
  
    @Override
    public PutObjectResponse putObject(String key, RequestBody body) {
        PutObjectRequest objectRequest = PutObjectRequest.builder().bucket(this.getBucket()).key(key)
                .build();
        return s3.putObject(objectRequest, body);
    }

    @Override
    public Region getRegion() {
        return this.region;
    }

    @Override
    public String getBucket() {
        return this.bucketName;
    }

    @Activate
    protected void activate(ComponentContext context) {
        this.region = getRegion(context.getProperties().get(S3Constants.BUCKET_REGION).toString());
        this.bucketName = context.getProperties().get(S3Constants.BUCKET_NAME).toString();
        
        AwsCredentialsProvider credProviders = this.getCredentialProvider(context.getProperties().get(S3Constants.BUCKET_ACCESS_KEY).toString(), context.getProperties().get(S3Constants.BUCKET_SECRET_KEY).toString());
        SdkHttpClient httpClient = ApacheHttpClient.builder().build();
        s3 = S3Client.builder().credentialsProvider(credProviders).region(this.region).httpClient(httpClient).build();
    }

    @Deactivate
    protected void deactivate() {
        this.s3 = null;
    }

    private Region getRegion(String region) {
        return Region.of(region);
    }

    private AwsCredentialsProvider getCredentialProvider(String accessKey, String seretKey) {
        return StaticCredentialsProvider.create(getCredentials(accessKey, seretKey));
    }

    private AwsCredentials getCredentials(String accessKey, String seretKey) {
        String decodedKey= this.security.decrypt(seretKey);
        
        return new AwsCredentials(){

            @Override
            public String accessKeyId() {
                return accessKey;
            }

            @Override
            public String secretAccessKey() {
                return decodedKey;
            }
            
        };
    }
}
