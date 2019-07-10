/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const TestApp = require('./reacttests.jsx');

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
window.reactgetmodelname = function() {
    const it = document.getElementById("modelname");
    if(null != it) {
        return it.innerText;
    }
    else {
        return "DEFAULT";
    }
}

const tabName = "reacttablejsx";
const elemId = "contenerREACT1";

ReactDOM.render(
    <TestApp idtab={tabName} />,
    document.getElementById(elemId)
);
