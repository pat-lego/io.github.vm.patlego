let cli = require('../cli');
let task = require('./options/task')

const SYSTEM_ARG = "system";

// Get CLI params
const args = cli.parse();

function system() {
    if (!args) {
        throw Error('Invalid CLI arguments, failed to parse');
    }

    var systemType = getSystemType();

    if (!systemType) {
        console.log('No system property provided, will not invoke the system command');
        return;
    }

    if (args.commands[0].task) {
        task.execute({
            "system": systemType
        }, args);
    }

    if (args.commands[0].rebuild) {

    }

}

function getSystemType() {
    if (args && args.commands && args.commands[0].args && args.commands[0].args[0] && args.commands[0].args[1]) {
        if (args.commands[0].args[0].toLowerCase() === SYSTEM_ARG.toLowerCase()) {
            return args.commands[0].args[1].toLowerCase();
        }
    }
    return undefined;
}

module.exports.execute = system;