package io.github.vm.patlego.aws.s3;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public interface BucketService {
    
    public PutObjectResponse putObject(String key, RequestBody body);

}
