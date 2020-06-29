const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ClassSchema = new Schema({
    Module_Code : String,
    Lecture_time : Date,
    Venue : String,
})

const ClassModel = mongoose.model('class',ClassSchema);

module.exports = ClassModel;