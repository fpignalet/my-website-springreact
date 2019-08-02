const path = require("path");

//called by npm run-script build
const inputdir = './src/main/js/';
const outputdir = 'src/main/resources/static/built';

module.exports = [

    //-------------------------------------------------
    //minifying module jquery
    {
        entry: inputdir + 'jquery/module_tests.js',
        output: {
            path: path.resolve(__dirname, outputdir),
            filename: 'bundlejquery.js'
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
    //minifying module angular
    {
        entry: inputdir + 'angular/module_tests.js',
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
    //minifying module react
    {
        entry: inputdir + 'react/module_tests.jsx',
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
    },

    //-------------------------------------------------
    //minifying module addressbook
    {
        entry: inputdir + 'react/module_adbook.jsx',
        output: {
            path: path.resolve(__dirname, outputdir),
            filename: 'bundleadbook.js'
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