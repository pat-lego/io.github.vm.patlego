![Container Deployments](https://github.com/pat-lego/pat-lego.vm.github.io/workflows/Deploy%20Container/badge.svg) ![Build Status](https://github.com/pat-lego/pat-lego.vm.github.io/workflows/Build%20and%20Test/badge.svg) [![Coverage Status](https://coveralls.io/repos/github/pat-lego/io.github.vm.patlego/badge.svg?branch=master)](https://coveralls.io/github/pat-lego/io.github.vm.patlego?branch=master)
# My VM

Currently this VM allows users to create Worflows and deploy SPA's into a JVM that is hosted by docker compose.

# Required Software

In order to start the system you will require the following

1. Java 11.x 
2. Maven 3.6.x
3. Docker 19
4. Docker Compose 1.25.5
5. Node 12.x
6. Vue 2.6
8. Postgres 13

## How to start the server

Build the project using `mvn clean install -Pdev-build` and navigate to the `karaf-system` module and navigate to the `target/assembly` folder. Within the assembly folder run the following commands.

1. `docker-compose build`
2. `docker-compose up`

To validate that the server is active nagivate to the following URL: `http://localhost:8181/system/console`

## Debug Karaf

The Karaf server has a debug profile setup in the Docker container over port 5005. Using Visual Studio Code make sure to have the Java development package installed and click on the debugger tab. Then select the `Debug (Attach) - Karaf` mode in order to step through code.

## SSH into server

Start the docker server and open a command prompt, once open execute the following command 

```
ssh -p 8101 karaf@localhost
```

**Note:** The SSH key will continuously change everytime you destroy/create the server, I strongly recommend to set the following settings in the SSH client config.

```
Host localhost
  StrictHostKeyChecking no
  UserKnownHostsFile=/dev/null
```

## How to create a new submodule

In order to create a new submodule open a terminal and run the following command in the karaf-system folder 

```
mvn archetype:generate -DgroupId=io.github.vm.patlego -DartifactId=system.[__module-name__] -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

# Contributors
- [Patrique Legault](https://github.com/patlego)