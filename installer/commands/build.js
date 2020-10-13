let shell = require('../shell');

const CMD_BUILD = 'BUILD';
const CLI_CMD_MSG = 'About to execute';

/**
 * Performs the build command offered by the CLI
 * @param {object} args - The cli args coming from commander.js
 * 
 */
function execute(args) {
   
    // No args set nothing to do here
    if (!args.commands[0].args[0]) {
        return;
    }

    // Validate that the build command was passed in
    if (args.commands[0].args[0].toLowerCase() === CMD_BUILD.toLowerCase()) {
        build(args);
    }

}

function build(args) {
    if (!args.commands[0].pomFile) {
        throw Error('Must supply the location of the pom file in order to perform the operation');
    }

    if (!args.commands[0].composeFile) {
        throw Error('Must supply the location of the docker-compose file in order to perform the operation');
    }

    mvnBuild(args);
    composeDown(args);
    composeBuild(args);
    startDatabase(args);
    executeSQL(args);
    startServer(args);
}

function mvnBuild(args) {
    const cmd = `mvn -f ${args.commands[0].pomFile} clean install -Pdev-build`;

    if (!shell.which('java')) {
        throw Error('Java is not installed please install java prior to running a build');
    }

    if (!shell.which('mvn')) {
        throw Error('Maven is not installed please install java prior to running a build');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`);
    shell.exec(cmd);
}

function composeDown(args) {
    const cmd = `docker-compose -f ${args.commands[0].composeFile} down`;

    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`);
    return shell.exec(cmd);
}

function composeBuild(args) {
    const cmd = `docker-compose -f ${args.commands[0].composeFile} build`;

    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`);
    return shell.exec(cmd);
}

function startDatabase(args) {
    const cmd = `docker-compose -f ${args.commands[0].composeFile} up -d postgres-db`;
    
    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`);
    return shell.exec(cmd);
}

function executeSQL(args) {
    const cmd = `mvn -f ${args.commands[0].pomFile} liquibase:update -pl system.sql -Pdev-build`;
    const time = 5000;

    if (!shell.which('java')) {
        throw Error('Java is not installed please install java prior to running a build');
    }

    if (!shell.which('mvn')) {
        throw Error('Maven is not installed please install java prior to running a build');
    }

    console.log(`Wait ${time/1000} seconds, give time for the database to setup`);
    sleep(time).then(() => {
        console.log(`${CLI_CMD_MSG} ${cmd}`);
        return shell.exec(cmd);
    });
}

function startServer(args) {
    const cmd = `docker-compose -f ${args.commands[0].composeFile} up -d karaf`;

    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }
    
    console.log(`${CLI_CMD_MSG} ${cmd}`);
    return shell.exec(cmd);
}

async function sleep(time) {
    await new Promise(resolve => setTimeout(resolve, time));
}

module.exports.execute = execute;