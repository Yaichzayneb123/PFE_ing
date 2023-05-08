
import { Component, OnInit } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { DataView } from 'primeng/dataview';
import { Product } from 'src/app/demo/modéle/product';
import { Variant } from 'src/app/demo/modéle/variant';
import { ProductService } from 'src/app/demo/service/product.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
    templateUrl: './listdemo.component.html',
    providers: [MessageService]
})
export class ListDemoComponent implements OnInit {

    products: Product[] = [];

    sortOptions: SelectItem[] = [];

    sortOrder: number = 0;

    sortField: string = '';

    sourceCities: any[] = [];

    targetCities: any[] = [];

    orderCities: any[] = [];
    currentUser: any;
    gesti: Product[] =[];

    cols: any[] = [];
    statuses: any[] = [];
    electronic: any[] = [];

    

    clothingDialog: boolean=false;
    accessoiresDialog: boolean=false;
    electonicDialog: boolean=false;
    fitnessDialog: boolean=false;

    constructor(private productService: ProductService,private  Service: LayoutService,private messageService: MessageService) { }

    ngOnInit() {
        this.currentUser=this.Service.getDataFromToken();
        console.log(this.currentUser.id);
        this.productService.getProducts().then(data => this.products = data);
        // this.productService.getProdbyIdSociete(this.currentUser.id ).subscribe(data => this.products = data);
               

        this.sourceCities = [
            { name: 'San Francisco', code: 'SF' },
            { name: 'London', code: 'LDN' },
            { name: 'Paris', code: 'PRS' },
            { name: 'Istanbul', code: 'IST' },
            { name: 'Berlin', code: 'BRL' },
            { name: 'Barcelona', code: 'BRC' },
            { name: 'Rome', code: 'RM' }];

        this.targetCities = [];

        this.orderCities = [
            { name: 'San Francisco', code: 'SF' },
            { name: 'London', code: 'LDN' },
            { name: 'Paris', code: 'PRS' },
            { name: 'Istanbul', code: 'IST' },
            { name: 'Berlin', code: 'BRL' },
            { name: 'Barcelona', code: 'BRC' },
            { name: 'Rome', code: 'RM' }];

        this.sortOptions = [
            { label: 'Price High to Low', value: '!price' },
            { label: 'Price Low to High', value: 'price' }
        ];
        this.cols = [
   
            { field: 'size', header: 'size' },
            { field: 'quatityVar', header: 'quatityVar' },
            
            { field: 'color', header: 'color' }
        ];
        this.electronic = [
            { label: 'smartphones', value: 'smartphones' },
            { label: 'ordinateurs portables', value: 'ordinateurs portables' },
            { label: 'tablettes', value: 'tablettes' },
            { label: 'téléviseurs', value: 'téléviseurs' },
        

        ];
        
        this.statuses = [
          { label: 'ROUGE', value: 'rouge' },
          { label: 'JAUNE', value: 'jaune' },
          { label: 'Noir', value: 'noir' }
        ];
    }

    onSortChange(event: any) {
        const value = event.value;

        if (value.indexOf('!') === 0) {
            this.sortOrder = -1;
            this.sortField = value.substring(1, value.length);
        } else {
            this.sortOrder = 1;
            this.sortField = value;
        }
    }

    onFilter(dv: DataView, event: Event) {
        dv.filter((event.target as HTMLInputElement).value);
    }
    

    // displayDialogByCategory(product: Product): void {
    //     switch (product.category) {
    //       case "Accessories":
    //         this.accessoiresDialog= true;
    //         break;
    //       case "Clothing":
    //         this.clothingDialog = true;
    //         break;
    //       case "Electronics":
    //         this.electonicDialog = true;
    //         break;
    //       case "Fitness":
    //         this.fitnessDialog= true;
    //         break;
    //       default:
    //         this.messageService.add({ severity: 'warning', summary: 'Successful', detail: 'category not exist', life: 3000 });
    //         break;
    //     }
    //   }
    
}
