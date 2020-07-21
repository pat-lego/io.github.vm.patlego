const path = require("path");

module.exports = {
  outputDir: path.resolve(__dirname, "dist/client"),
  publicPath: "/client",
  pages: {
    index: {
      entry: 'src/main.js',
      title: 'System Client'
    }
  }
}