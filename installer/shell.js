var shell = require('shelljs');

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

module.exports.which = isInstalled;