/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const ReactBStrap = require('react-bootstrap');
const ReactSNav = require('react-sidenav');
const Redux = require('redux');

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

    /// display button
    render() {
        if (this.state.clicked) {
            this.createtable();

            return (
                <div>

                    { this.testtext[2] }
                    <div id={this.tableid}></div>

                </div>

            );
        }
        else {
            return (
                <div>

                    { this.renderbutton() }
                    <div id={this.tableid}></div>

                </div>
            );
        }
    }

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

    /// diaplay a table when button is clicked
    createtable() {
        const container = this.contener.querySelector('#' + this.tableid);
        ReactDOM.render(<ReactTableJSX data={ this.testdata } />, container);
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
    constructor(props, context) {
        super(props, context);

        this.state = {
            open: false,
        };
    }

    render() {
        const { open } = this.state;
        return (
            <>
                <ReactBStrap.Button
                    onClick={() => this.setState({ open: !open })}
                    aria-controls="example-collapse-text"
                    aria-expanded={open}
                >
                    click
                </ReactBStrap.Button>
                <ReactBStrap.Collapse in={this.state.open}>
                    <div id="example-collapse-text">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer
                        labore wes anderson cred nesciunt sapiente ea proident.
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer
                        labore wes anderson cred nesciunt sapiente ea proident.
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer
                        labore wes anderson cred nesciunt sapiente ea proident.
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer
                        labore wes anderson cred nesciunt sapiente ea proident.
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer
                        labore wes anderson cred nesciunt sapiente ea proident.
                    </div>
                </ReactBStrap.Collapse>
            </>
        );
    }
}

//-----------------------------------------------------------------------------------------------------------
class ReactTabcontainer extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.text1 = props.text1;
        this.text2 = props.text2;
        this.state = {
        };
    }

    render() {
        const { open } = this.state;
        return (
            <ReactBStrap.TabContainer id="list-group-tabs-example" defaultActiveKey="#link1">
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
            </ReactBStrap.TabContainer>

        );
    }
}

//-----------------------------------------------------------------------------------------------------------
class ReactNavigation extends React.Component {

    state = { selectedPath: '' }

    onItemSelection = (arg) => {
        this.setState({ selectedPath: arg.path })
    }

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

    constructor(props) {
        super(props);

        this.ids = props.ids;

        this.store = Redux.createStore(
            (state = 0, action) => {
                switch (action.type) {
                    case 'INCREMENT':
                        return state + 1
                    case 'DECREMENT':
                        return state - 1
                    default:
                        return state
                }
            }
        );

        this.store.subscribe(() => console.log(this.store.getState()));
    }

    /// display 'application', contents
    render() {
        try {
            this.store.dispatch({ type: 'INCREMENT' });

            return (
                <div>

                    <ReactNavigation />

                    { this.renderbutton(this.ids[0])}
                    { this.renderbutton(this.ids[1])}

                    <ReactDisclosable />

                    <ReactTabcontainer
                        text1={"LINK-1-: If thy soul check thee that I come so near, Swear to thy blind soul that I was thy 'Will', And will, thy soul knows, is admitted there; Thus far for love, my love-suit, sweet, fulfil.\n" +
                        "'Will', will fulfil the treasure of thy love, Ay, fill it full with wills, and my will one.\n" +
                        "In things of great receipt with ease we prove Among a number one is reckon'd none: Then in the number let me pass untold, Though in thy store's account I one must be;\n"}
                        text2={"LINK-2-: If thy soul check thee that I come so near, Swear to thy blind soul that I was thy 'Will', And will, thy soul knows, is admitted there; Thus far for love, my love-suit, sweet, fulfil.\n" +
                        "'Will', will fulfil the treasure of thy love, Ay, fill it full with wills, and my will one.\n" +
                        "In things of great receipt with ease we prove Among a number one is reckon'd none: Then in the number let me pass untold, Though in thy store's account I one must be;\n"}
                    />

                </div>
            );
        }
        catch(ex) {

        }
        finally{
            this.store.dispatch({ type: 'DECREMENT' });
        }
    }

    renderbutton(id) {
        return (
            <div>
                <h3>BUTTON {id}</h3>
                <ReactButtonJSX
                    data={document}
                    id={id}
                    testtext={[
                    'CLICK ME!',
                    'Do something',
                    'Now displaying the following list\n' ]}
                    testdata={[
                    'JSX Test table Item1 ',
                    'JSX Test table Item2 '
                ]}
                tableid={
                    "reacttablejsx"
                }/>

            </div>
        );
    }

    reduxSample1(){
    }

}

module.exports = TestApp;