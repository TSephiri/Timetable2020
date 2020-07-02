const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ClassSchema = new Schema({
    degree_id: String,
    module: String,
    day: String,
    time: String,
    venue: String
})

const ClassModel = mongoose.model('class', ClassSchema);

module.exports = ClassModel;