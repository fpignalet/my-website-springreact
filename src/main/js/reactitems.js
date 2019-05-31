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
class ReactButtonJSX extends React.Component {

    /// @brief ctor
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

    /// display button
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
                    { this.renderbutton() }
                </div>
            );
        }
    }

    /**
     *
     * @returns {*}
     */
    renderbutton() {
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

//-----------------------------------------------------------------------------------------------------------
class ReactTableJSX extends React.Component {

    ///ctor
    constructor(props) {
        super(props);

        this.state = {
            data: props.data
        };
    }

    /// display the object content
    render() {

        // 1st parse the map to generate table items
        const namesList = this.state.data.map(
            (item, index) => {
                return this.renderitem(item, index);
            }
        );

        // 2nd insert table items
        return (
            <div>
                { this.rendertable(namesList) }
            </div>
        );

    }

    /// display table
    rendertable(namesList) {

        return (
            <table>
                <tbody>
                    <tr key="0"><th>Name</th><th>Index</th></tr>
                    { namesList }
                </tbody>
            </table>
        );

    }

    /// display 1 table item
    renderitem(item, index) {

        return (
            <tr key={index + 1}>
                <td>{item}</td>
                <td>{index}</td>
            </tr>
        );

    }

}

//-----------------------------------------------------------------------------------------------------------
/// desc
class ReactDynListJSX extends React.Component {

    /// ctor
    /// @param props
    constructor(props) {
        super(props);

        this.onchange = this.onchange.bind(this);
        this.onsubmit = this.onsubmit.bind(this);

        this.state = {
            items: [],
            text: ''
        };

    }

    /// display the object content
    render() {
        return (
            <div>
                { this.renderlist() }
                { this.renderform() }
            </div>
        );
    }

    /// display the list
    renderlist() {
        return (
            <ul>
                {this.state.items.map(item => (
                    <li key={item.id}>{item.text}</li>
                ))}
            </ul>
        );
    }

    /// display the form with list input
    renderform() {
        return (
            <form onSubmit={this.onsubmit}>

                <label htmlFor="new-todo">
                    What needs to be added?
                </label>

                <input
                    id="new-todo"
                    onChange={this.onchange}
                    value={this.state.text}
                />

                <button>
                    Add #{this.state.items.length + 1}
                </button>

            </form>
        );
    }

    /// called when input modified
    onchange(e) {
        this.setState({ text: e.target.value });
    }

    /// called when list modified
    onsubmit(e) {
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
}

//-----------------------------------------------------------------------------------------------------------
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
class ReactTabcontainer extends React.Component {

    /**
     *
     * @param props
     * @param context
     */
    constructor(props, context) {
        super(props, context);

        this.text1 = props.text1;
        this.text2 = props.text2;
        this.state = {
            //...
        };
    }

    /**
     *
     * @returns {*}
     */
    render() {
        const { open } = this.state;
        return (
            <ReactBStrap.TabContainer id="list-group-tabs-example" defaultActiveKey="#link1">
                { this.renderrow()}
            </ReactBStrap.TabContainer>

        );
    }

    /**
     *
     * @param ids
     * @returns {*}
     */
    renderrow(ids) {
        return (
            <ReactBStrap.Row>
                <ReactBStrap.Col sm={4}>
                    <ReactBStrap.ListGroup>
                        <ReactBStrap.ListGroupItem action href="#link1">
                            Link 1
                        </ReactBStrap.ListGroupItem>
                        <ReactBStrap.ListGroupItem action href="#link2">
                            Link 2
                        </ReactBStrap.ListGroupItem>
                    </ReactBStrap.ListGroup>
                </ReactBStrap.Col>
                <ReactBStrap.Col sm={8}>
                    <ReactBStrap.TabContent>
                        <ReactBStrap.TabPane eventKey="#link1">
                            { this.text1 }
                        </ReactBStrap.TabPane>
                        <ReactBStrap.TabPane eventKey="#link2">
                            { this.text2 }
                        </ReactBStrap.TabPane>
                    </ReactBStrap.TabContent>
                </ReactBStrap.Col>
            </ReactBStrap.Row>
        );
    }
}

//-----------------------------------------------------------------------------------------------------------
class ReactNavigation extends React.Component {

    /**
     *
     * @type {{selectedPath: string}}
     */
    state = { selectedPath: '' };

    /**
     *
     * @param arg
     */
    onItemSelection = (arg) => {
        this.setState({ selectedPath: arg.path })
    };

    /**
     *
     * @returns {*}
     */
    render() {

        return (
            <ReactSNav.SideNav
                selectedPath={this.state.selectedPath}
                onItemSelection={this.onItemSelection}>
                <ReactSNav.Nav id={'1'}>1</ReactSNav.Nav>
                <ReactSNav.Nav id={'2'}>2</ReactSNav.Nav>
            </ReactSNav.SideNav>
        )
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

        this.state = {
            now: 0
        };

    }

    /**
     *
     */
    componentWillMount() {
        this.modelname = window.reactgetmodelname();
    }

    /**
     *
     */
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

    /**
     *
     */
    componentWillUnmount() {
        clearInterval(this.updater);
    }

    /// display 'application', contents
    render() {
        return (
            <ReactBStrap.Container>

                <ReactBStrap.Row>
                    { this.rendercol([0,1]) }
                    {/*{ this.rendercol([2,3]) }*/}
                </ReactBStrap.Row>

                {/*<ReactBStrap.Row>*/}
                {/*        { this.rendercol([4,5]) }*/}
                {/*        { this.rendercol([6,7]) }*/}
                {/*        { this.rendercol([8,9]) }*/}
                {/*</ReactBStrap.Row>*/}

            </ReactBStrap.Container>
        );
    }

    /**
     *
     * @param ids
     * @returns {*}
     */
    rendercol(ids) {
        return (
            <ReactBStrap.Col>

                { this.rendertitle()}

                { this.renderbutton(ids[0], this.idtab)}
                { this.renderbutton(ids[1], this.idtab)}

                { this.renderdiscosables()}

                { this.rendertabcontainer()}

                { this.renderdynlist()}

                { this.renderprogressbar()}

            </ReactBStrap.Col>
        );
    }

    /**
     *
     * @returns {*}
     */
    rendertitle() {
        return (
            <div>
                FROM MODEL:
                { this.modelname }
            </div>
        );
    }

    /**
     *
     * @param idbt
     * @param idtab
     * @returns {*}
     */
    renderbutton(idbt, idtab) {
        return (
            <ReactButtonJSX
                data={document}
                id={idbt}
                testtext={ jsondata1["testtext"] }
                testdata={ jsondata1["testdata"] }
                tableid={ idtab }
            />
        );
    }

    /**
     *
     * @returns {*}
     */
    renderdiscosables() {
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
                    <ReactBStrap.Col>
                        {namesList1}
                    </ReactBStrap.Col>
                    {/*<ReactBStrap.Col>*/}
                    {/*    {namesList2}*/}
                    {/*</ReactBStrap.Col>*/}
                </ReactBStrap.Row>

            </ReactBStrap.Container>
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

    /**
     *
     * @returns {*}
     */
    rendertabcontainer() {
        return (
            <ReactTabcontainer
                data={document}
                text1={jsondata1["link-text1"]}
                text2={jsondata1["link-text2"]}
            />
        );
    }

    /**
     *
     * @returns {*}
     */
    renderdynlist() {
        return (
            <ReactDynListJSX
                data={document}
            />
        );
    }

    /**
     *
     * @returns {*}
     */
    renderprogressbar() {
        return (
            <ProgressBar id="progressbar" animated now={this.state.now} />
        );
    }

    /**
     *
     */
    reduxSample1(){
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

    /**
     *
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

}

module.exports = TestApp;