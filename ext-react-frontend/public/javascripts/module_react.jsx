/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

import TestApp from './impl/core/reacttests.jsx';

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
const tabName = "reacttablejsx";
const elemId = "reactzone0";

ReactDOM.render(
    <TestApp idtab={tabName} />,
    document.getElementById(elemId)
);
