const express = require('express');
const bodyParser = require('body-parser');

const mongoose = require('mongoose');

var app = express();

//app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));

var studentsController = require('./controllers/studentController');
var classController = require('./controllers/classController')




//Mongoose
const db = require('./config/keys').MongoURI;

//Connect Mongo
mongoose.connect(db,{useNewUrlParser: true}).then(() => console.log('MongoDb connected'))
.catch(err => console.log(err));



app.use('/api/students',studentsController);
app.use('/api/class',classController);



const PORT = process.env.PORT || 3000;

app.listen(PORT,console.log(`Server started at port: ${PORT}`));

