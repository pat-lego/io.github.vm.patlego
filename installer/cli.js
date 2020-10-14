const commander = require('commander');

const CMD_BUILD = 'build';
const CMD_DESTROY = 'destroy';
const CMD_REBUILD = 'rebuild';

const DEV_SERVER = 'server';
const DEV_DATABASE = 'database';

const cliArgs = {};

function stackParser(option) {
    if (option.toLowerCase() === DEV_SERVER.toLowerCase()) {
        return DEV_SERVER;
    }

    if (option.toLowerCase() === DEV_DATABASE.toLowerCase()) {
        return DEV_DATABASE;
    }

    throw Error('Cannot have an undefined stack option when invoking rebuild command');
}

/**
 * @returns map - Relates the command to the commander object (command, commander) => (build, comamnd)
 * 
 */
function programOptions() {
    // Create a new program to prevent clashes if ever wanting duplicates
    const program = new commander.Command();

    program.addCommand(getBuildCommand());
    program.addCommand(getDestroyCommand());
    program.addCommand(getRebuildCommand());

    program.parse();
    return cliArgs;
}

function getBuildCommand() {
    const build = new commander.Command(CMD_BUILD);
    build
        .description('Builds the DEV environment')
        .requiredOption('-f --compose-file [file]', 'The location of the docker-compose file')
        .requiredOption('-p --pom-file [file]', 'The location of the pom.xml file')
        .action((command) => {
            cliArgs[CMD_BUILD] = command;
        });
    return build;
}

function getDestroyCommand() {
    const destroy = new commander.Command(CMD_DESTROY);
    destroy
        .description('Destroys the DEV environment')
        .requiredOption('-f --compose-file [file]', 'The location of the docker-compose file')
        .action((command) => {
            cliArgs[CMD_DESTROY] = command;
        });
    return destroy;
}

function getRebuildCommand() {
    const rebuild = new commander.Command(CMD_REBUILD);
    rebuild
        .description('Rebuilds a designated part of the DEV application stack')
        .requiredOption('-s --stack [stack]', 'The stack to rebuild', stackParser)
        .requiredOption('-p --pom-file [file]', 'The location of the pom.xml file')
        .requiredOption('-f --compose-file [file]', 'The location of the docker-compose file')
        .action((command) => {
            cliArgs[CMD_REBUILD] = command;
        });

    return rebuild;
}

module.exports.parse = programOptions;
module.exports.CMD_BUILD = CMD_BUILD;
module.exports.CMD_DESTROY = CMD_DESTROY;
module.exports.CMD_REBUILD = CMD_REBUILD;
module.exports.DEV_SERVER = DEV_SERVER;
module.exports.DEV_DATABASE = DEV_DATABASE;