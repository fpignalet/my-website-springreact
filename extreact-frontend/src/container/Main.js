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
            ponged: 'Not Ponged',
            othered: 'Not Othered'
        }

        this.pong = this.pong.bind(this);
        this.other = this.other.bind(this);
    }

    /**
     *
     */
    pong() {
        axios
            .get("http://localhost:8080/ext_test1")
            .then(
                res => {
                    alert("Received Successful response from server!");
                    this.setState({ponged: 'TEST 1! '});
                },
                err => {
                    alert("Server rejected response with: " + err);
                }
            );
    }

    /**
     *
     */
    other() {
        axios
            .get("http://localhost:8080/ext_test2")
            .then(
                res => {
                    alert("Received Successful response from server!");
                    this.setState({othered: 'TEST 2! '});
                },
                err => {
                    alert("Server rejected response with: " + err);
                }
            );
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
                <p className="App-intro">
                    <div>
                        <button onClick={this.pong}>Pong!</button>
                        <div>Ponged: {this.state.ponged}</div>
                    </div>
                    <div>
                        <button onClick={this.other}>Other!</button>
                        <div>Othered: {this.state.othered}</div>
                    </div>
                </p>
            </div>
        );
    }
}

export default Main;
