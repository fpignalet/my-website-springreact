import React, {Component} from 'react';
import '../css/App.css';
import axios from 'axios';

class Main extends Component {

    /**
     *
     * @param props
     */
    constructor(props) {
        super(props);
        this.state = {
            test1: 'Not answered',
            test2: 'Not answered'
        };

        this.exthttpgetjson0 = this.exthttpgetjson0.bind(this);
        this.exthttpgetjson1 = this.exthttpgetjson1.bind(this);
    }

    /**
     *
     */
    exthttpgetjson0() {
        this.getdatafrombackend("exthttpgetjson0", "content1", (value) => {
            alert("Received Successful response from server: " + value);
            this.setState({test1: value})
        });
    }

    /**
     *
     */
    exthttpgetjson1() {
        this.getdatafrombackend("exthttpgetjson1", "DBConteners", (value) => {
            alert("Received Successful response from server: " + value);
            this.setState({test2: value})
        });
    }

    /**
     *
     */
    getdatafrombackend(path, select, cbk) {
        const local = this;

        this.value = "";

        axios
            .get("http://localhost:8080/" + path)
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

        if(Object.prototype.toString.call(data) === '[object Array]') {
            data.forEach((itm, idx) => {
                value = local.parse(value, itm);
            });
        }
        else if(Object.prototype.toString.call(data) === '[object Map]') {
            Object.keys(data).forEach((itm, idx) => {
                value = local.parse(value, itm + ": " + data[itm]);
            });
        }
        else if(Object.prototype.toString.call(data) === '[object Object]') {
            Object.keys(data).forEach((itm, idx) => {
                value = local.parse(value, itm + ": " + data[itm]);
            });
        }
        else {
            value = text + data + "\r\n";
        }

        return value;
    }

    /**
     *
     * @returns {*}
     */
    render() {
        return (
            <div className="Main">
                <header className="App-header">
                    <h1 className="App-title">EXTREACT-FRONTEND</h1>
                </header>
                <div className="App-intro">
                    <div>
                        <button onClick={this.exthttpgetjson0}>exthttpgetjson0</button>
                        <div>exthttpgetjson0: {this.state.test1}</div>
                    </div>
                    <div>
                        <button onClick={this.exthttpgetjson1}>exthttpgetjson1</button>
                        <div>exthttpgetjson1: {this.state.test2}</div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Main;
