'use strict';

/*************************************************************************************
 * REQUIRES
 *************************************************************************************/
const React = require('react');
const ReactDOM = require('react-dom');

const Redux = require('redux');
const ReactBStrap = require('react-bootstrap');
const ProgressBar = require('react-bootstrap/ProgressBar');

const jsondata1 = require('../../../../../src/main/resources/static/data/datatest.json');
const jsondata2 = require('../../../../../src/main/resources/static/data/datafpihist.json');

/*************************************************************************************
 * IMPORTS
 *************************************************************************************/
import ReactBaseJSX from '../../lib/reactbase.jsx';

import {ReactButtonJSX, ReactDynListJSX} from '../../lib/react/components_local.jsx';

import {ReactDisclosable, ReactTabcontainer} from '../../lib/react/components_bstrap.jsx';

import {ReactConnect} from '../../lib/react/components_frontend.jsx';

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/

class Tools {

}

/// desc
class TestApp extends ReactBaseJSX {

    /**
     * @param props
     */
    constructor(props) {
        super(props);

        this.state = {
            now: 0
        };

        this.idtab = props.idtab;
        this.modelname = "default";

        this.store = Redux.createStore(
            (state = 0, action) => {
                switch (action.type) {
                    case 'RENDERING':
                        return state + 1;
                    case 'DONE':
                        return state - 1;
                    default:
                        return state
                }
            }
        );
        this.store.subscribe(() => console.log(this.store.getState()));

    }

    componentWillMount() {
        this.modelname = "NONE";
        // this.modelname = window.reactgetmodelname();
    }

    componentDidMount() {
        /*
        client({method: 'GET', path: '/api/employees'}).done(response => {
            this.setState({employees: response.entity._embedded.employees});
        });
        */

        this.getCurrentTime()
            .then(currentTime => {
                console.log('The current time is: ' + currentTime);
                return true;
            })
            .catch(err => console.log('There was an error:' + err));

        this.updater = setInterval(() => { this.state = { now: this.state.now + 1 } }, 1000);

    }

    componentWillUnmount() {
        clearInterval(this.updater);
    }

    /**
     * @param onSuccess
     * @param onFail
     * @returns {Promise<any>}
     */
    getCurrentTime(onSuccess, onFail) {
        // Get the current 'global' time from an API using Promise
        return new Promise((resolve, reject) => {
            setTimeout(function() {
                try {
                    const currentTime = new Date();
                    resolve(currentTime);
                }
                catch (e) {
                    reject("Promise error");
                }
            }, 2000);
        })
    };

    loadFromFileData1() {
        return jsondata1["collapsable-text"].map(
            (item, index) => {
                return this.renderOneDiscosable(item, index, [
                    "over",
                    "text",
                    "subcontent"
                ]);
            }
        );

    }

    loadFromFileData2() {
        return jsondata2["DBCVConteners"].map(
            (item, index) => {
                return this.renderOneDiscosable(item, index, [
                    "contenertype",
                    "contenerphoto",
                    "contenerdate",
                    "contenername",
                    "contenersubname",
                    "conteneritems"
                ]);
            }
        );

    }

    /**
     * @param pageSize
     */
    loadFromServer(pageSize) {
        /*
        follow(client, root, [
            {rel: 'employees', params: {size: pageSize}}]
        ).then(employeeCollection => {
            return client({
                method: 'GET',
                path: employeeCollection.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                this.schema = schema.entity;
                return employeeCollection;
            });
        }).done(employeeCollection => {
            this.setState({
                employees: employeeCollection.entity._embedded.employees,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: employeeCollection.entity._links});
        });
        */
    }

    /// display 'application', contents
    render() {
        return (
            <ReactBStrap.Container>

                <ReactBStrap.Row>
                    { this.renderOneLocalCol([0,1]) }
                    { this.renderOneLocalCol([2,3]) }
                </ReactBStrap.Row>

                <ReactBStrap.Row>
                    { this.renderOneLocalCol([4,5]) }
                    { this.renderOneLocalCol([6,7]) }
                    { this.renderOneLocalCol([8,9]) }
                </ReactBStrap.Row>

                <ReactBStrap.Row>
                    { this.renderOneBStrapCol() }
                </ReactBStrap.Row>

                <ReactBStrap.Row>
                    { this.renderOneFrontendCol() }
                </ReactBStrap.Row>

            </ReactBStrap.Container>

        );
    }

    renderCanvas() {
        return (
            <ReactCanvasJSX/>
        );
    }

    /**
     * @param ids
     * @returns {*}
     */
    renderOneLocalCol(ids) {
        return (
            <ReactBStrap.Col>

                { this.renderTitle()}

                { this.renderButton(ids[0], this.idtab)}
                { this.renderButton(ids[1], this.idtab)}

                { this.renderDynList()}

            </ReactBStrap.Col>
        );
    }

    /**
     * @param ids
     * @returns {*}
     */
    renderOneBStrapCol() {
        return (
            <ReactBStrap.Col>

                { this.renderDiscosables()}

                { this.renderTabContainer()}

                { this.renderProgressBar()}

            </ReactBStrap.Col>
        );
    }

    /**
     * @param ids
     * @returns {*}
     */
    renderOneFrontendCol() {
        return (
            <ReactBStrap.Col>

                { this.renderFrontend()}

            </ReactBStrap.Col>
        );
    }

    /**
     *
     * @returns {*}
     */
    renderTitle() {
        return (
            <div>
                FROM MODEL: { this.modelname }
            </div>
        );
    }

    /**
     *
     * @param idbt
     * @param idtab
     * @returns {*}
     */
    renderButton(idbt, idtab) {
        const testtext = jsondata1["testtext"];
        const testdata = jsondata1["testdata"];
        return (
            <ReactButtonJSX
                data={document}
                id={idbt}
                testtext={ testtext }
                testdata={ testdata }
                tableid={ idtab }
            />
        );
    }

    /**
     *
     * @returns {*}
     */
    renderDiscosables() {
        // 1st parse the map to generate table items
        const namesList1 = this.loadFromFileData1();
        const namesList2 = this.loadFromFileData2();

        //2nd: ...
        return (
            <ReactBStrap.Container>

                <ReactBStrap.Row>
                    <ReactBStrap.Col>
                        {namesList1}
                    </ReactBStrap.Col>
                    <ReactBStrap.Col>
                        {namesList2}
                    </ReactBStrap.Col>
                </ReactBStrap.Row>

            </ReactBStrap.Container>
        );
    }

    /**
     *
     * @returns {*}
     */
    renderOneDiscosable(item, index, entries) {
        return (
            <ReactDisclosable
                contener={document}
                data={item}
                entries = {entries}
                index={1}
            />
        );
    }

    /**
     *
     * @returns {*}
     */
    renderTabContainer() {
        const text1 = jsondata1["link-text1"];
        const text2 = jsondata1["link-text2"];
        return (
            <ReactTabcontainer
                data={document}
                text1={text1}
                text2={text2}
            />
        );
    }

    /**
     *
     * @returns {*}
     */
    renderDynList() {
        return (
            <ReactDynListJSX data={document} />
        );
    }

    /**
     *
     * @returns {*}
     */
    renderProgressBar() {
        return (
            <ProgressBar id="progressbar" animated now={this.state.now} />
        );
    }

    /**
     *
     * @returns {*}
     */
    renderFrontend() {
        return (
            <ReactConnect />
        );
    }

}

export default TestApp;
