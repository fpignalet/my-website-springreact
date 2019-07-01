/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
//import React from 'react';
//import ReactDOM from 'react-dom';

/*************************************************************************************
 * IMPLEMENTATION: TESTS
 *************************************************************************************/
class TestButtonJSX extends React.Component {

    /// @brief ctor
    /// @param props
    constructor(props) {
        super(props);

        //--------------
        this.contener = props.data;

        this.buttontext = [
            'CLICK ME!',
            'Do something',
            'Did something using React/JSX: now displaying the following list\n'
        ];

        this.tableid = "reacttablejsx";
        this.tabledata = [
            'JSX Test table Item1 ',
            'JSX Test table Item2 ',
            'JSX Test table Item3 '
        ];

        //--------------
        this.state = {
            clicked: false,
            text: this.buttontext[1]
        };

    }

    //set the text
    onMouseover (e) { this.setState({ text: this.buttontext[0]}) }
    //set the text
    onMouseout (e) { this.setState({ text: this.buttontext[1]}) }
    //set the clicked status
    onClick (e) { this.setState({ clicked: true }) }

    /// display button
    render() {
        if (this.state.clicked) {
            this.createtable();

            return (
                <div>

                    { this.buttontext[2] }
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
        ReactDOM.render(<TestTableJSX data={ this.tabledata } />, container);
    }

}

//-----------------------------------------------------------------------------------------------------------
class TestTableJSX extends React.Component {

    ///ctor
    constructor(props) {
        super(props);

        //--------------
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
class TestListJSX extends React.Component {
    /// ctor
    /// @param props
    constructor(props) {
        super(props);

        //--------------
        this.onchange = this.onchange.bind(this);
        this.onsubmit = this.onsubmit.bind(this);

        //--------------
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
/// desc
class TestAppJSX extends React.Component {

    /// display 'application', contents
    render() {
        return (
            <div>
                <div>
                    <h3>BUTTON</h3>
                    <TestButtonJSX data={document} />

                </div>

                <div>
                    <h3>LIST</h3>
                    <TestListJSX data={document} />

                </div>

            </div>
        );
    }
}

/*************************************************************************************
 * IMPLEMENTATION: SITE STRUCTURE
 *************************************************************************************/
class SiteGridJSX extends React.Component {

    ///ctor
    constructor(props) {
        super(props);

        this.content = props.content;

        this.ENTRY = "blog_entry";
        this.PHOTOID = "PHOTO";
        this.TITLEID = "TITLE";
        this.TITLEDATE = "DATE";
        this.WAITID = "wait";
        this.WAITPROGRID = "progress";
        this.WAITBARID = "bar";
        this.CONTID = "CONTENT";
    }

    componentDidMount() {
        window.onload_();
    }

    componentWillUnmount() {
        window.onunload_();
    }

    listenScrollEvent() {
        window.onscroll_();
    }

    /// display the object content
    render() {

        // 1st parse the map to generate table items
        const entrylist = this.content.map(
            (item, index) => {
                return this.renderitem(item, index);
            }
        );
        // 2nd insert table items
        return (
            <div onScroll={this.listenScrollEvent.bind(this)}>
                { entrylist }
            </div>
        );

    }

    /// display 1 table item
    renderitem(item, index) {

        const entryID = this.ENTRY + item.id;
        const entryPHOTOSRC = "";

        const entryPHOTOID = entryID + this.PHOTOID;
        const entryTITLEID = entryID + this.TITLEID;
        const entryTITLEDATE = entryID + this.TITLEDATE;
        const entryWAITID = entryID + this.WAITID;
        const entryWAITPROGRID = entryID + this.WAITPROGRID;
        const entryWAITBARID = entryID + this.WAITBARID;
        const entryCONTID = entryID + this.CONTID;

        return (
            <div id={ entryID }
                 className="w3-card-4 w3-margin w3-white">
                <img id={ entryPHOTOID }
                     src={ entryPHOTOSRC }
                     alt="img"
                     style={{width:'100%'}} />
                <div className="w3-container">
                    <h3 id={ entryTITLEID }></h3>
                    <h5 id={ entryTITLEDATE }></h5>
                </div>
                <div id={ entryWAITID }>
                    <div id={ entryWAITPROGRID }
                         className="myProgress">
                        <div id={ entryWAITBARID }
                             className="myBar reactentry"
                             style={{width:'100%'}}>Loading...</div>
                    </div>
                </div>
                <div id={ entryCONTID }
                     className="w3-container">
                </div>
            </div>
        );

    }

}

//-----------------------------------------------------------------------------------------------------------
//export default TestAppJSX;

function reactexecuteBLOG(id, content) {
    ReactDOM.render(<SiteGridJSX content={ content } />, document.querySelector(id));
}

function reactexecuteTECH(id) {
    ReactDOM.render(<TestAppJSX />, document.querySelector(id));
}
