const express = require('express');
var router = express.Router();

//To get id from mongoose
var ObjectId = require('mongoose').Types.ObjectId;

const ClassModel  = require('../models/class');

router.get('/', (req, res) => {
    ClassModel.find((err, docs) => {
        if (!err) {
            res.send(docs);
        } else {
            console.log("Error at retrieving students: " + JSON.stringify(err, undefined, 2))
        }
    })
});

router.post('/', (req, res,next) => {
    console.log(req.body);
    ClassModel.create({ degree_id: req.body.degree_id, module: req.body.module, day: req.body.day, time: req.body.time, venue: req.body.venue }).then((cls) => {
        console.log(req.params);
        res.send(cls);
    }).catch(next);
});

// //get using student id
// router.get('/:id', (req, res) => {

//     var id = req.params.id;
//     ClassModel.find({id},(err, docs) => {
//         if (!err) {
//             res.send(docs);
//         } else {
//             console.log("Error at retrieving students: " + JSON.stringify(err, undefined, 2))
//         }
//     })
// });

//Update
router.put('/:id', (req, res) => {
    //check if given class id exists
    if (!ObjectId.isValid(req.params.id)) {
        return res.status(400).send(`No record with given id: ${req.params.id}`);
    }

    var cls = {
        Module_Code: req.body.Module_Code,
        Lecture_time: req.body.Lecture_time,
        Venue: req.body.Venue
    };
    ClassModel.findByIdAndUpdate(req.params.id, { $set: cls }, { new: true }, (err, docs) => {
        if (!err) {
            res.send(docs)
        } else {
            console.log("Error at upadting class: " + JSON.stringify(err, undefined, 2))
        }
    })
});

//Delete
router.delete('/:id', (req, res) => {
    //check if given class id exists
    if (!ObjectId.isValid(req.params.id)) {
        return res.status(400).send(`No record with given id: ${req.params.id}`);
    }

    ClassModel.findByIdAndRemove(req.params.id, (err, docs) => {
        if (!err) {
            res.send(docs)
        } else {
            console.log("Error at deleting class: " + JSON.stringify(err, undefined, 2))
        }
    })
});

module.exports = router;