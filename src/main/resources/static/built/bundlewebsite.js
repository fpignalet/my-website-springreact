!function(t){var e={};function n(r){if(e[r])return e[r].exports;var o=e[r]={i:r,l:!1,exports:{}};return t[r].call(o.exports,o,o.exports,n),o.l=!0,o.exports}n.m=t,n.c=e,n.d=function(t,e,r){n.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:r})},n.r=function(t){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},n.t=function(t,e){if(1&e&&(t=n(t)),8&e)return t;if(4&e&&"object"==typeof t&&t&&t.__esModule)return t;var r=Object.create(null);if(n.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var o in t)n.d(r,o,function(e){return t[e]}.bind(null,o));return r},n.n=function(t){var e=t&&t.__esModule?function(){return t.default}:function(){return t};return n.d(e,"a",e),e},n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},n.p="",n(n.s=0)}([function(t,e,n){"use strict";class r{httpGetRAW(t,e,n){const r=new XMLHttpRequest;r.onreadystatechange=function(){4===this.readyState&&200===this.status&&(null!=n?n(this.responseText):(console.log(this.responseText),alert(this.responseText)))},r.open("GET",t+"?param="+e,!0),r.send()}httpGetJSON(t,e,n,r){const o=this,s=new XMLHttpRequest;s.onreadystatechange=function(){if(4===this.readyState&&200===this.status){const e=JSON.parse(this.responseText);let s="";if(null==n)for(var t in e){const n=o.parse(s,e[t]);null!=r?r(n):(console.log(t+": "+e[t]),alert(n))}else{const a=o.parse(s,e[n]);null!=r?r(a):(console.log(t+": "+e[t]),alert(a))}}},s.open("GET",t+"?param="+e,!0),s.send()}parse(t,e){const n=this;let r=t;return"[object Array]"===Object.prototype.toString.call(e)?e.forEach((t,e)=>{r=n.parse(r,t)}):"[object Map]"===Object.prototype.toString.call(e)?Object.keys(e).forEach((t,o)=>{r=n.parse(r,t+": "+e[t])}):"[object Object]"===Object.prototype.toString.call(e)?Object.keys(e).forEach((t,o)=>{r=n.parse(r,t+": "+e[t])}):r=t+e+"\r\n",r}}window.guirequestfrommain=()=>{(new r).httpGetRAW("guirequestfrommain","here is CONTENT MAIN",t=>{console.log(t)})},window.guirequestfromreact=()=>{(new r).httpGetRAW("guirequestfromreact","here is REACT PAGE")},window.testhttpgetfromparam=()=>{(new r).httpGetJSON("testhttpgetfromparam","MAIN ENTRY","item1")},window.testpopulatedb=()=>{(new r).httpGetJSON("testpopulatedb",null,"DBConteners")},window.exthttpgetjson1=()=>{(new r).httpGetJSON("exthttpgetjson1",null,"DBConteners",t=>{console.log(t);const e=document.createElement("li");e.innerText=t,document.getElementById("dyntest").appendChild(e)})}}]);