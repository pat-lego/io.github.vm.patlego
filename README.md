# My VM

This a server that contains API's and systems that I always wanted to work with.

# Required Software

In order to start the system you will require the following

1. Java 11.x 
2. Maven 3.6.3
3. Docker 19
4. Docker Compose 1.25.5

## How to start the server

Build the project using `mvn clean install -Pdev-build` and navigate to the `karaf-system` module and navigate to the `target/assembly` folder. Within the assembly folder run the following commands.

1. `docker-compose build`
2. `docker-compose up`

To validate that the server is active nagivate to the following URL: `http://localhost:8181/system/console`

## SSH into server

Start the docker server and open a command prompt, once open execute the following command `ssh -p 8101 karaf@localhost`

## How to create a new submodule

In order to create a new submodule open a terminal and run the following command in the karaf-system folder 
```mvn archetype:generate -DgroupId=pat-lego.vm.github.io -DartifactId=system.features -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false```

# Contributors
- [Patrique Legault](https://github.com/pat-lego)