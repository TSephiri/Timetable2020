const express = require('express');
var router = express.Router();

//To get id from mongoose
var ObjectId = require('mongoose').Types.ObjectId;

var  StudentModel  = require('../models/student');
var  ClassModel  = require('../models/class')

router.get('/', (req, res) => {
    StudentModel.find((err, docs) => {
        if (!err) {
            res.send(docs);
        } else {
            console.log("Error at retrieving students: " + JSON.stringify(err, undefined, 2));
        }
    })
});

router.post('/', (req, res) => {
    StudentModel.create({
        StudentNo: req.body.StudentNo,
        Password: req.body.Password,
        Email: req.body.Email,
        Degree_id: req.body.Degree_id,
        classes: getDegreeInfo(req.body.Degree_id)
    }).then((student) => {
        res.send(student);
    })
})

router.put('/:id', (req, res) => {
    //check if given class id exists
    if (!ObjectId.isValid(req.params.id)) {
        return res.status(400).send(`No record with given id: ${req.params.id}`);
    }

    var student = {
        StudentNo: req.body.StudentNo,
        Password: req.body.Password,
        Email: req.body.Email,
        Degree_id: req.body.Degree_id,

    };
    StudentModel.findByIdAndUpdate(req.params.id, { $set: student }, { new: true }, (err, docs) => {
        if (!err) {
            res.send(docs)
        } else {
            console.log("Error at upadting student: " + JSON.stringify(err, undefined, 2))
        }
    })

})

//Delete
router.delete('/:id', (req, res) => {
    //check if given class id exists
    if (!ObjectId.isValid(req.params.id)) {
        return res.status(400).send(`No record with given id: ${req.params.id}`);
    }

    StudentModel.findByIdAndRemove(req.params.id, (err, docs) => {
        if (!err) {
            res.send(docs)
        } else {
            console.log("Error at deleting student: " + JSON.stringify(err, undefined, 2))
        }
    })
});

var getDegreeInfo = function(degree) {
    ClassModel.find({ degree_id: degree }).then((deg) => {
        console.log(deg);
        return deg;
    })
}

module.exports = router;