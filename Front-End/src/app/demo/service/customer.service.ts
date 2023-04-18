import { Gesti} from './../api/customer';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../api/customer';
import { Observable } from 'rxjs';

@Injectable()
export class CustomerService {

    constructor(private http: HttpClient) { }

    getGest() {
        return this.http.get<any>('http://localhost:8086/api/v1/gestionnaire/getgest')
            .toPromise()
            .then(res => res as Customer[])
            // .then(data => data);
    }

    getGestbyIdSociete(id: any) {
        return this.http.get<any>(`http://localhost:8086/api/v1/entreprise/gest/` + id);
            // .toPromise()
            // .then(res => res as Customer[])
    
    }


    addGest(gesti : Gesti ){
        return this.http.post<any>('http://localhost:8086/api/v1/gestionnaire/save',gesti);
      }

      update(gestion : Gesti, id: any):Observable<any>{
        return this.http.put<any>(`http://localhost:8086/api/v1/gestionnaire/` + id, gestion);
      }
      delete(id:any){
        return this.http.delete('http://localhost:8086/api/v1/gestionnaire/id/' + id );
        }
      
      


    getCustomersSmall() {
        return this.http.get<any>('assets/demo/data/customers-small.json')
            .toPromise()
            .then(res => res.data as Customer[])
            .then(data => data);
    }

    getCustomersMedium() {
        return this.http.get<any>('assets/demo/data/customers-medium.json')
            .toPromise()
            .then(res => res.data as Customer[])
            .then(data => data);
    }

    getCustomersLarge() {
        return this.http.get<any>('assets/demo/data/customers-large.json')
            .toPromise()
            .then(res => res.data as Customer[])
            .then(data => data);
    }
   
}
