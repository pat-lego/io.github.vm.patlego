package io.github.vm.patlego.aws.s3;

import software.amazon.awssdk.core.sync.RequestBody;

public interface BucketService {
    
    public void putObject(String key, RequestBody body);

}
