'use strict';

/*************************************************************************************
 * REQUIRES
 *************************************************************************************/
const React = require('react');
const ReactDOM = require('react-dom');

const ReactBStrap = require('react-bootstrap');
const ReactSNav = require('react-sidenav');

const axios = require('axios');

/*************************************************************************************
 * IMPORTS
 *************************************************************************************/
import ReactBaseJSX from '../reactbase.jsx';

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/

export class ReactTableJSX extends ReactBaseJSX {

    constructor(props) {
        super(props);

        this.state = {
            data: props.data
        };
    }

    render() {

        // 1st parse the map to generate table items
        const namesList = this.state.data.map(
            (item, index) => {
                return this.renderOneItem(item, index);
            }
        );

        // 2nd insert table items
        return (
            <div>
                { this.renderTable(namesList) }
            </div>
        );

    }

    renderTable(namesList) {

        return (
            <table>
                <tbody>
                <tr key="0"><th>Name</th><th>Index</th></tr>
                { namesList }
                </tbody>
            </table>
        );

    }

    renderOneItem(item, index) {

        return (
            <tr key={index + 1}>
                <td>{item}</td>
                <td>{index}</td>
            </tr>
        );

    }

}

export class ReactButtonJSX extends ReactBaseJSX {

    /// @param props
    constructor(props) {
        super(props);

        this.contener = props.data;
        this.buttonid = props.id;
        this.testtext = props.testtext;
        this.testdata = props.testdata;
        this.tableid = props.tableid + props.id;

        this.state = {
            clicked: false,
            text: props.testtext[1]
        };

    }

    //set the text
    onMouseover (e) { this.setState({ text: this.testtext[0]}) }
    //set the text
    onMouseout (e) { this.setState({ text: this.testtext[1]}) }

    //set the clicked status
    onClick (e) { this.setState({ clicked: true }) }

    componentDidUpdate() {
        if (this.state.clicked) {
            const container = this.contener.querySelector('#' + this.tableid);
            ReactDOM.render(<ReactTableJSX data={ this.testdata } />, container);
        }
    }

    render() {
        if (this.state.clicked) {
            return (
                <div>
                    <h3>BUTTON { this.buttonid }</h3>
                    { this.testtext[2] }
                    <div id={this.tableid}></div>
                </div>

            );
        }
        else {
            return (
                <div>
                    <h3>BUTTON { this.buttonid }</h3>
                    { this.renderButton() }
                </div>
            );
        }
    }

    renderButton() {
        return (
            <button
                id={ this.buttonid }
                onMouseEnter={ this.onMouseover.bind(this) }
                onMouseLeave={ this.onMouseout.bind(this) }
                onClick={ this.onClick.bind(this) }>
                { this.state.text }
            </button>
        );
    }

}

/// desc
export class ReactDynListJSX extends ReactBaseJSX {

    /// @param props
    constructor(props) {
        super(props);

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            items: [],
            text: ''
        };

    }

    /// called when input modified from form
    onChange(e) {
        this.setState({ text: e.target.value });
    }

    /// called when list modified after change
    onSubmit(e) {
        e.preventDefault();

        if (!this.state.text.length) {
            return;
        }

        const newItem = {
            text: this.state.text,
            id: Date.now()
        };

        this.setState(state => ({
            items: this.state.items.concat(newItem),
            text: ''
        }));
    }

    render() {
        return (
            <div>
                { this.renderList() }
                { this.renderForm() }
            </div>
        );
    }

    renderList() {
        return (
            <ul>
                {this.state.items.map(item => (
                    <li key={item.id}>{item.text}</li>
                ))}
            </ul>
        );
    }

    renderForm() {
        return (
            <form onSubmit={this.onSubmit}>

                <label htmlFor="new-todo">
                    What needs to be added?
                </label>

                <input
                    id="new-todo"
                    onChange={this.onChange}
                    value={this.state.text}
                />

                <button>
                    Add #{this.state.items.length + 1}
                </button>

            </form>
        );
    }

}
