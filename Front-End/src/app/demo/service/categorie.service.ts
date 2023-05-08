import { Categorie } from '../modéle/categorie';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private http: HttpClient,) { }

  getAllCategorie() {
    return this.http.get<any>('http://localhost:8086/api/v1/cat/getAll')
        .toPromise()
        .then(res => res as Categorie[])
        // .then(data => data);
}
getById(id:any) {
  return this.http.get('http://localhost:8086/api/v1/cat/get/'+ id)
  .toPromise()
  .then(res => res as Categorie)
}

addCategorie(categorie : Categorie ){
    return this.http.post<any>('http://localhost:8086/api/v1/cat/save',categorie);
  }

  update(categorie  : Categorie, id: any):Observable<any>{
    return this.http.put<any>(`http://localhost:8086/api/v1/cat/` + id, categorie);
  }
  delete(id:any){
    return this.http.delete('http://localhost:8086/api/v1/cat/del/' + id );
    }
  
  
}
