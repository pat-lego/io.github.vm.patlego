const console = require('../console');
const shell = require('../shell');
const cli = require('../cli');

const CLI_CMD_MSG = 'About to execute';

function execute(args) {
     // No args set nothing to do here
     if (!args) {
        return;
    }

    // Validate that the build command was passed in
    if (args.hasOwnProperty(cli.CMD_DESTROY)) {
        console.log(`Executing the ${cli.CMD_DESTROY} command`.underline.brightGreen);
        composeDown(args);
    }
}

function composeDown(args) {
    const cmd = `docker-compose -f ${args[cli.CMD_DESTROY].composeFile} down`;

    if (!shell.which('docker-compose')) {
        throw Error('docker-compose is not installed on the system, please install it prior to executing the installer');
    }

    console.log(`${CLI_CMD_MSG} ${cmd}`.underline.yellow);
    return shell.exec(cmd);
}

module.exports.execute = execute;