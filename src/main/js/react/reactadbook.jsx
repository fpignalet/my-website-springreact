/*************************************************************************************
 * INCLUDES
 *************************************************************************************/
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

const axios = require('axios');

/*************************************************************************************
 * IMPLEMENTATION
 *************************************************************************************/

class AdBookApp extends React.Component {

    constructor() {
        super();

        this.title = "Address Book"

        this.address = 'http://localhost:8080/';
        this.requestlist = 'addressbook_list';
        this.addcontact = 'addressbook_add';
        this.editcontact = 'addressbook_edit';
        this.removecontact = 'addressbook_remove';
        this.select = 'result',

        this.state = {
            contacts: [],
            index: 0,
            status: "none"
        };
    }

    componentDidMount() {
        this.listContact()
    }

    handleClickInList(index) {
        this.setState({index:index, status:"edit"})
    }

    listContact(contact) {
        this.getdatafrombackend(
            this.requestlist,
            this.select,
            (value) => {
                this.setState({contacts: value})
            }
        );
    }

    addContact(index, contact) {
        this.senddatatobackend(
            this.addcontact + "?vorname=" + contact.vorname + "&nachname=" + contact.nachname + "&email=" + contact.emailadresse,
            this.select,
            (value) => {
                this.setState({contacts: value})
            }
        );
    }

    editContact(index, contact) {
        this.senddatatobackend(
            this.editcontact +
                "?id=" + this.state.contacts[index].id +
                "&cur_vorname=" + this.state.contacts[index].vorname + "&cur_nachname=" + this.state.contacts[index].nachname +
                "&new_vorname=" + contact.vorname + "&new_nachname=" + contact.nachname + "&email=" + contact.emailadresse,
            this.select,
            (value) => {
                this.setState({contacts: value})
            }
        );
    }

    removeContact(index, contact) {
        this.senddatatobackend(
            this.removecontact +
            "?id=" + this.state.contacts[index].id +
            "&vorname=" + contact.vorname + "&nachname=" + contact.nachname,
            this.select,
            (value) => {
                this.setState({contacts: value})
            }
        );
    }

    getdatafrombackend(request, select, cbk) {
        const local = this;

        axios
            .get(this.address + request)
            .then(
                res => {
                    const jsonStr = JSON.stringify(res.data);
                    const jsonRes = JSON.parse(jsonStr);
                    if(null != jsonRes["error"]){
                        alert("Server returned error: " + jsonRes["error"]);
                        return;
                    }

                    cbk(jsonRes[select]);
                },
                err => {
                    alert("Server rejected request with: " + err);
                }
            );
    }

    senddatatobackend(request, select, cbk) {
        const local = this;

        axios
            .post(this.address + request)
            .then(
                res => {
                    const jsonStr = JSON.stringify(res.data);
                    const jsonRes = JSON.parse(jsonStr);
                    if(null != jsonRes["error"]){
                        alert("Server returned error: " + jsonRes["error"]);
                        return;
                    }

                    cbk(jsonRes[select]);
                },
                err => {
                    alert("Server rejected request with: " + err);
                }
            );
    }

    render() {
        return (
            <div>
                <h1>{this.title}</h1>
                <ContactList contacts={this.state.contacts}
                             handleToUpdate={this.handleClickInList.bind(this)}
                />
                <ContactEdit contacts={this.state.contacts}
                             index={this.state.index}
                             status={this.state.status}
                             addContact={this.addContact.bind(this)}
                             editContact={this.editContact.bind(this)}
                             removeContact={this.removeContact.bind(this)}
                />
            </div>
        )
    }

}

class ContactList extends React.Component {

    constructor() {
        super();

        this.prompt = "Enter name or lastname";

        this.state = {
            search: ''
        }
    }

    updateSearch(event) {
        this.setState({
            search: event.target.value
        })
    }

    contactSelect(index) {
        this.props.handleToUpdate(index);
    }

