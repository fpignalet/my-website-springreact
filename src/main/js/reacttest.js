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
ReactDOM.render(<TestApp ids={[1,2]}/>, document.getElementById("contenerREACT1"));
ReactDOM.render(<TestApp ids={[3,4]}/>, document.getElementById("contenerREACT2"));
