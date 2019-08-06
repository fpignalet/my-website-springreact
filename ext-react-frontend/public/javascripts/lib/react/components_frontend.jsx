'use strict';

/*************************************************************************************
 * REQUIRES
 *************************************************************************************/
const React = require('react');
const ReactDOM = require('react-dom');

const axios = require('axios');

/*************************************************************************************
 * IMPORTS
 *************************************************************************************/
import ReactBaseJSX from '../reactbase.jsx';

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/

export class ReactConnect extends ReactBaseJSX {

    /**
     *
     * @param props
     */
    constructor(props) {
        super(props);

        this.state = {
            address: 'http://localhost:8080/',
            test0: 'Not answered',
            getjson0: 'exthttpgetjson0',
            content0: 'content1',
            test1: 'Not answered',
            getjson1: 'exthttpgetjson1',
            content1: 'DBCVConteners',
            test2: 'Not answered',
            populatedb: 'extpopulatecvdb',
            content2: '',
            test3: 'Not answered',
            populatefile: 'extpopulatecvfile',
            content3: '',
            test4: 'Not answered',
            callnativelib: 'extnativelib',
            content4: ''
        };

        this.exthttpgetjson0 = this.exthttpgetjson0.bind(this);
        this.exthttpgetjson1 = this.exthttpgetjson1.bind(this);
        this.populatedb = this.populatedb.bind(this);
        this.populatefile = this.populatefile.bind(this);
        this.callnativelib = this.callnativelib.bind(this);
    }

    exthttpgetjson0() {
        this.getdatafrombackend(
            this.state.getjson0,
            this.state.content0,
            (value) => {
                // alert("Received Successful response from server: " + value);
                this.setState({test0: value})
            }
        );
    }

    exthttpgetjson1() {
        this.getdatafrombackend(
            this.state.getjson1,
            this.state.content1,
            (value) => {
                // alert("Received Successful response from server: " + value);
                this.setState({test1: value})
            }
        );
    }

    populatedb() {
        this.getdatafrombackend(
            this.state.populatedb,
            this.state.content2,
            (value) => {
                // alert("Received Successful response from server: " + value);
                this.setState({test2: value})
            }
        );
    }

    populatefile() {
        this.getdatafrombackend(
            this.state.populatefile,
            this.state.content3,
            (value) => {
                // alert("Received Successful response from server: " + value);
                this.setState({test3: value})
            }
        );
    }

    callnativelib() {
        this.getdatafrombackend(
            this.state.callnativelib,
            this.state.content4,
            (value) => {
                // alert("Received Successful response from server: " + value);
                this.setState({test4: value})
            }
        );
    }

    /**
     *
     * @param path
     * @param select
     * @param cbk
     */
    getdatafrombackend(path, select, cbk) {
        const local = this;

        axios
            .get(this.state.address + path)
            .then(
                res => {
                    const jsonStr = JSON.stringify(res.data);
                    const jsonRes = JSON.parse(jsonStr);

                    let text = "";
                    let value = local.parse(text, jsonRes[select]);
                    cbk(value);
                },
                err => {
                    alert("Server rejected response with: " + err);
                }
            );
    }

    /**
     *
     * @param text
     * @param select
     */
    parse(text, data) {
        const local = this;

        let value = text;

        switch(Object.prototype.toString.call(data)) {

            case '[object Array]':
                data.forEach(
                    (itm, idx) => {
                        value = local.parse(value, itm);
                    }
                );
                break;

            case '[object Map]':
                Object.keys(data).forEach(
                    (itm, idx) => {
                        value = local.parse(value, itm + ": " + data[itm]);
                    }
                );
                break;

            case '[object Object]':
                Object.keys(data).forEach(
                    (itm, idx) => {
                        value = local.parse(value, itm + ": " + data[itm]);
                    }
                );
                break;

            default:
                value = text + data + "\n";
                break;

        }

        return value;
    }

    render() {
        return (
            <div className="Main">
                <header className="App-header">
                    <h1 className="App-title">EXTREACT-FRONTEND</h1>
                </header>
                <div className="App-intro">
                    <div>
                        <button onClick={this.exthttpgetjson0}>exthttpgetjson0</button>
                        <div>exthttpgetjson0: {this.state.test0}</div>
                    </div>
                    <div>
                        <button onClick={this.exthttpgetjson1}>exthttpgetjson1</button>
                        <div>exthttpgetjson1: {this.state.test1}</div>
                    </div>
                    <div>
                        <button onClick={this.populatedb}>populatedb</button>
                        <div>populatedb: {this.state.test2}</div>
                    </div>
                    <div>
                        <button onClick={this.populatefile}>populatefile</button>
                        <div>populatefile: {this.state.test3}</div>
                    </div>
                    <div>
                        <button onClick={this.callnativelib}>callnativelib</button>
                        <div>nativelib: {this.state.test4}</div>
                    </div>
                </div>
            </div>
        );
    }
}
