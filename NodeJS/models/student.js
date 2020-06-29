const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const StudentSchema = new Schema({
    studentNo : String,
    password : String,
    classes : []
})

const Student = mongoose.model('student',StudentSchema); 
module.exports = Student;