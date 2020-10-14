const shell = require('../shell');
const cli = require('../cli');
const console = require('../console');

const CLI_CMD_MSG = 'About to execute';

/**
 * Performs the build command offered by the CLI
 * @param {map} args - Each key is mapped to the command that executed it, for instance build is a command and in the map it will contain its parameters
 * 
 */
function execute(args) {
   
    // If not defined then exit
    if (!args) {
        return;
    }

    // Validate that the build command was passed in
    if (args.hasOwnProperty(cli.CMD_BUILD)) {
        console.log(`Executing the ${cli.CMD_BUILD} command`.underline.brightYellow);
        build(args);
    }

}

function build(args) {
    if (!args[cli.CMD_BUILD].pomFile) {
        throw Error('Must supply the location of the pom file in order to perform the operation');
    }

    if (!args[cli.CMD_BUILD].composeFile) {
        throw Error('Must supply the location of the docker-compose file in order to perform the operation');
    }

    mvnBuild(args);
    composeDown(args);
    composeBuild(args);
    startDatabase(args);
    executeSQL(args).then(() => {
        startServer(args);
    });
}

function mvnBuild(args) {
    const cmd = `mvn -f ${args[cli.CMD_BUILD].pomFile} clean install -Pdev-build`;

    if (!shell.which('java')) {
        throw Error('Java is not installed please install java prior to running a build');
    }

    if (!shell.which('mvn')) {
        throw Error('Maven is not installed please install java prior to running a build');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    shell.exec(cmd);
}

function composeDown(args) {
    const cmd = `docker-compose -f ${args[cli.CMD_BUILD].composeFile} down`;

    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    return shell.exec(cmd);
}


function composeBuild(args) {
    const cmd = `docker-compose -f ${args[cli.CMD_BUILD].composeFile} build`;

    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    return shell.exec(cmd);
}

function startDatabase(args) {
    const cmd = `docker-compose -f ${args[cli.CMD_BUILD].composeFile} up -d postgres-db`;
    
    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    return shell.exec(cmd);
}

async function executeSQL(args) {
    const cmd = `mvn -f ${args[cli.CMD_BUILD].pomFile} liquibase:update -pl system.sql -Pdev-build`;
    const time = 5000;

    if (!shell.which('java')) {
        throw Error('Java is not installed please install java prior to running a build');
    }

    if (!shell.which('mvn')) {
        throw Error('Maven is not installed please install java prior to running a build');
    }

    console.log(`Wait ${time/1000} seconds, give time for the database to setup`);
    await sleep(time);
    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    return shell.exec(cmd);
}

function startServer(args) {
    const cmd = `docker-compose -f ${args[cli.CMD_BUILD].composeFile} up -d karaf`;

    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }
    
    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    return shell.exec(cmd);
}

async function sleep(time) {
    await new Promise(resolve => setTimeout(resolve, time));
}

module.exports.execute = execute;