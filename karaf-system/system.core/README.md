# Amazon CLI

Currently this project is using Amazon CLI v2.

This can be downloaded and installed [here](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html)

## Configure the CLI

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

# Amazon ECS

In order to create an ECS cluster it is recommended to install the [ECS CLI](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ECS_CLI_installation.html) client in order to perform these tasks. Thee list of tasks can be found [here](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ecs-cli-tutorial-fargate.html).

## IAM

Make sure that the user creating the cluster can access IAM sincee they will need to create users and policies

## Create ECS Configuration

Once the ecs-cli is installed users can then start to create the Fargate cluster. Amazon ECS has various flavors of installation, I prefer Fargate to EC2 and EKS.

Create the cluster configuration

```
ecs-cli configure --cluster CLUSTER_NAME --default-launch-type FARGATE --config-name CONFIG_NAME --region REGION
```

**Note:** that cluster names can only have `^[a-zA-Z0-9\\-_]{1,255}$` as valid characters

Create a CLI profile using access key and secret key

```
ecs-cli configure profile --access-key ACCESS_KEY --secret-key SECRET_ACCESS-KEY --profile-name PROFILE_NAME
```

## Create Cluster

Create the cluster using the previous configurations applied, I used `pat-lego.vm.dev` as my previous configuration names.

```
ecs-cli up --cluster-config pat-lego.vm.dev --ecs-profile pat-lego.vm.dev
```

The desired output is as follows:
```
VPC created: vpc-09e5761b75aae5ef4
Subnet created: subnet-04ea550bd3bef5a28
Subnet created: subnet-050e540c6d29a6224
Cluster creation succeeded.
```

## Retrieve the security group

Now that our cluster is created we need to get the security group that is being used

```
aws ec2 describe-security-groups --filters Name=vpc-id,Values=vpc-09e5761b75aae5ef4
```

The desired output depends on the data format specified but at the end of the output the GroupId should be defined.

```
"GroupId": "sg-0e45e3c5d89f74ce2"
```

## Add ports desired

```
aws ec2 authorize-security-group-ingress --group-id sg-0e45e3c5d89f74ce2 --protocol tcp --port 8181 --cidr 0.0.0.0/0
```

# ECS Compose

Now that the cluster is created we need to create the compose file that will be used to deploy containers to the cluster.

A sample file can be found [here](src/main/resources/ecs)

# ECS Deploy

Now that the cluster and the compose file are built users can now deploy containers into them, use the following command to deploy a container

```
ecs-cli compose --project-name pat-lego-VM-Dev service up --create-log-groups --cluster-config pat-lego.vm.dev --ecs-profile pat-lego.vm.dev
```

# ECS View Containers

To view the running containers execute the following command

```
ecs-cli compose --project-name pat-lego-VM-Dev service ps --cluster-config pat-lego.vm.dev --ecs-profile pat-lego.vm.dev
```

# ECS View Logs

ECS allows users to view the logs within them, leverage the following to do so

```
ecs-cli logs --task-id 0c2862e6e39e4eff92ca3e4f843c5b9a --follow --cluster-config pat-lego.vm.dev --ecs-profile pat-lego.vm.dev
```

# ECS Cleanup

Once the testing and the fun is over cleanup by executing the following

```
ecs-cli compose --project-name pat-lego-VM-Dev service down --cluster-config pat-lego.vm.dev --ecs-profile pat-lego.vm.dev
```

```
ecs-cli down --force --cluster-config pat-lego.vm.dev --ecs-profile pat-lego.vm.dev
```

# Amazon VPC Endpoints

VPC endpoints are like VPN's but they use the Amazon client specifically. In order to get this going the first thing you will need to do is [download](https://aws.amazon.com/vpn/client-vpn-download/) the AWS VPN Client. Once that is installed then users can create there endpoint following [this](https://docs.aws.amazon.com/vpn/latest/clientvpn-admin/cvpn-getting-started.html) documentation.

**Note:** My endpoint was configured to work with Mutual Certificate based authentication