const express = require('express');
const app = express();
const bodyParser = require('body-parser')
var port = process.env.PORT || "8000";


app.use(bodyParser.urlencoded({extended: false}));
//app.use(bodyParser.json());

//Making mongodb connection 
const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

    mongoose.connect('mongodb://localhost/Timetable2020');

    mongoose.connection.once('open',function(){
        console.log('Connection has been successfully made');
    }).on('error',function(error) {
        console.log('There is an error ',error);
    });




app.listen(port,()=>{
    console.log('server is running on '+[port]);
})