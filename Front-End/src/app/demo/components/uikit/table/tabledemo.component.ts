import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Customer, Gesti, Representative } from 'src/app/demo/api/customer';
import { CustomerService } from 'src/app/demo/service/customer.service';
import { Product } from 'src/app/demo/api/product';
import { Table } from 'primeng/table';
import { MessageService, ConfirmationService } from 'primeng/api';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
interface expandedRows {
    [key: string]: boolean;
}

@Component({
    templateUrl: './tabledemo.component.html',
    providers: [MessageService, ConfirmationService]
})
export class TableDemoComponent implements OnInit {

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

    expandedRows: expandedRows = {};

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
    
    


    @ViewChild('filter') filter!: ElementRef;

    constructor(private customerService: CustomerService, private  Service: LayoutService, private messageService: MessageService) { }
    

    ngOnInit() {
        
    

        // this.customerService.getGest().then(data => this.gest = data);
        this.currentUser=this.Service.getDataFromToken();
        console.log(this.currentUser.id);
        // this.getGestbyId();

        
            // var eventData = JSON.stringify(this.product);
            
            this.customerService.getGestbyIdSociete(this.currentUser.id ).subscribe(
                
            res=>{
             
             this.customer = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )
        
          
        
        // this.customerService.getGestbyIdSociete(this.currentUser.id).then(data => this.gest = data);
        
        this.loading = false;
       
        // this.customerService.getCustomersLarge().then(customers => {
        //     this.customers1 = customers;
        //     this.loading = false;
            

        //     // @ts-ignore
        //     this.customers1.forEach(customer => customer.date = new Date(customer.date));
        // });
        // this.customerService.getCustomersMedium().then(customers => this.customers2 = customers);
        // this.customerService.getCustomersLarge().then(customers => this.customers3 = customers);
        // this.productService.getProductsWithOrdersSmall().then(data => this.products = data);

        // this.representatives = [
        //     { name: 'Amy Elsner', image: 'amyelsner.png' },
        //     { name: 'Anna Fali', image: 'annafali.png' },
        //     { name: 'Asiya Javayant', image: 'asiyajavayant.png' },
        //     { name: 'Bernardo Dominic', image: 'bernardodominic.png' },
        //     { name: 'Elwin Sharvill', image: 'elwinsharvill.png' },
        //     { name: 'Ioni Bowcher', image: 'ionibowcher.png' },
        //     { name: 'Ivan Magalhaes', image: 'ivanmagalhaes.png' },
        //     { name: 'Onyama Limba', image: 'onyamalimba.png' },
        //     { name: 'Stephen Shaw', image: 'stephenshaw.png' },
        //     { name: 'XuXue Feng', image: 'xuxuefeng.png' }
        // ];

        // this.statuses = [
        //     { label: 'Unqualified', value: 'unqualified' },
        //     { label: 'Qualified', value: 'qualified' },
        //     { label: 'New', value: 'new' },
        //     { label: 'Negotiation', value: 'negotiation' },
        //     { label: 'Renewal', value: 'renewal' },
        //     { label: 'Proposal', value: 'proposal' }
        // ];

        this.dropdownItems = [
            { name: 'Gestionnaire ', code: 'Gestionnaire ' },
            { name: 'autre', code: 'autre' },
            { name: 'Option 3', code: 'Option 3' }
        ];
    }

    // onSort() {
    //     this.updateRowGroupMetaData();
    // }

    // updateRowGroupMetaData() {
    //     this.rowGroupMetadata = {};

    //     if (this.customers3) {
    //         for (let i = 0; i < this.customers3.length; i++) {
    //             const rowData = this.customers3[i];
    //             const representativeName = rowData?.representative?.name || '';

    //             if (i === 0) {
    //                 this.rowGroupMetadata[representativeName] = { index: 0, size: 1 };
    //             }
    //             else {
    //                 const previousRowData = this.customers3[i - 1];
    //                 const previousRowGroup = previousRowData?.representative?.name;
    //                 if (representativeName === previousRowGroup) {
    //                     this.rowGroupMetadata[representativeName].size++;
    //                 }
    //                 else {
    //                     this.rowGroupMetadata[representativeName] = { index: i, size: 1 };
    //                 }
    //             }
    //         }
    //     }
    // }

    expandAll() {
        if (!this.isExpanded) {
            this.products.forEach(product => product && product.name ? this.expandedRows[product.name] = true : '');

        } else {
            this.expandedRows = {};
        }
        this.isExpanded = !this.isExpanded;
    }

    formatCurrency(value: number) {
        return value.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
    }

    onGlobalFilter(table: Table, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
    }

    clear(table: Table) {
        table.clear();
        this.filter.nativeElement.value = '';
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