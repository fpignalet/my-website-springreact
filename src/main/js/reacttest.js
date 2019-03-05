/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const TestApp = require('./reactitems');

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
ReactDOM.render(
    <TestApp idtab={"reacttablejsx"} />,
    document.getElementById("contenerREACT1")
);
