'use strict';

/*************************************************************************************
 * REQUIRES
 *************************************************************************************/
const React = require('react');
const ReactDOM = require('react-dom');

const ReactBStrap = require('react-bootstrap');
const ReactSNav = require('react-sidenav');

/*************************************************************************************
 * IMPORTS
 *************************************************************************************/
import ReactBaseJSX from '../reactbase.jsx';

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/

export class ReactDisclosable extends ReactBaseJSX {

    /**
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

export class ReactTabcontainer extends ReactBaseJSX {

    /**
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

    render() {
        const { open } = this.state;
        return (
            <ReactBStrap.TabContainer id="list-group-tabs-example" defaultActiveKey="#link1">

                { this.renderOneRow()}

            </ReactBStrap.TabContainer>

        );
    }

    /**
     * @param ids
     */
    renderOneRow(ids) {
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

export class ReactNavigation extends ReactBaseJSX {

    /**
     * @type {{selectedPath: string}}
     */
    state = { selectedPath: '' };

    /**
     * @param arg
     */
    onItemSelection = (arg) => {
        this.setState({ selectedPath: arg.path })
    };

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
