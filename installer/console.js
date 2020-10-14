const colors = require('colors');

/**
 * Print information to the console
 * @param {string} - msg
 * 
 * @example
 * msg.underline.yellow
 */
function log(msg) {
    console.log('\n');
    console.log(msg);
    console.log('\n');
}

module.exports.log = log;