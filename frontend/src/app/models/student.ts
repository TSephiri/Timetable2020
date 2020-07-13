import { Degree } from './degree';

export class Student {
    id: Number;
    student_no: String;
    email: String;
    degree_id: String;
    classes: []

    constructor(id,student_no,email,degree_id,classes){
        this.id = id;
        this.student_no = student_no
        this.email = email
        this.degree_id = degree_id
        this.classes = classes

    }
}