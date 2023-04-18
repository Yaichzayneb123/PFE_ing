import { Image } from './../../../api/image';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/demo/api/product';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { ProductService } from 'src/app/demo/service/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
@Component({
    templateUrl: './crud.component.html',
    providers: [MessageService]
})
export class CrudComponent implements OnInit {

    

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
    product: any;

    image : any;
    userFile : any;
    imageURL : any;
    currentUser: any;
    gesti: Product[] =[];
    id: any;
    
    
    uploadedFiles: any[] = [];

    rowsPerPageOptions = [5, 10, 20];

    constructor(private productService: ProductService,private  Service: LayoutService, private messageService: MessageService,private router :Router, private act: ActivatedRoute) { }

    ngOnInit() {

        this.currentUser=this.Service.getDataFromToken();
        console.log(this.currentUser.id);

        this.productService.getProdbyIdSociete(this.currentUser.id ).then(data => this.gesti = data);
        
        // this.productService.getProducts().then(data => this.products = data);
        // console.log(this.product.id)

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
    modifier(){
        // var eventData = JSON.stringify(this.product);
        this.productService.update(this.product, this.product.id ).subscribe(
            
        res=>{

             this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products updated', life: 3000 });
             this.ngOnInit()
             this.hideDialog()
          },
          err=>{
            console.log(err);
    
          }
        )
    
      }

    openNew() {
        this.product = {};
        this.submitted = false;
        this.productDialog = true;
        
    }

    deleteSelectedProducts() {
        this.deleteProductsDialog = true;
    }

    editProduct(product: Product) {
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

    // saveProduct() {
    //     this.submitted = true;

    //     if (this.product.name?.trim()) {
    //         if (this.product.id) {
    //             // @ts-ignore
    //             this.product.inventoryStatus = this.product.inventoryStatus.value ? this.product.inventoryStatus.value : this.product.inventoryStatus;
    //             this.products[this.findIndexById(this.product.id)] = this.product;
    //             this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000 });
    //         } else {
    //             this.product.id = this.createId();
    //             this.product.code = this.createId();
    //             this.product.image = 'product-placeholder.svg';
    //             // @ts-ignore
    //             this.product.inventoryStatus = this.product.inventoryStatus ? this.product.inventoryStatus.value : 'INSTOCK';
    //             this.products.push(this.product);
    //             this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });
    //         }

    //         this.products = [...this.products];
    //         this.productDialog = false;
    //         this.product = {};
    //     }
    // }

    saveEvent(){
        // this.product=this.form.value;
        this.product.societe=this.currentUser.id;
        console.log(this.product.societe);
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


    // onSelectedImage(e:any){
    //     this.image = e.target.files[0];
    //     var reader = new FileReader();
    //     reader.readAsDataURL(this.image);
    //     reader.onload = (res=>{this.imageURL = reader.result})
    //     console.log(this.image);
    //   }


    // findIndexById(id: string): number {
    //     let index = -1;
    //     for (let i = 0; i < this.products.length; i++) {
    //         if (this.products[i].id === id) {
    //             index = i;
    //             break;
    //         }
    //     }

    //     return index;
    // }

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
