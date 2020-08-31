const postcssPurgecss = require(`@fullhuman/postcss-purgecss`);
const { purge } = require('./tailwind.config');

const purgecss = postcssPurgecss({
  // Specify the paths to all of the template files in your project.
  content: [
    './public/**/*.html',
    './src/**/*.vue',
  ],
  // Include any special characters you're using in this regular expression.
  // See: https://tailwindcss.com/docs/controlling-file-size/#understanding-the-regex
  defaultExtractor: content => content.match(/[\w-/:]+(?<!:)/g) || [],
  // Whitelist auto generated classes for transitions and router links.
  // From: https://github.com/ky-is/vue-cli-plugin-tailwind
  whitelistPatterns: [/-(leave|enter|appear)(|-(to|from|active))$/, /^(?!(|.*?:)cursor-move).+-move$/, /^router-link(|-exact)-active$/],
});

module.exports = {
  plugins: [
    require('postcss-import'),
    require('tailwindcss'),
    require('autoprefixer'),
    // If we do a PROD build then NODE_ENV will be correctly set https://cli.vuejs.org/guide/mode-and-env.html#modes
    ...process.env.NODE_ENV === 'production' ? [purgecss] : []
  ],
};