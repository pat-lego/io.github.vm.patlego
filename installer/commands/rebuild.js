const shell = require('../shell');
const cli = require('../cli');
const console = require('../console');

const CLI_CMD_MSG = 'About to execute';

function execute(args) {
    if (!args) {
        return;
    }

    if (args.hasOwnProperty(cli.CMD_REBUILD)) {
        console.log(`Executing the ${cli.CMD_REBUILD} command`.underline.brightRed);
        rebuild(args);
    }
}

function rebuild(args) {
    if (args[cli.CMD_REBUILD].stack === cli.DEV_SERVER) {
        rebuildServer(args);
    }

    if (args[cli.CMD_REBUILD].stack === cli.DEV_DATABASE) {
        rebuildDatabase(args);
    }
}

function rebuildDatabase(args) {
    console.log('Rebuilding the database'.underline.yellow);

    stopDatabaseContainer(args);
    upDatabaseContainer(args);
    migrateSQL(args);
}

function stopDatabaseContainer(args) {
    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    const cmd = `docker-compose -f ${args[cli.CMD_REBUILD].composeFile} rm -f -s -v postgres-db`;

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    shell.exec(cmd);
}

function upDatabaseContainer(args) {
    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    const cmd = `docker-compose -f ${args[cli.CMD_REBUILD].composeFile} up -d postgres-db`;

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    shell.exec(cmd);
}

function migrateSQL(args) {
    if (!shell.which('java')) {
        throw Error('Java is not installed please install java prior to running a build');
    }

    if (!shell.which('mvn')) {
        throw Error('Maven is not installed please install java prior to running a build');
    }

    const cmd = `mvn -f ${args[cli.CMD_REBUILD].pomFile} liquibase:update -pl system.sql -Pdev-build`;

    const time = 5000;
    console.log(`Sleeping ${time / 1000} in order to give the database container time to setup properly`);
    
    sleep(time).then(() => {
        console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
        shell.exec(cmd);
    })
}

async function sleep(time) {
    await new Promise(resolve => setTimeout(resolve, time));
}

function rebuildServer(args) {
    console.log('About to rebuild server'.underline.yellow);

    stopServerContainer(args);
    rebuildServerContainer(args);
    upServer(args);
}

function stopServerContainer(args) {
    const cmd = `docker-compose -f ${args[cli.CMD_REBUILD].composeFile} rm -f -s -v karaf`;

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    shell.exec(cmd);
}

function rebuildServerContainer(args) {
    const cmd = `mvn -f ${args[cli.CMD_REBUILD].pomFile} clean install -Pdev-build`;

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    shell.exec(cmd);
}

function upServer(args) {
    const cmd = `docker-compose -f ${args[cli.CMD_REBUILD].composeFile} up -d karaf`;

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    shell.exec(cmd);
}

module.exports.execute = execute;