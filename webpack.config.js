const path = require("path");

//called by npm run-script build
const inputdir = './src/main/js/';
const outputdir = 'src/main/resources/static/built';

module.exports = [

    //-------------------------------------------------
    //minifying module_direct.js
    {
        entry: inputdir + 'core/appmodule.js',
        output: {
            path: path.resolve(__dirname, outputdir),
            filename: 'bundlewebsite.js'
        },
        module: {
            rules: [
                {
                    test: /\.(js)$/,
                    exclude: /node_modules/
                }
            ]
        }
    },

    //-------------------------------------------------
    //minifying module_direct.js
    {
        entry: inputdir + 'angular/angmodule.js',
        output: {
            path: path.resolve(__dirname, outputdir),
            filename: 'bundleangular.js'
        },
        module: {
            rules: [
                {
                    test: /\.(js)$/,
                    exclude: /node_modules/
                }
            ]
        }
    },

    //-------------------------------------------------
    //minifying and bundling module_game.js
    {
        entry: inputdir + 'react/reactmodule.jsx',
        output: {
            path: path.resolve(__dirname, outputdir),
            filename: 'bundlereact.js'
        },
        module: {
            rules: [
                {
                    test: /\.(js|jsx)$/,
                    exclude: /node_modules/,
                    use: [
                        {
                            loader: 'babel-loader',
                            options: {
                                presets: [
                                    '@babel/preset-env',
                                    '@babel/react',
                                    {
                                        'plugins': ['@babel/plugin-proposal-class-properties']
                                    }
                                ]
                            }
                        }
                    ]
                }
            ]
        }
    }

];