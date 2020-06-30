const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const StudentSchema = new Schema({
    StudentNo : String,
    Password : String,
    classes : []
})

const StudentModel = mongoose.model('students',StudentSchema); 
module.exports = StudentModel;