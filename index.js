var options = require('./installer/options');
var shell = require('./installer/shell');

// Retrieve the command line arguments
const program = options.parse();
shell.which('docker-compose');
