import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/demo/service/product.service';
import { PhotoService } from 'src/app/demo/service/photo.service';
import { Product } from 'src/app/demo/modéle/product';
import { Depot } from 'src/app/demo/modéle/depot';
import { DepotService } from 'src/app/demo/service/depot.service';
import { MessageService } from 'primeng/api';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { ActivatedRoute } from '@angular/router';

@Component({
    templateUrl: './mediademo.component.html',
    providers: [MessageService]
})
export class MediaDemoComponent implements OnInit {

    products!: Product[];

    images!: any[];
    depotDialog: boolean = false;
    deleteDepotDialog: boolean = false;
    updateDepotDialog: boolean = false;
    depot: Depot ={};
    currentUser: any;
    submitted: boolean = false;
    idGestionnaire:any;

    depo: Depot[] = [];
    id: any;
    

    galleriaResponsiveOptions: any[] = [
        {
            breakpoint: '1024px',
            numVisible: 5
        },
        {
            breakpoint: '960px',
            numVisible: 4
        },
        {
            breakpoint: '768px',
            numVisible: 3
        },
        {
            breakpoint: '560px',
            numVisible: 1
        }
    ];

    carouselResponsiveOptions: any[] = [
        {
            breakpoint: '1024px',
            numVisible: 3,
            numScroll: 3
        },
        {
            breakpoint: '768px',
            numVisible: 2,
            numScroll: 2
        },
        {
            breakpoint: '560px',
            numVisible: 1,
            numScroll: 1
        }
    ];

    constructor( private act: ActivatedRoute,private productService: ProductService, private photoService: PhotoService, private depotService: DepotService, private messageService: MessageService,private  Service: LayoutService) { }

    ngOnInit() {

        this.currentUser=this.Service.getDataFromToken();
        console.log(this.currentUser.id);


        this.depotService.getDepotbyIdSociete(this.currentUser.id ).subscribe(
                
            res=>{
             
             this.depo = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )


        this.productService.getProductsSmall().then(products => {
            this.products = products;
        });

        this.photoService.getImages().then(images => {
            this.images = images;
        });
    }
    openDepot(){
        this.depotDialog= true;
        this.submitted = false;

    }

    hideDialog(){
        this.depotDialog = false;
        this.deleteDepotDialog = false;
        this.updateDepotDialog = false;
        

    }
    saveEvent(){
        this.depot.idEntreprise = this.currentUser.id;
    
        this.depotService.addDepot(this.depot)
        
        .subscribe(res=>{
          
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Depot Created', life: 3000 });
            this.hideDialog();
            this.ngOnInit();
            
          },err=>{
            console.log(err);
          });


    }
    deleteDepot(id:any){
        this.deleteDepotDialog = true;
        this.id=id;

    }
    delete(id:any){
        this.depotService.delete(id)
        .subscribe(
          res=>{
            this.messageService.add({ severity: 'error', summary: 'Successful', detail: 'Depot deleted', life: 3000 });
            this.ngOnInit();
            this.hideDialog();
            console.log(res);
          },
          err=>{
            console.log(err);
          }
        )
    

    }
    editDepot(depot: Depot) {
        this.depot= {...depot};
        this.updateDepotDialog= true;
         
    }

    modifier(){
        this.depotService.update(this.depot, this.depot.id ).subscribe(
            
        res=>{
    
             this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Depot updated', life: 3000 });
             this.ngOnInit()
             this.hideDialog()
          },
          err=>{
            console.log(err);
    
          }
        )
    
      }
    
}
