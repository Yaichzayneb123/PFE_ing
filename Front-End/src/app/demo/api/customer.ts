export interface Country {
    name?: string;
    code?: string;
}

export interface Representative {
    name?: string;
    image?: string;
}

export interface Customer {
    id?: number;
    firstName?: string;
    lastName?: string;
    country?: Country;
    email?: string;
    phone?: string;
    address?: string;
    societe?: number;
    

    // company?: string;
    // date?: string;
    // status?: string;
    // activity?: number;
    //  representative?: Representative;
}
export interface Gesti {
    id?: number;
    firstName?: string;
    lastName?: string;
    country?: Country;
    email?: string;
    phone?: string;
    address?: string;
    
}
