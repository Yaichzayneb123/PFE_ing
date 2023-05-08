import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Depot } from '../modéle/depot';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepotService {

  constructor(private http: HttpClient) { }

  addDepot(depot : Depot ){
    return this.http.post<any>('http://localhost:8086/api/v1/depot/save',depot);
  }
  getDepotbyIdSociete(id: any) {
    return this.http.get<any>(`http://localhost:8086/api/v1/entreprise/depot/` + id);
        // .toPromise()
        // .then(res => res as Customer[])

}
delete(id:any){
  return this.http.delete('http://localhost:8086/api/v1/depot/del/' + id );
}
update(depot : Depot, id: any):Observable<any>{
  return this.http.put<any>(`http://localhost:8086/api/v1/depot/update/` + id, depot);
}


  
}
