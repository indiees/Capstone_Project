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


app.get('/bays', bays);

function bays(req,res){
    {
        data = [
            { 
                "bay_id": 1,
                "location": "melbourne",
                "max_capacity": 15
            },
            { 
                "bay_id": 2,
                "location": "oakleigh",
                "max_capacity": 20
            }
        ]
        res.send(pug.renderFile("bays.html", data=data));
    }
} 

app.use("/scripts",express.static("scripts"));
app.use("/style",express.static("style"));
app.use(express.static("public"));
app.listen(port, startServer);
function startServer(port){
    console.log("Server is running")
}
