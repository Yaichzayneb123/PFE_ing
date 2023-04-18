
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Variant } from '../api/variant';

@Injectable({
  providedIn: 'root'
})
export class VariantService {

  constructor(private http: HttpClient) { }

  add(variant : Variant ){
    return this.http.post<any>('http://localhost:8086/api/v1/variant/save',variant);
  }

  update(variant : Variant, id: any):Observable<any>{
    return this.http.put<any>(`http://localhost:8086/api/v1/variant/update/` + id, variant );
  }
  delete(id:any){
    return this.http.delete('http://localhost:8086/api/v1/variant/del/' + id );
    }
    getAll() {
      return this.http.get<any>('http://localhost:8086/api/v1/variant/getvariant')
          .toPromise()
          .then(res => res as Variant[])
          // .then(data => data);
  }

}
