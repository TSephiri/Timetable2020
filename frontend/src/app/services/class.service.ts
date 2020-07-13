import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'


const apiUrl = " http://localhost:3000/api/class"

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  constructor(private http: HttpClient) { }

  getClasses(){
    return this.http.get(apiUrl);
    
  }
}
