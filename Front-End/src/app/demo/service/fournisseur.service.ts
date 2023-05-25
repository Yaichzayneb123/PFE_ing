import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../modéle/client';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {

  constructor(private http: HttpClient) { }
  getFourbyIdSociete(id: any) {
    return this.http.get<any>(`http://localhost:8086/api/v1/fourn/societe/` + id)
}
getFourByIdsoc(id:any) {
  return this.http.get<any>(`http://localhost:8086/api/v1/fourn/societe/` + id)
      .toPromise()
      .then(res => res as Client[])
      .then(data => data);
}
addFour(client : Client ){
    return this.http.post<any>('http://localhost:8086/api/v1/fourn/save',client);
  }

  update(client : Client, id: any):Observable<any>{
    return this.http.put<any>(`http://localhost:8086/api/v1/fourn/update/` + id, client);
  }
  delete(id:any){
    return this.http.delete('http://localhost:8086/api/v1/fourn/del/' + id );
    }
    getNbFour() {
      return this.http.get<any>(`http://localhost:8086/api/v1/fourn/count` )
  }
  
}
