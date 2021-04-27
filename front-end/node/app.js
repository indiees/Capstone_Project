const express = require('express');
const pug =  require('pug');
const app = express();
const port = 80;
const fetch = require ("node-fetch")

app.get('/', index);

function index(req,res){
    {
        res.send(pug.renderFile("login-register.html"));
    }
} 

app.get('/cars', cars);

function cars(req,res){
    {
        res.send(pug.renderFile("cars.html"));
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
        response=[]
        url = "http://localhost:7000/api/bay/search"
        fetch(url)
            .then(response=>response.json()
            
            ) .then(
                html=> res.send(pug.renderFile("bays.html", data=html.payload))
            )
            
       
    }
} 

app.use("/scripts",express.static("scripts"));
app.use("/style",express.static("style"));
app.use(express.static("public"));
app.listen(port, startServer);
function startServer(port){
    console.log("Server is running")
}
