import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Categorie } from 'src/app/demo/modéle/categorie';
import { CategorieService } from 'src/app/demo/service/categorie.service';

@Component({
    templateUrl: './emptydemo.component.html',
    providers: [MessageService]
})
export class EmptyDemoComponent {


    deleteCategoryDialog: boolean=false;
    CategoryDialogupdate: boolean=false;
    savecategoryDialog: boolean=false;
    category: Categorie = {} ;
    cat: Categorie[] = [];
    id: any;


    constructor(private serviceCategorie:CategorieService,private messageService:MessageService){}
    ngOnInit() {
        
    
            this.serviceCategorie.getAllCategorie().then(data => this.cat = data);
        }

        
    hideDialog() {
        this.deleteCategoryDialog = false;
        this.CategoryDialogupdate = false;
        this.savecategoryDialog= false;
    }

    openNew() {
        
        // this.submitted = false;
        this.savecategoryDialog = true;
    }
    edit(category: Categorie) {

        this.category= {...category};
        console.log(this.category);
        
        this.CategoryDialogupdate = true;
         
    }
    saveEvent(){
        this.serviceCategorie.addCategorie(this.category)
        .subscribe(res=>{
          
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'category Created', life: 3000 });
            this.hideDialog();
            this.ngOnInit();
            
          },err=>{
            console.log(err);
          });
 }
 deleteCat(id:any) {
    this.deleteCategoryDialog = true;
    this.id=id;
    
}
 delete(id: any){
    this.serviceCategorie.delete(id)
    .subscribe(
      res=>{
        this.messageService.add({ severity: 'error', summary: 'Successful', detail: 'category deleted', life: 3000 });
        this.ngOnInit();
        this.hideDialog();
        console.log(res);
      },
      err=>{
        console.log(err);
      }
    )

  }
  modifier(){
    // var eventData = JSON.stringify(this.product);
    
   
    this.serviceCategorie.update(this.category, this.category.id ).subscribe(
        
    res=>{

         this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Category updated', life: 3000 });
         this.ngOnInit()
         this.hideDialog()
      },
      err=>{
        console.log(err);

      }
    )

  }




}

