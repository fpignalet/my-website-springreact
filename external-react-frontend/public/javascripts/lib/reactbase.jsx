'use strict';

/*************************************************************************************
 * REQUIRES
 *************************************************************************************/
const React = require('react');
const ReactDOM = require('react-dom');

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/

class ReactBaseJSX extends React.Component {

    shouldComponentMount() {
        console.log("shouldComponentMount")
        return true;
    }

    componentWillMount() {
        console.log("componentWillMount")
    }

    componentDidMount() {
        console.log("componentDidMount")
    }

    shouldComponentUpdate() {
        console.log("shouldComponentUpdate")
        return true;
    }

    componentWillUpdate() {
        console.log("componentWillUpdate")
    }

    componentDidUpdate() {
        console.log("componentDidUpdate")
    }

    shouldComponentUnmount() {
        console.log("shouldComponentUnmount")
        return true;
    }

    componentWillUnmount() {
        console.log("componentWillUnmount")
    }

    componentDidUnmount() {
        console.log("componentDidUnmount")
    }

}

export default ReactBaseJSX;
