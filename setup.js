const cli = require('./installer/cli');
const build = require('./installer/commands/build');
const destroy = require('./installer/commands/destroy');
const rebuild = require('./installer/commands/rebuild');

 // Get CLI params
 const args = cli.parse();

// Execute Build Command
build.execute(args);

// Execute Destroy Command
destroy.execute(args);

// Execute Rebuild Command
rebuild.execute(args);