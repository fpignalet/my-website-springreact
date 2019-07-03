const path = require("path");

module.exports = [

    //-------------------------------------------------
    //bundling module_direct.js
    {
        entry: './src/main/js/website.js',
        output: {
            path: path.resolve(__dirname, 'src/main/resources/static/built'),
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
    //transpiling and bundling module_game.js
    {
        entry: './src/main/js/reactmodule.js',
        output: {
            path: path.resolve(__dirname, 'src/main/resources/static/built'),
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