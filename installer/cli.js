const commander = require('commander');

const CMD_BUILD = 'build';
const CMD_DESTROY = 'destroy';

var cliArgs = {};

/**
 * @returns map - Relates the command to the commander object (command, commander) => (build, comamnd)
 * 
 */
function programOptions() {
    // Create a new program to prevent clashes if ever wanting duplicates
    const program = new commander.Command();

    program.addCommand(getBuildCommand());
    program.addCommand(getDestroyCommand());
    
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
            cliArgs['build'] = command;
        });
    return build;
}

function getDestroyCommand() {
    const destroy = new commander.Command(CMD_DESTROY);
    destroy
        .description('Destroys the DEV environment')
        .requiredOption('-f --compose-file [file]', 'The location of the docker-compose file')
        .action((command) => {
            cliArgs['destroy'] = command;
        });
    return destroy;
}

module.exports.parse = programOptions;
module.exports.CMD_BUILD = CMD_BUILD;
module.exports.CMD_DESTROY = CMD_DESTROY;
