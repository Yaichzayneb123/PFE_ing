
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SousOption, Variant } from '../modéle/variant';

@Injectable({
  providedIn: 'root'
})
export class VariantService {

  constructor(private http: HttpClient) { }

 
  saveVariant(sousOptionList: any| undefined, id:any): Observable<Variant> {
    const variantData = { 
      idproduit : id,sousOptionId: sousOptionList! };
    return this.http.post<Variant>('http://localhost:8086/api/v1/variant/save', variantData);
  }

  GetVariantByIdProduit(id: any):Observable<any>{
    return this.http.get<any>(`http://localhost:8086/api/v1/variant/getvariantByIdProduit/` + id)

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
