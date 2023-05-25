import { Client } from "./client";
import { Product } from "./product";

export interface Commande {
    id?: number;
    idClient?: Client;
    produitList?: Product[];
    idEntreprise?: number;
    montant?: number;
    listProdId?:number [];
    listcmdDto?:number []

}
export interface CommandeF {
    id?: number;
    idFour?: Client;
    produitList?: Product[];
    idEntreprise?: number;
    montant?: number;
    listProdId?:number [];
    listcmdDto?:number []

}
export interface Command {
    id?: number;
    code?: string;
    dateCommande?: string;
    montant?: number;
    idEntreprise?: number;
    idClient?: number;
    //produitList?: Product[];
    listProdId?: number[];
  }