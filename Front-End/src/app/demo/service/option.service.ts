import { SousOption } from './../modéle/option';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OptionService {
  apiUrl = 'http://localhost:8086/api/v1/option/';

  constructor(private http: HttpClient) { }

  addOption(option: any){
    return this.http.post<any>('http://localhost:8086/api/v1/option/save',option);
  }

  getOptionById(id: any) :Observable<any> {
    return this.http.get<any>('http://localhost:8086/api/v1/option/get/'+ id)
       
}
getAllOption() :Observable<any> {
  return this.http.get<any>('http://localhost:8086/api/v1/option/all')
     
}
}
