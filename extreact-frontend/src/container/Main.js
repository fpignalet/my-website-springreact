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
        }

        this.httpext1 = this.httpext1.bind(this);
        this.httpext2 = this.httpext2.bind(this);
    }

    /**
     *
     */
    httpext1() {
        this.getdatafrombackend("httpext1", "content1", (value) => {
            alert("Received Successful response from server: " + value);
            this.setState({test1: value})
        });
    }

    /**
     *
     */
    httpext2() {
        this.getdatafrombackend("httpext2", "DBConteners", (value) => {
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
                        <button onClick={this.httpext1}>httpext1</button>
                        <div>httpext1: {this.state.test1}</div>
                    </div>
                    <div>
                        <button onClick={this.httpext2}>httpext2</button>
                        <div>httpext2: {this.state.test2}</div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Main;
