# Amazon CLI

Currently this project is using Amazon CLI v2.

This can be downloaded and installed [here](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html)

## Configure the CCLIL

Once the AWS CLI is installed run the `aws config` command to seetup your CLI. This is relatively easy and it will ask you a simple set of questions

1. What is your `AWS Access Key ID`
2. What is your `AWS Secret Access Key`
3. What is your default region
4. What is your preferred dataa format

# Amazon ECR

In order to login to Amazon ECR the first thing that needs to happen is that you create a programmatic AWS user and them
to all the services you require them to have. Note that you should keep your credentials in a safe location.

## Accessing Amazon ECR

Once the CLI is configured and you created your user you are ready to push Docker images to the cloud. The first thing that you will need to do it name your images with the following syntax `ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com/REPO_NAME:IMAGE_NAME`

Once this is done then you can push your images up to ECR using the following command

### Login to ECR

```
docker login -u AWS -p $(aws ecr get-login-password --region ca-central-1) ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com
```

### Create the repository

```
aws ecr create-repository \
    --repository-name REEPO_NAME \
    --image-scanning-configuration scanOnPush=true \
    --region REGION
```

### Push Docker Image

```
docker push ACCOUNT_ID.dkr.ecr.REGION.amazonaws.com/REPO_NAME:IMAGE_NAME
```