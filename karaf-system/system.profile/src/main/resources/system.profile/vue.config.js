const path = require("path");

module.exports = {
  outputDir: path.resolve(__dirname, "dist/profile"),
  publicPath: "/profile",
  pages: {
    index: {
      entry: 'src/main.js',
      title: 'System Client'
    }
  }
}