import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Commande, CommandeF } from '../modéle/commande';
import { Observable, forkJoin } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {

  constructor(private http: HttpClient) { }

  addCommande(commande : Commande ){
    console.log("cmdBack",commande)
    return this.http.post<any>('http://localhost:8086/api/v1/commandes/save',commande);
  }
  getCmdbyIdSociete(id: any) {
    return this.http.get<any>(`http://localhost:8086/api/v1/entreprise/commande/` + id)
}
addCommandeFour(commande : CommandeF ){
  console.log("cmdBack",commande)
  return this.http.post<any>('http://localhost:8086/api/v1/commandeFour/save',commande);
}
getCmdFourbyIdSociete(id: any) {
  return this.http.get<any>(`http://localhost:8086/api/v1/entreprise/commandeFour/` + id)
}

 
}
