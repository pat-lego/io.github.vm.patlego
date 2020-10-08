const { program } = require('commander');

const PROGRAM_VERSION = '0.0.1';

const ENV_DEV = 'DEV';

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

function programOptions() {
    program.version(PROGRAM_VERSION);
    program
        .command('system [env]')
        .description('Environment Type to Build')
            .option('-t', '--task <task>',  'Task to perform on the server', parseTaskType)
            .option('-r', '--rebuild <stack>', 'Rebuild a part of the application', parseRebuildType);

    return program.parse(process.argv);
}

module.exports.parse = programOptions;
