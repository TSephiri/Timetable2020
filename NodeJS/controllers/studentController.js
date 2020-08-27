const express = require('express');
var router = express.Router();

//To get id from mongoose
var ObjectId = require('mongoose').Types.ObjectId;

var  StudentModel  = require('../models/student');
var  ClassModel  = require('../models/class')

// router.get('/:id', (req, res) => {
//     StudentModel.findOne({StudentNo:req.params.id},(err, docs) => {
//         if (!err) {
//             res.send(docs);
//         } else {
//             console.log("Error at retrieving student: " + JSON.stringify(err, undefined, 2));
//         }
//     })
// });
//
router.get('/:id', (req, res) => {
    var id = req.params.id;
    StudentModel.find({StudentNo: id},(err, docs) => {
        if (!err) {
            res.send(docs);
            console.log("sent student info"+id);
        } else {
            console.log("Error at retrieving students: " + JSON.stringify(err, undefined, 2));
        }
    })
});

router.post('/', (req, res,next) => {
    //var classInfo = getDegreeInfo(req.body.Degree_id);
    //
    ClassModel.find({ degree_id: req.body.Degree_id }).then((deg) => {
        StudentModel.create({
            StudentNo: req.body.StudentNo,
            Password: req.body.Password,
            Email: req.body.Email,
            Degree_id: req.body.Degree_id,
            classes: deg
        }).then((student) => {
            res.send(student);
        })
    }).catch(next)
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


module.exports = router;