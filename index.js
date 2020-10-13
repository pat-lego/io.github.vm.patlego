let cli = require('./installer/cli');
let build = require('./installer/commands/build');

 // Get CLI params
 const args = cli.parse();

// Execute Build Command
build.execute(args);