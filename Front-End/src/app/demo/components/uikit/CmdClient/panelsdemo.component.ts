import { CommandeProduit } from '../../../modéle/commandeProduit';
import { CommandeService } from '../../../service/commande.service';
import { Commande } from '../../../modéle/commande';
import { ClientService } from 'src/app/demo/service/client.service';
import { Component, OnInit } from '@angular/core';
import { MenuItem, MessageService } from 'primeng/api';
import { Client } from 'src/app/demo/modéle/client';
import { Product } from 'src/app/demo/modéle/product';
import { ProductService } from 'src/app/demo/service/product.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { Observable } from 'rxjs';


@Component({
    templateUrl: './panelsdemo.component.html',
    providers: [MessageService]
})
export class PanelsDemoComponent implements OnInit {
  newProduct:any
  total:any
  list:any
    commande: any = {
        produitList: []
      };
      command: any={};

    items: MenuItem[] = [];

    cardMenu: MenuItem[] = [];
    maxQte: any;

    filteredCountries: any[] = [];
    selectedCountryAdvanced?: Product;
    selectedClient?: any;
    allClient:any[] = [];
    clients:Client[] = [];
   // productss: { name: string, quantity: number, price: number, idDepot:number}[] = [];

    products: Product[] = [];
    selectedProduct: Product[] = [{}];
    currentUser: any;
    quantity: number=0;
    totalAllProducts: number = 0;

    selectedProductList: any[] = [{}];
    dropDownProduct: any;
    constructor(private productService: ProductService,private  Service: LayoutService,private messageService: MessageService,private commandeService:CommandeService,private clientService:ClientService ){}

    async ngOnInit() {

        this.currentUser=this.Service.getDataFromToken();
        console.log(this.currentUser.id);
        //this.productService.getProducts().then(products => this.products = products);
        this.products = await this.productService.getProducts();
        console.log(this.products);
        this.clients = await this.clientService.getCltByIdsoc(this.currentUser.id);
        console.log(this.clients);
        this.dropDownProduct={ 
          name: "",
          quantity:0,
          price: 0,
        };

    }
   

    filterCountry(event: any) {
        const filtered: any[] = [];
        const query = event.query;
        for (let i = 0; i < this.products.length; i++) {
            const country = this.products[i];
            if (country.name?.toLowerCase().indexOf(query.toLowerCase()) == 0) {
                filtered.push(country);
            }
        }
        this.filteredCountries = filtered;
    }
    filterClient(event: any) {
        const filtered: any[] = [];
        const query = event.query;
        for (let i = 0; i < this.clients.length; i++) {
            const country = this.clients[i];
            if (country.email?.toLowerCase().indexOf(query.toLowerCase()) == 0) {
                filtered.push(country);
            }
        }
        this.allClient = filtered;
    }
  

    calculeTotal(product:any,i:any){
      console.log("calcule",product);
      console.log("index",this.selectedProductList[i]);
      this.totalAllProducts = 0;
      const  p = this.selectedProductList[i] ;
      this.selectedProductList[i].total = p.quantityCmd* p.name?.price

for (let p of this.selectedProductList) {
  p.total = p.quantityCmd * (p.name ? p.name.price : 0);
  this.totalAllProducts += p.total;
  
}

console.log(this.totalAllProducts)
    }


    // addProduct() {
       
    //       const newProduct = { 
    //         name: "",
    //         quantity :0,
    //         price: 0,
    //         total: 0
    //       };
    //       this.commande?.produitList?.push(newProduct);
    //       this.selectedProductList.push(newProduct);
    //       this.selectedCountryAdvanced={}; // Réinitialise la sélection du pays
    //       this.quantity = 0; // Réinitialise la quantité
    //       console.log(this.selectedProductList +"rrrrrrrrrrrrr");
    //  this.selectedProductList.forEach(element => {
    //  console.log( element.name.id+"eeeeqqq")
    // this.list = this.products.filter((Produit)=>{ Produit.id = element.name.id}) 
    // console.log(this.list)
    //  });
        
    //   }
      addProduct() {
        const newProduct = { 
          name: this.dropDownProduct.name,
          quantity: this.quantity,
          price: this.dropDownProduct.price,
          total: 0
        };
        this.commande.produitList.push(newProduct);
        this.selectedProductList.push(newProduct);
        this.selectedCountryAdvanced = {}; // Réinitialise la sélection 
        this.quantity = 0; // Réinitialise la quantité
      }
      
      getMaxQuantity(product: any): void {
        if (product && product.quantity) {
          this.maxQte=product.quantity;
        } else {
          this.maxQte = 0;

        }
      }

      onSelect(event: any) :any{
        console.log(event.price + "event")
        this.newProduct = { 
          name: event.name,
          quantity: event.quantity,
          price: event.price,
        };
        this.getMaxQuantity(this.newProduct)
        this.dropDownProduct=this.newProduct;
        // console.log( this.dropDownProduct.price)

      }

      saveCommande() {
        this.commande.idEntreprise = this.currentUser.id;
        console.log(this.commande.idEntreprise);

   
  const selectedProductIds = this.selectedProductList.map((product: any) => {
    return {
      idProd: product.name.id,
      quantityCmd: product.quantityCmd // Récupérer l'ID du produit et la quantité
    };
  });

  this.commande.listcmdDto = selectedProductIds;

  

      
        const commande: Commande = {
          idClient: this.selectedClient?.id,
          produitList: [],
          idEntreprise: this.commande.idEntreprise,
          montant: this.totalAllProducts,
          listProdId: selectedProductIds.map((product: any) => product.idProd),
          listcmdDto: selectedProductIds.map((product: any) => product)
        
        };
      console.log(commande);
        this.commandeService.addCommande(commande).subscribe(
          (res) => {
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Command Created',
              life: 3000
            });
            this.ngOnInit();
          },
          (err) => {
            console.log(err);
          }
        );
      }
      
      
      
      
      

      
      
    
}
