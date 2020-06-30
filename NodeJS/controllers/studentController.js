const express = require('express');
var router = express.Router();

//To get id from mongoose
var ObjectId = require('mongoose').Types.ObjectId;

var {StudentModel} = require('../models/student');

router.get('/',(req,res)=> {
    StudentModel.find((err,docs)=> {
        if(!err)
        {
            res.send(docs);
        }
        else{
            console.log("Error at retrieving students: " + JSON.stringify(err,undefined,2))
        }
    }) 
});

module.exports = router

