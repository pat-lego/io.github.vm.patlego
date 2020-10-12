const shell = require('../../shell');

const ENV_DEV = 'DEV';

const TASK_BUILD = "BUILD";
const TASK_DESTROY = "DESTROY";

/**
 * 
 * @param {object} options - provides the object to perform the task operations
 * 
 * @example
 *  options -> {
 *                  "system": "DEV",
 *                  "task": "BUILD | DESTROY",
 *                  "composeFile": "docker-compose.yml location",
 *                  "pomFile": "pom.xml location"
 *              }
 */
function execute(options, args) {
    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    if (!options.system) {
        throw Error('Must provide the system to perform the operation on');
    }

    if (options.system.toLowerCase() === ENV_DEV.toLowerCase()) {
        dev(options, args);
    }

}

function dev(options, args) {
    if (args.commands[0].task.toLowerCase() === TASK_BUILD.toLowerCase()) {
        build(options, args);
    }

    if (args.commands[0].task.toLowerCase() === TASK_DESTROY.toLowerCase()) {
        destroy(options, args);
    }
}

function build(options, args) {
    if (!args.commands[0].pomFile) {
        throw Error('Must supply the location of the pom file in order to perform the operation');
    }

    if (!args.commands[0].composeFile) {
        throw Error('Must supply the location of the docker-compose file in order to perform the operation');
    }
    mvnBuild(options, args);
    composeDown(options, args);
    buildDatabase(options, args);
    executeSQL(options, args);
    // TODO build karaf
}

function mvnBuild(options, args) {
    if (!shell.which('java')) {
        throw Error('Java is not installed please install java prior to running a build');
    }

    if (!shell.which('mvn')) {
        throw Error('Maven is not installed please install java prior to running a build');
    }

    console.log(`About to execute mvn -f ${args.commands[0].pomFile} clean install -Pdev-build`);
    shell.exec(`mvn -f ${args.commands[0].pomFile} clean install -Pdev-build`);
}

function destroy(options, args) {
    if (!args.commands[0].composeFile) {
        throw Error('Must supply the location of the docker-compose file in order to perform the operation');
    }
    composeDown(options, args);
}

function composeDown(options, args) {
    console.log(`About to execute docker-compose -f ${args.commands[0].composeFile} down`);
    return shell.exec(`docker-compose -f ${args.commands[0].composeFile} down`);
}

function buildDatabase(options, args) {
    return shell.exec(`docker-compose -f ${args.commands[0].composeFile} up -d postgres-db`);
}

function executeSQL(options, args) {
    console.log('Wait 5 seconds, give time for the database to setup');
    sleep(5000).then(() => {
        console.log(`mvn -f ${args.commands[0].pomFile} liquibase:update -pl system.sql -Pdev-build`);
        return shell.exec(`mvn -f ${args.commands[0].pomFile} liquibase:update -pl system.sql -Pdev-build`);
    });
}

async function sleep(time) {
    await new Promise(resolve => setTimeout(resolve, time));
}

module.exports.execute = execute;