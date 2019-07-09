20190702: it's now a node + React application
- execute in console 
    npm install
    npm run-script build
    DEBUG=canvas-game-bootstrap:* npm run-script start
    or npm run-script start

20190628: it's now a node application
- install node.js
- create a package.json file with at least this content
  "scripts": {
    "build": "webpack", --> will launch webpack using webpack.config.js
    "start": "node ./rawstart.js", --> will launch node app
  },
  "devDependencies": {
    "webpack": "^4.35.0",
    "webpack-cli": "^3.3.5"
  }
- create a webpack.config.js which contains
    entry: './js/{JAVASCRIPT_FILE}.js', -->JAVASCRIPT_FILE is a main javascript file which makes imports for all others
    output: {
        filename: 'bundle.js', --> the compiled file to be used in html code
        path: path.resolve(__dirname, 'dist')
    }
- execute in console 
    npm install
    npm run-script build
    DEBUG=canvas-game-bootstrap:* npm run-script start
    or npm run-script start

2018...:
    Evolved from "a simple starting point for writing 2d games". 
    See https://github.com/jlongster/canvas-game-bootstrap/blob/master/tutorial.md for more information.
    - it's now all Javascript Classes

