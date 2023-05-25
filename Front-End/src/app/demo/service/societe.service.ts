import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SocieteService {

  constructor(private http: HttpClient) { }
  getSociete () {
    return this.http.get<any>(`http://localhost:8086/api/v1/entreprise/all` )
}
validate( id: number){
  return this.http.get<any>('http://localhost:8086/api/v1/entreprise/validate/' + id);
          
}
}
