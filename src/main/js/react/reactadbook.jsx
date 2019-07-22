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
        this.regcontact = 'addressbook_reg';
        this.editcontact = 'addressbook_edit';
        this.removecontact = 'addressbook_remove';
        this.select = 'result',
        this.message = 'message',
        this.error = 'error',

        this.state = {
            contacts: [],
            index: 0,
            selected: 0,
            status: "none"
        };
    }

    componentDidMount() {
        this.listContact()
    }

    handleClickInList(index, contact) {
        this.setState({index:index, selected:contact.id, status:"edit"})
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

        this.setState({status:"none"})
    }

    regContact(index, contact) {
        this.senddatatobackend(
            this.regcontact + "?vorname=" + contact.vorname + "&nachname=" + contact.nachname + "&email=" + contact.emailadresse,
            this.select,
            (value) => {
                this.setState({contacts: value})
            }
        );

        this.setState({status:"none"})
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

        this.setState({status:"none"})
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

        this.setState({status:"none"})
    }

    getdatafrombackend(request, select, cbk) {
        const local = this;

        axios
            .get(this.address + request)
            .then(
                res => {
                    const jsonStr = JSON.stringify(res.data);
                    const jsonRes = JSON.parse(jsonStr);
                    if(null != jsonRes[this.error]){
                        alert("Server returned error: " + jsonRes[this.error]);
                        return;
                    }

                    if(null != jsonRes[this.message]){
                        alert("Server returned message: " + jsonRes[this.message]);
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
                    if(null != jsonRes[this.error]){
                        alert("Server returned error: " + jsonRes[this.error]);
                        return;
                    }

                    if(null != jsonRes[this.message]){
                        alert("Server returned message: " + jsonRes[this.message]);
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
                             selected={this.state.selected}
                             status={this.state.status}
                             addContact={this.addContact.bind(this)}
                             regContact={this.regContact.bind(this)}
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

    contactSelect(index, contact) {
        this.props.handleToUpdate(index, contact);
    }

    render() {
        const filteredContacts = this.props.contacts.filter(
            (contact) => {
                const fullName = contact.vorname.toLowerCase() + contact.nachname.toLowerCase()
                return contact.enabled && (fullName.indexOf(this.state.search.toLowerCase()) !== -1);
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

class Contact extends React.Component {

    handleListItemClick(event, index) {
        this.props.select(index, this.props.contact)
    }

    render() {
        return (
            <li onClick={event => this.handleListItemClick(event, this.props.index)}>
                <p>
                    {this.props.index} <br />
                    {this.props.contact.id} <br />
                    {this.props.contact.vorname} <br />
                    {this.props.contact.nachname} <br />
                    {this.props.contact.emailadresse} <br />
                    {this.props.contact.enabled}
                </p>
            </li>
        )
    }
}

class ContactEdit extends React.Component {

    componentWillMount() {
        this.none = "none";
        this.edit = "edit";
        this.index = "[DBG] index";
        this.id = "[DBG] id";
        this.vorname = "vorname";
        this.nachname = "nachname";
        this.emailadresse = "emailadresse";
        this.verified = "verified";
        this.ADD = "add";
        this.REG = "reg";
        this.EDIT = "edit";
        this.REMOVE = "remove";

        this.setDefaultContact()
    }

    componentDidMount() {
        if(this.edit == this.props.status){
            this.setActiveContact()
        }
        else {
            this.setDefaultContact()
        }
    }

    componentDidUpdate(prevProps) {
        if ((this.props.status !== prevProps.status) ||
            (this.props.index !== prevProps.index)) {
            this.setActiveContact()
        }
    }

    setDefaultContact() {
        this.setState({
            id: "",
            vorname: "",
            nachname: "",
            emailadresse: "",
            enabled: ""
        });
    }

    setActiveContact() {
        this.setState({
            id: this.props.contacts[this.props.index].id,
            vorname: this.props.contacts[this.props.index].vorname,
            nachname: this.props.contacts[this.props.index].nachname,
            emailadresse: this.props.contacts[this.props.index].emailadresse,
            enabled: this.props.contacts[this.props.index].enabled
        });
    }

    handleChange(data) {
        const state = this.state;
        const name = data.target.name;
        state[name] = data.target.value;
        this.setState(state);
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

        const contact = this.state;
        switch(submitButton.value){
            case this.ADD:
                this.props.addContact(this.props.index, contact)
                break;
            case this.REG:
                this.props.regContact(this.props.index, contact)
                break;
            case this.EDIT:
                this.props.editContact(this.props.index, contact)
                break;
            case this.REMOVE:
                this.props.removeContact(this.props.index, contact)
                break;
        }
    }

    render() {

        return (
            <div className="contact-add">
                <form className="add-form" onSubmit={this.handleSubmit.bind(this)} method="post">
                    <div className="form-field">
                        <label>List index: </label>
                        <input type="text"
                               name={this.index}
                               value={this.props.index}
                        />
                    </div>
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
                    <div className="form-field">
                        <label>Verified: </label>
                        <input type="text"
                               name={this.verified}
                               value={this.state.enabled}
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
            case this.none:
                return (
                    <div>
                        <button name="subject" type="submit" value={this.ADD}>Add</button>
                        <button name="subject" type="submit" value={this.REG}>Register</button>
                    </div>
                )
            case this.edit:
                return (
                    <div>
                        <button name="subject" type="submit" value={this.ADD}>Add</button>
                        <button name="subject" type="submit" value={this.REG}>Register</button>
                        <button name="subject" type="submit" value={this.EDIT}>Save</button>
                        <button name="subject" type="submit" value={this.REMOVE}>Delete</button>
                    </div>
                )
        }
    }
}

module.exports = AdBookApp;