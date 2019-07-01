const path = require('path');

module.exports = {
	devtool: 'sourcemaps',
	cache: true,
	mode: 'development',

	entry: [
		'./js/reacttest.js'
	],
	output: {
		path: __dirname,
		filename: './built/bundle.js'
	},

	module: {
		rules: [
			{
				test: path.join(__dirname, '.'),
				exclude: /(node_modules)/,
				use: [{
					loader: 'babel-loader',
					options: {
						presets: [
							"@babel/preset-env",
							"@babel/preset-react"
						],
						plugins: [
							"@babel/plugin-proposal-class-properties"
						]
					}
				}]
			}
		],
	}

};
