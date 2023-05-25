import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GestionnaireService {

  constructor(private http: HttpClient) { 
  }
  
  getGestbyId (id: any) {
    return this.http.get<any>(`http://localhost:8086/api/v1/gestionnaire/get/` + id);
}
getNbGests() {
  return this.http.get<any>(`http://localhost:8086/api/v1/gestionnaire/count` )
}
}
