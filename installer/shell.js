const shell = require('shelljs');

/**
 * Determine is the desired utility is available on the local system
 * @param {string} utility - The name of the utility on the system
 * 
 * @example
 *          isInstalled('docker-compose')
 */
function isInstalled(utility) {
    if (!utility) {
        throw Error('Must provide a utility to check for on the local operating system');
    }
    
    if(!shell.which(utility)) {
        return false;
    }
    return true;
}

/**
 * Performs a command line operation
 * @param {string} cmd - CLI command to run
 * @returns {number} - Represents the exit code value
 */
function exec(cmd) {
    if(!cmd) {
        throw Error('Please specify a command as a string prior to running this function');
    }

    return shell.exec(cmd).code;
}

module.exports.which = isInstalled;
module.exports.exec = exec;