<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import axios from 'axios';
import cors from 'cors';

export default {
  name: 'app',
  ponged: 'Not answered',
  othered: 'Not answered',
  components: {
    HelloWorld
  },
  mounted () {
    const corsOptions = {
      method: 'GET',
      mode: 'no-cors',
      withCredentials: true,
      credentials: 'same-origin',
      origin: 'http://localhost:8081',
      credentials: true,
      crossDomain: true,
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET,OPTIONS',
      }
    };

    const local = this;

    let select1 = "content1";
    axios
      .get('http://localhost:8080/ext_test1', corsOptions)
      .then(
              res => {
                alert("Received Successful response from server!");
                const jsonStr = JSON.stringify(res.data);
                const jsonRes = JSON.parse(jsonStr);

                let text = "";
                let value = local.parse(text, jsonRes[select1]);
                alert(value);
              },
              err => {
                alert("Server rejected response with: " + err);
              }
      );

    let select2 = "data_CVtitle";
    axios
      .get('http://localhost:8080/ext_test2', corsOptions)
      .then(
              res => {
                alert("Received Successful response from server!");
                const jsonStr = JSON.stringify(res.data);
                const jsonRes = JSON.parse(jsonStr);

                let text = "";
                let value = local.parse(text, jsonRes[select2]);
                alert(value);
              },
              err => {
                alert("Server rejected response with: " + err);
              }
      );
  },
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

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
