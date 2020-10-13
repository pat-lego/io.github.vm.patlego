let cli = require('./installer/cli');
let build = require('./installer/commands/build');
let destroy = require('./installer/commands/destroy');

 // Get CLI params
 var args = cli.parse();

// Execute Build Command
build.execute(args);

// Execute Destroy Command
destroy.execute(args);