import { Categorie } from "./categorie";

interface InventoryStatus {
    label: string;
    value: string;
}
export interface Product {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
    price?: number;
    quantity?: number;
    quantityCmd?:number;
    inventoryStatus?: InventoryStatus;
    category?: number;
    categoryName?: string;
    cat?:Categorie;
    image?: string;
    // rating?: number;
    societe?: number;
    depotId?:number;
    
}