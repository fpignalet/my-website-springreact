/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const TestApp = require('./reactitems.js');

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
ReactDOM.render(
    <TestApp idtab={"reacttablejsx"} />,
    document.getElementById("reactzone0")
);
