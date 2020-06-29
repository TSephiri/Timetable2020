const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const StudentSchema = new Schema({
    StudentNo : String,
    Password : String,
    classes : []
})

const StudentModel = mongoose.model('student',StudentSchema); 
module.exports = StudentModel;