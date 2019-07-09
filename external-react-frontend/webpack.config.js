module.exports = [

    //-------------------------------------------------
    //minifying module_tests.js
    {
        entry: './public/javascripts/module_tests.jsx',
        output: {
            path: __dirname + "/public/dist",
            filename: 'bundletests.js'
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