    render() {
        const filteredContacts = this.props.contacts.filter(
            (contact) => {
                const fullName = contact.vorname.toLowerCase() + contact.nachname.toLowerCase()
                return fullName.indexOf(this.state.search.toLowerCase()) !== -1;
            }
        );

        return (
            <div className="people-list">
                <div className="search">
                    <input type="text"
                           className="search_input"
                           value={this.state.search}
                           onChange={this.updateSearch.bind(this)}
                           placeholder={this.prompt}
                    />
                </div>
                <ul className="list">
                    {
                        filteredContacts.map((contact, index)=> {
                            return <Contact contact={contact}
                                            index={index}
                                            select={this.contactSelect.bind(this)}
                            />
                        })
                    }
                </ul>
            </div>

        )
    }

}

class ContactEdit extends React.Component {

    componentWillMount() {
        this.id = "id";
        this.vorname = "vorname";
        this.nachname = "nachname";
        this.emailadresse = "emailadresse";
        this.ADD = "add";
        this.EDIT = "edit";
        this.REMOVE = "remove";

        this.setState({
            id: "",
            vorname: "",
            nachname: "",
            emailadresse: ""
        });
    }

    componentDidMount() {
        if("edit" == this.props.status){
            this.reloadProps()
        }
        else {
            this.setState({
                id: "",
                vorname: "",
                nachname: "",
                emailadresse: ""
            });
        }
    }

    componentDidUpdate(prevProps) {
        if ((this.props.status !== prevProps.status) ||
            (this.props.index !== prevProps.index)) {
            this.reloadProps()
        }
    }

    reloadProps() {
        this.setState({
            id: this.props.contacts[this.props.index].id,
            vorname: this.props.contacts[this.props.index].vorname,
            nachname: this.props.contacts[this.props.index].nachname,
            emailadresse: this.props.contacts[this.props.index].emailadresse
        });
    }

    handleSubmit(e) {
        e.preventDefault();

        let submitButton;
        if(typeof e.explicitOriginalTarget != 'undefined'){  //
            submitButton = e.explicitOriginalTarget;
        }
        else if(typeof document.activeElement.value != 'undefined'){  // IE
            submitButton = document.activeElement;
        }

        switch(submitButton.value){
            case this.ADD:
                this.props.addContact(this.props.index, this.state)
                break;
            case this.EDIT:
                this.props.editContact(this.props.index, this.state)
                break;
            case this.REMOVE:
                this.props.removeContact(this.props.index, this.state)
                break;
        }
    }

    handleChange(data) {
        const state = this.state;
        const name = data.target.name;
        state[name] = data.target.value;
        this.setState(state);
    }

    render() {

        return (
            <div className="contact-add">
                <form className="add-form" onSubmit={this.handleSubmit.bind(this)} method="post">
                    <div className="form-field">
                        <label>DB Id: </label>
                        <input type="text"
                               name={this.id}
                               value={this.state.id}
                        />
                    </div>
                    <div className="form-field">
                        <label>Vorname: </label>
                        <input type="text"
                               name={this.vorname}
                               value={this.state.vorname}
                               onChange={this.handleChange.bind(this)}
                        />
                    </div>
                    <div className="form-field">
                        <label>Nachname: </label>
                        <input type="text"
                               name={this.nachname}
                               value={this.state.nachname}
                               onChange={this.handleChange.bind(this)}
                        />
                    </div>
                    <div className="form-field">
                        <label>Email: </label>
                        <input type="text"
                               name={this.emailadresse}
                               value={this.state.emailadresse}
                               onChange={this.handleChange.bind(this)}
                        />
                    </div>
                    { this.renderButtons() }
                </form>
            </div>
        )
    }

    renderButtons() {
        switch(this.props.status){
            case "none":
                return (
                    <button name="subject" type="submit" value={this.ADD}>Add</button>
                )
            case "edit":
                return (
                    <div>
                        <button name="subject" type="submit" value={this.ADD}>Add</button>
                        <button name="subject" type="submit" value={this.EDIT}>Save</button>
                        <button name="subject" type="submit" value={this.REMOVE}>Delete</button>
                    </div>
                )
        }
    }
}

class Contact extends React.Component {

    handleListItemClick(event, index) {
        this.props.select(index)
    }

    render() {
        return (
            <li onClick={event => this.handleListItemClick(event, this.props.index)}>
                <p>
                    {this.props.contact.id} <br />
                    {this.props.contact.vorname} <br />
                    {this.props.contact.nachname} <br />
                    {this.props.contact.emailadresse}
                </p>
            </li>
        )
    }
}

module.exports = AdBookApp;