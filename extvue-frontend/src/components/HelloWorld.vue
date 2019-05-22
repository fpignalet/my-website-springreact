<!--<link rel="stylesheet" type="text/css" href="../css/App.css">-->

<template>
  <div className="hello">
    <header className="App-header">
      <h1 className="App-title">EXTVUE-FRONTEND</h1>
    </header>
    <div className="App-intro">
      <div>
        <button v-on:click="exthttpgetjson0()">exthttpgetjson0</button>
        <div>exthttpgetjson0: {{test1}}</div>
      </div>
      <div>
        <button v-on:click="exthttpgetjson1()">exthttpgetjson1</button>
        <div>exthttpgetjson1: {{test2}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: [
    'test1',
    'test2'
  ]
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
