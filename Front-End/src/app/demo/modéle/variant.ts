import { type } from "os";

export interface Variant {
    id?: number;
    sousOptionList: SousOption[];
    idproduit:any;
       
}
export interface SousOption {
    id: number;
    name: string;
  }