import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../modéle/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }
  getClientbyIdSociete(id: any) {
    return this.http.get<any>(`http://localhost:8086/api/v1/client/societe/` + id)
}
getCltByIdsoc(id:any) {
  return this.http.get<any>(`http://localhost:8086/api/v1/client/societe/` + id)
      .toPromise()
      .then(res => res as Client[])
      .then(data => data);
}
addClient(client : Client ){
    return this.http.post<any>('http://localhost:8086/api/v1/client/save',client);
  }

  update(client : Client, id: any):Observable<any>{
    return this.http.put<any>(`http://localhost:8086/api/v1/client/update/` + id, client);
  }
  delete(id:any){
    return this.http.delete('http://localhost:8086/api/v1/client/del/' + id );
    }
    getNbClients() {
      return this.http.get<any>(`http://localhost:8086/api/v1/client/count` )
  }
  
}
