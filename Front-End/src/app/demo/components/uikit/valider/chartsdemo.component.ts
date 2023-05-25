import { Component, OnDestroy, OnInit, ElementRef, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Subscription } from 'rxjs';
import { Customer, Gesti, Representative } from 'src/app/demo/modéle/customer';
import { Product } from 'src/app/demo/modéle/product';
import { Societe } from 'src/app/demo/modéle/societe';
import { CustomerService } from 'src/app/demo/service/customer.service';
import { SocieteService } from 'src/app/demo/service/societe.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
    templateUrl: './chartsdemo.component.html',
    providers: [MessageService]
})
export class ChartsDemoComponent implements OnInit{

    selectedState: any = null;

    customers1: Customer[] = [];

    customers2: Customer[] = [];

    customers3: Customer[] = [];

    selectedCustomers1: Customer[] = [];

    // selectedCustomer: Customer = {};

    representatives: Representative[] = [];

    statuses: any[] = [];
    dropdownItems: any[] = [];

    products: Product[] = [];

    rowGroupMetadata: any;

   

    productDialog: boolean = false;
    productDialogupdate: boolean = false;
    submitted: boolean = false;
    deleteProductDialog: boolean = false;

    activityValues: number[] = [0, 100];

    isExpanded: boolean = false;

    idFrozen: boolean = false;

    loading: boolean = true;
    gest: Customer[] = [];
    gesti: Customer ={};

    gestion: any;

    customer: Customer[] = [];

    


    gestionnaire: Gesti={} ;

    currentUser: any;
    id: any;

    societe:Societe[]=[];
    validate: any;

    verfied: boolean=false;
    
    


    @ViewChild('filter') filter!: ElementRef;

    constructor(private customerService: CustomerService,private societeService:SocieteService, private  Service: LayoutService, private messageService: MessageService) { }
    

    ngOnInit() {
        
    


        // this.currentUser=this.Service.getDataFromToken();
        // console.log(this.currentUser.id);
        

        
          
            
            this.societeService.getSociete().subscribe(
                
            res=>{
             
             this.societe = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )

          
          
        
        
    }

    valid(id:any){
      this.societeService.validate(id).subscribe(
        res=>{
          console.log(res);
          this.validate=res;
          this.ngOnInit();
        }
      )
    }

  

   

    formatCurrency(value: number) {
        return value.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
    }

   
    openNew() {
        
        // this.submitted = false;
        this.productDialog = true;
    }

    hideDialog() {
        this.productDialog = false;
        this.productDialogupdate = false;
        this.submitted = false;
        this.deleteProductDialog= false;
    }

    saveEvent(){
        this.gesti.societe = this.currentUser.id;
        this.customerService.addGest(this.gesti)
        .subscribe(res=>{
          
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'gestionnaire Created', life: 3000 });
            this.hideDialog();
            this.ngOnInit();
            
          },err=>{
            console.log(err);
          });
      
}
modifier(){
    // var eventData = JSON.stringify(this.product);
    
   
    this.customerService.update(this.gestionnaire, this.gestionnaire.id ).subscribe(
        
    res=>{

         this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Gestionnaire updated', life: 3000 });
         this.ngOnInit()
         this.hideDialog()
      },
      err=>{
        console.log(err);

      }
    )

  }
  
    editProduct(gestionnaire: Gesti) {
        this.gestionnaire= {...gestionnaire};
        
        this.productDialogupdate = true;
         
    }
    // editProduct(product: Product) {
    //     this.product = { ...product };
    //     this.productDialogupdate = true;
        
        
       
    // }

    deleteUser(id:any) {
        this.deleteProductDialog = true;
        this.id=id;
        
    }

    delete(id: any){
        this.customerService.delete(id)
        .subscribe(
          res=>{
            this.messageService.add({ severity: 'error', summary: 'Successful', detail: 'Gestionnaire deleted', life: 3000 });
            this.ngOnInit();
            this.hideDialog();
            console.log(res);
          },
          err=>{
            console.log(err);
          }
        )
    
      }
    
    
}
