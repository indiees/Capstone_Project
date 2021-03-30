const express = require('express');
const pug =  require('pug');
const app = express();
const port = 80;

app.get('/', index);

function index(req,res){
    {

        res.send(pug.renderFile("index.html",{ name: "Mush"}));
    }
} 
app.get('/hello', index2);

function index2(req,res){
    {
        res.send('test')
    }
} 
app.get('/loginRegister', loginregister);

function loginregister(req,res){
    {

        res.send(pug.renderFile("login-register.html"));
    }
} 


app.use("/public",express.static("public"));
app.listen(port, test);

function test(port){
    console.log("everything is running as expected")
}

