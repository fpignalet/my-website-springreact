<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
    import HelloWorld from './components/HelloWorld.vue'
    import axios from 'axios';

    class Tests {

}

export default {
  name: 'app',
  components: {
    HelloWorld
  },
  methods: {
    /**
     *
     */
    httpext1() {
      this.getdatafrombackend("httpext1", "content1", (value) => {
        alert("Received Successful response from server: " + value);
        HelloWorld.test1 = value
      });
    },
    /**
     *
     */
    httpext2() {
      this.getdatafrombackend("httpext2", "data_CVtitle", (value) => {
        alert("Received Successful response from server: " + value);
        HelloWorld.test2 = value
      });
    },
    /**
     *
     */
    getdatafrombackend(path, select, cbk) {
      const corsOptions = {
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': 'http://localhost:8081',
          'Access-Control-Allow-Methods': 'GET,OPTIONS',
        }
      };

      const local = this;

      this.value = "";

      axios
          .get("http://localhost:8080/" + path, corsOptions)
          .then(
                  res => {
                    const jsonStr = JSON.stringify(res.data);
                    const jsonRes = JSON.parse(jsonStr);
                    let text = "";
                    let value = local.parse(text, jsonRes[select]);

                    cbk(value);
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
  },
  mounted() {
    this.httpext1();
    this.httpext2();
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
