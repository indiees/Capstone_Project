const express = require('express');
const pug =  require('pug');
const app = express();
const port = 80;

app.get('/', index);

function index(req,res){
    {
        res.send(pug.renderFile("login-register.html"));
    }
} 

app.get('/bookings', bookings);

function bookings(req,res){
    {
        res.send(pug.renderFile("bookings.html"));
    }
} 

app.use("/scripts",express.static("scripts"));
app.use("/style",express.static("style"));
app.use(express.static("public"));
app.listen(port, startServer);
function startServer(port){
    console.log("Server is running")
}
