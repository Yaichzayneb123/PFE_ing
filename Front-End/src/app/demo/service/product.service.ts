import { Product } from '../modéle/product';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class ProductService {

  
    apiUrl = 'http://localhost:8086/api/v1/produit';

    constructor(private http: HttpClient) { }

    getProductsSmall() {
        return this.http.get<any>('assets/demo/data/products-small.json')
            .toPromise()
            .then(res => res.data as Product[])
            .then(data => data);
    }

    getProducts() {
        return this.http.get<any>('http://localhost:8086/api/v1/produit/getproduit')
            .toPromise()
            .then(res => res as Product[])
            .then(data => data);
    }
    // updateProducts() {
    //     return this.http.get<any>('http://localhost:8086/api/v1/produit/')
    //         .toPromise()
    //         .then(res => res as Product[])
    //         .then(data => data);
    // }

  
      update( id: any, product: any ):Observable<any>{
        console.log(product);
       
        return this.http.put<any>('http://localhost:8086/api/v1/produit/' + id, product);
      }

      getProductById( id: number){
        return this.http.get<any>('http://localhost:8086/api/v1/produit/get/' + id);
                
    }
    getProdbyIdSociete(id: any) :Observable<any>{
        return this.http.get<any>(`http://localhost:8086/api/v1/depot/produit/` + id)
           
    
    }
    getProdbyIdCategorie(id: any) {
        return this.http.get<any>(`http://localhost:8086/api/v1/produit/` + id)
            .toPromise()
            .then(res => res as Product[])
            .then(data => data);
    
    }
    delete(id:any){
        return this.http.delete('http://localhost:8086/api/v1/produit/del/' + id );
        }


    getProductsMixed() {
        return this.http.get<any>('assets/demo/data/products-mixed.json')
            .toPromise()
            .then(res => res.data as Product[])
            .then(data => data);
    }

    getProductsWithOrdersSmall() {
        return this.http.get<any>('assets/demo/data/products-orders-small.json')
            .toPromise()
            .then(res => res.data as Product[])
            .then(data => data);
    }

    addPro(produit : string,  image: File): Observable<any>{
        const data: FormData = new FormData();
        data.append('produit', produit);
        data.append('image', image);
        return this.http.post<any>(this.apiUrl + "/Add" , data);
      }

      getNbProds() {
        return this.http.get<any>(`http://localhost:8086/api/v1/produit/count` )
    }
}
