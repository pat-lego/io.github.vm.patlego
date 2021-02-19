package io.github.vm.patlego.aws.s3;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public interface BucketService {
    
    public PutObjectResponse putObject(String key, RequestBody body);

    public ResponseInputStream<GetObjectResponse> getObject(String key);

    public DeleteObjectResponse deleteObject(String key);

    public Region getRegion();

    public String getBucket();

}
