import { Image } from '../../../modéle/image';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/demo/modéle/product';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { ProductService } from 'src/app/demo/service/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, map } from 'rxjs';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { CategorieService } from 'src/app/demo/service/categorie.service';
import { Categorie } from 'src/app/demo/modéle/categorie';
@Component({
    templateUrl: './crud.component.html',
    providers: [MessageService]
})
export class CrudComponent implements OnInit {

    
    productId:any;
    productDialog: boolean = false;
    
    productDialogupdate: boolean=false;

    deleteProductDialog: boolean = false;

    deleteProductsDialog: boolean = false;

    products: Product[] = [];

    // product: Product = {} ;

    selectedProducts: Product[] = [];

    submitted: boolean = false;

    cols: any[] = [];

    statuses: any[] = [];
    product: Product = {} ;
 

    image : any;
    userFile : any;
    imageURL : any;
    idDepot: any;
    gesti: Product[] =[];
    id: any;
    
    
    uploadedFiles: any[] = [];

    rowsPerPageOptions = [5, 10, 20];

    cat: Categorie[] = [];
    
    selectedCategory: any;
  

    constructor(private productService: ProductService,private serviceCategorie:CategorieService,private  Service: LayoutService, private messageService: MessageService,private router :Router, private act: ActivatedRoute) { }

    ngOnInit() {
      this.idDepot= this.act.snapshot.paramMap.get('id');
          this.getProductsBySociete();
        this.serviceCategorie.getAllCategorie().then(data => this.cat = data);
        this.cols = [
            { field: 'product', header: 'Product' },
            { field: 'price', header: 'Price' },
             { field: 'category', header: 'Category' },
            // { field: 'rating', header: 'Reviews' },
            { field: 'inventoryStatus', header: 'Status' }
        ];

        this.statuses = [
            { label: 'INSTOCK', value: 'instock' },
            { label: 'LOWSTOCK', value: 'lowstock' },
            { label: 'OUTOFSTOCK', value: 'outofstock' }
        ];

    }
 getProductsBySociete(){
  this.idDepot= this.act.snapshot.paramMap.get('id');
  this.productService.getProdbyIdSociete(this.idDepot ).subscribe({
    next :(res:any)=>{
       this.gesti =res
       console.log(res);
    }
  }) 

 }
    modifier(){
      this.idDepot= this.act.snapshot.paramMap.get('id');
        this.product.depotId=this.idDepot;
      
        //this.product.societe=this.currentUser.id

        this.productService.update(this.id, this.product ).subscribe(
        res=>{
          console.log(res);
    
             this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products updated', life: 3000 });
             this.ngOnInit()
             this.hideDialog()
             this.product = {};
             
          },
          err=>{
            console.log(err);
    
          }
        )
      
      }

    openNew() {
        this.product = {...this.product};
       this.productId =this.product.id;
        this.submitted = false;
        this.productDialog = true;
        
    }

    deleteSelectedProducts() {
        this.deleteProductsDialog = true;
    }

    editProduct(product: Product) {
      this.id = product.id
        this.product = { ...product };
        this.productDialogupdate = true;
        
        
       
    }
    

    deleteProduct(product: Product) {
        this.deleteProductDialog = true;
        this.product = { ...product };
    }
    

    confirmDeleteSelected() {
        this.deleteProductsDialog = false;
        this.products = this.products.filter(val => !this.selectedProducts.includes(val));
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
        this.selectedProducts = [];
    }

    confirmDelete() {
        this.deleteProductDialog = false;
        //this.products = this.products.filter(val => val.id !== this.product.id);
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
        this.product = {};
    }

    hideDialog() {
        this.productDialog = false;
        this.productDialogupdate = false;
        this.deleteProductDialog = false;
        this.submitted = false;
    }
    
   

    
    saveEvent(){
        // this.product=this.form.value;
        //this.product.depotId=1;
        this.idDepot= this.act.snapshot.paramMap.get('id');
        this.product.depotId=this.idDepot;
        console.log(this.product.depotId);
        console.log(this.product);
        var eventData = JSON.stringify(this.product);
        console.log(eventData);
        this.productService.addPro(eventData, this.image).subscribe(res=>{
          
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });
          this.hideDialog();
          this.ngOnInit();
        },err=>{
          console.log(err);
        });
      }

      onSelectedImage(e: any){
        this.userFile = e.target.files[0];
        // @ts-ignore
        this.image = document.querySelector('input[type=file]').files[0];
        var reader = new FileReader();
        if (this.image instanceof Blob) {
        reader.readAsDataURL(this.image);
      } else {
        console.error('Parameter is not a valid Blob object');
      }
        reader.onload = (res=>{this.imageURL = reader.result})
        console.log(this.userFile);
    }

    createId(): string {
        let id = '';
        const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        for (let i = 0; i < 5; i++) {
            id += chars.charAt(Math.floor(Math.random() * chars.length));
        }
        return id;
    }

    onGlobalFilter(table: Table, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
    }
    onUpload(event: any) {
        for (const file of event.files) {
            this.uploadedFiles.push(file);
        }

        this.messageService.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded' });
    }

    onBasicUpload() {
        this.messageService.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded with Basic Mode' });
    }

    deleteProd(id:any) {
        this.deleteProductDialog = true;
        this.id=id;
        
    }
    delete(id: any){
        this.productService.delete(id)
        .subscribe(
          res=>{
            this.messageService.add({ severity: 'error', summary: 'Successful', detail: 'Produit deleted', life: 3000 });
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


