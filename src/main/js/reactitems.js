/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const ReactBStrap = require('react-bootstrap');
const ReactSNav = require('react-sidenav');
const Redux = require('redux');
const ProgressBar = require('react-bootstrap/ProgressBar');

const jsondata1 = require('../resources/static/datatest.js');
const jsondata2 = require('../resources/static/datafpicv.js');

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/
class ReactDisclosable extends React.Component {

    /**
     *
     * @param props
     * @param context
     */
    constructor(props, context) {
        super(props, context);

        this.contener = props.contener;
        this.data = props.data;
        this.entries = props.entries;

        this.subid = "subid";
        this.title = "DISCLOSE"
        this.ctlname = "collapse-text"

        this.state = {
            open: false,
            index: props.index
        };
    }

    componentDidUpdate() {
        if (this.data[this.entries[2]]) {
            this.addSub()
        }
    }

    /**
     *
     * @returns {*}
     */
    render() {
        return (
            <ReactBStrap.Container>
                { this.renderButton() }
                { this.renderCollapse() }
            </ReactBStrap.Container>
        );
    }

    renderButton() {
        const { open } = this.state;
        return (
            <ReactBStrap.Button
                onClick={
                    () => this.setState({ open: !open })
                }
                aria-controls={ this.ctlname }
                aria-expanded={ open }
            >
                { this.title }
            </ReactBStrap.Button>
        );
    }

    renderCollapse() {
        const text1 = this.data[this.entries[0]];
        const text2 = this.data[this.entries[1]];
        const nextid = this.subid + this.index;
        return (
            <ReactBStrap.Collapse in={this.state.open}>
                <div id={this.ctlname}>
                    { text1 }
                    <br/>
                    { text2 }
                    <br/>
                    <div id={ nextid }></div>
                </div>
            </ReactBStrap.Collapse>
        );
    }

    addSub(){
        const container = this.contener.querySelector('#' + this.subid + this.index);
        const data = this.data[this.entries[2]];
        const entries = this.entries;
        const index = this.index + 1;
        ReactDOM.render(
            <ReactDisclosable
                contener={ container }
                data={ data }
                entries={ entries }
                index={ index }
            /> ,
            container);
    }
}

//-----------------------------------------------------------------------------------------------------------
/// desc
class TestApp extends React.Component {

    /**
     *
     * @param props
     */
    constructor(props) {
        super(props);

        this.idtab = props.idtab;
        this.modelname = "default";

        this.state = {
            now: 0
        };

    }

    /**
     *
     */
    componentWillMount() {
        this.modelname = window.reactgetmodelname();
        alert(this.modelname);
    }

    /**
     *
     */
    componentWillUnmount() {
    }

    /// display 'application', contents
    render() {
        // 1st parse the map to generate table items
        const namesList1 = jsondata1["collapsable-text"].map(
            (item, index) => {
                return this.renderdiscosable(item, index, [
                    "title",
                    "text",
                    "subcontent"
                ]);
            }
        );

        const namesList2 = jsondata2["DBConteners"].map(
            (item, index) => {
                return this.renderdiscosable(item, index, [
                    "contenertype",
                    "contenerphoto",
                    "contenerdate",
                    "contenername",
                    "contenersubname",
                    "conteneritems"
                ]);
            }
        );

        return (
            <ReactBStrap.Container>

                <ReactBStrap.Row>
                    { this.rendercol(namesList1) }
                    { this.rendercol(namesList2) }
                </ReactBStrap.Row>

            </ReactBStrap.Container>
        );
    }

    /**
     *
     * @param ids
     * @returns {*}
     */
    rendercol(namesList) {
        return (
            <ReactBStrap.Col>
                {namesList}
            </ReactBStrap.Col>
        );
    }

    /**
     *
     * @returns {*}
     */
    renderdiscosable(item, index, entries) {
        return (
            <ReactDisclosable
                contener={document}
                data={item}
                entries = {entries}
                index={1}
            />
        );
    }

}

module.exports = TestApp;