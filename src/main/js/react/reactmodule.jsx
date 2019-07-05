/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const TestApp = require('./reactitems.jsx');

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
window.reactgetmodelname = function() {
    return document.getElementById("modelname").innerText;
}

ReactDOM.render(
    <TestApp idtab={"reacttablejsx"} />,
    document.getElementById("contenerREACT1")
);
