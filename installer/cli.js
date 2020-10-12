const commander = require('commander');

const TASK_BUILD = 'BUILD';
const TASK_DESTROY = 'DESTROY';

const REBUILD_SERVER = 'SERVER';
const REBOULD_DATABSE = 'DATABASE';

function parseTaskType(value, previousValue) {
    if (value) {
        if (value.toLowerCase() === TASK_BUILD.toLowerCase()) {
            return TASK_BUILD;
        }

        if (value.toLowerCase() === TASK_DESTROY.toLowerCase()) {
            return TASK_DESTROY;
        }
    }

    return undefined;
}

/**
 * Function to parse the 
 * @param {string} value - current CLI value 
 * @param {string} previousValue - previous CLI value
 */
function parseRebuildType(value, previousValue) {
    if (value) {
        if (value.toLowerCase() === REBUILD_SERVER.toLowerCase()) {
            return REBUILD_SERVER;
        }

        if (value.toLowerCase() === REBOULD_DATABSE.toLowerCase()) {
            return REBOULD_DATABSE;
        }
    }
    return undefined;
}

/**
 * @returns program - Commander Program
 * 
 * @example
 *      To access task use `program.commands[0].task`
 *      To access system user `program.commands[0].args[1]`
 *          Note: `program.commands[0].args[0]` returns the value `system`
 */
function programOptions() {
    // Create a new program to prevent clashes if ever wanting duplicates
    const program = new commander.Command();
    program
        .command('system')
        .arguments('[env]')
        .description('Define the system environment, curently only supported environment is DEV')
            .option('-t --task <task>',  'Task to perform on the server', parseTaskType)
            .option('-r --rebuild <stack>', 'Rebuild a part of the application', parseRebuildType)
            .option('-f --compose-file <file>', 'The location of the docker-compose file')
            .option('-p --pom-file <file>', 'The location of the pom.xml file')
        .parse(process.argv);
    return program;
}

module.exports.parse = programOptions;
