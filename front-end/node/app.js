const express = require('express');
const pug =  require('pug');
const app = express();
const port = 80;
const fetch = require ("node-fetch")

app.get('/', index);

function index(req,res){
    {
        res.send(pug.renderFile("views/login-register.pug"));
    }
} 

app.get('/cars', cars);

async function cars(req,res){
    {
        data={};
        //We feed in the URL of the request we want
        url = "http://localhost:7000/api/car/colors"
        await fetch(url)
            .then(response=>response.json() //Always use this line (it just converts from raw HTML into a json object)
            ) 
            .then(
                html=> {
                    //We check if the request succeeded
                    if (html.status=="success"){
                        //If it did then save it into data
                        data['colors']=html.payload
                    }
                    else{
                        //If it didnt work then write the error into the console
                        console.log("Error Detected: ")
                        console.log(html.message)
                    }
                }
            )
        //We feed in the URL of the request we want
        url = "http://localhost:7000/api/car/makes"
        await fetch(url)
            .then(response=>response.json() //Always use this line (it just converts from raw HTML into a json object)
            ) 
            .then(
                html=> {
                    //We check if the request succeeded
                    if (html.status=="success"){
                        //If it did then save it into data
                        data['makes']=html.payload
                    }
                    else{
                        //If it didnt work then write the error into the console
                        console.log("Error Detected: ")
                        console.log(html.message)
                    }
                }
            )
        //We feed in the URL of the request we want
        url = "http://localhost:7000/api/car/locations"
        await fetch(url)
            .then(response=>response.json() //Always use this line (it just converts from raw HTML into a json object)
            ) 
            .then(
                html=> {
                    //We check if the request succeeded
                    if (html.status=="success"){
                        //If it did then save it into data
                        data['locations']=html.payload
                    }
                    else{
                        //If it didnt work then write the error into the console
                        console.log("Error Detected: ")
                        console.log(html.message)
                    }
                }
            )
        url = "http://localhost:7000/api/car/locations"
        await fetch(url)
            .then(response=>response.json() //Always use this line (it just converts from raw HTML into a json object)
            ) 
            .then(
                html=> {
                    //We check if the request succeeded
                    if (html.status=="success"){
                        //If it did then save it into data
                        data['locations']=html.payload
                    }
                    else{
                        //If it didnt work then write the error into the console
                        console.log("Error Detected: ")
                        console.log(html.message)
                    }
                }
            )
        url = "http://localhost:7000/api/car/search"
        await fetch(url)
            .then(response=>response.json() //Always use this line (it just converts from raw HTML into a json object)
            ) 
            .then(
                html=> {
                    //We check if the request succeeded
                    if (html.status=="success"){
                        //If it did then save it into data
                        data['cars']=html.payload
                    }
                    else{
                        //If it didnt work then write the error into the console
                        console.log("Error Detected: ")
                        console.log(html.message)
                    }
                }
            )
            
        res.send(pug.renderFile("views/cars.pug",data=data))
    }
} 


app.get('/bays', bays);

function bays(req,res){
    {
        url = "http://localhost:7000/api/bay/search"
        fetch(url)
            .then(response=>response.json()
            ) 
            .then(
                html=> res.send(pug.renderFile("views/bays.pug", data=html.payload))
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
app.get('*',defaultPage);
function defaultPage(req, res){
    res.send(pug.renderFile("views/cannotbefound.pug"))
}