package io.github.vm.patlego.aws.s3.constants;

public class S3Constants {

    private S3Constants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String BUCKET_NAME = "bucket.name";

    public static final String BUCKET_ACCESS_KEY = "bucket.accesskey";

    public static final String BUCKET_SECRET_KEY = "bucket.secretkey";

    public static final String BUCKET_REGION = "bucket.region";

}
