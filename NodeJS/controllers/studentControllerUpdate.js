const express = require('express');
var router = express.Router();

var StudentModel = require('../models/student');
var ClassModel = require('../models/class')

//**************** */
//**************** */

router.put('/:id', (req, res, next) => {
    //find student
    var newInfo = {
        degree_id: req.body.Degree_id,
        module: req.body.module,
        day: req.body.day,
        time: req.body.time,
        venue: req.body.venue
    }

    StudentModel.findOne({ StudentNo: req.params.id }, (err, docs) => {
        console.log(docs._id);
        var id = docs._id;
        StudentModel.findOne({
            _id: id, 'classes.time': req.body.time,
            'classes.day': req.body.day
        }, (err, docs) => {
            if (docs) {
                StudentModel.findOneAndUpdate({
                    _id: id, 'classes.time': req.body.time,
                    'classes.day': req.body.day
                }, { 'classes.$': newInfo }
                    , (error, docs) => {
                        if (!error) {
                            res.send("Updated student");
                            console.log(docs);
                        } else {
                            res.send(error);
                        }
                    }).catch(next)
            } else {
                StudentModel.updateOne({ _id: id }, { '$push': { 'classes': newInfo } },
                    (error, docs) => {
                        if (!error) {
                            res.send("Updated student");
                            console.log(docs);
                        } else {
                            console.log(error);
                            res.send(error);
                        }
                    })
            }
        })
    })
})


module.exports = router;