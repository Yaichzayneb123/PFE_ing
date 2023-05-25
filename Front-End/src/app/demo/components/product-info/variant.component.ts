
import { OptionService } from './../../service/option.service';
import { Optionn, SousOption } from './../../modéle/option';
import { Variant } from '../../modéle/variant';
import { Component, EventEmitter, HostBinding, HostListener, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../service/product.service';
import { MessageService, TreeNode } from 'primeng/api';
import { VariantService } from '../../service/variant.service';
import { FormBuilder, Validators } from '@angular/forms';
import { NodeService } from '../../service/node.service';


@Component({
  selector: 'app-variant',
  templateUrl: './variant.component.html',
  styleUrls: ['./variant.component.scss'],
  providers: [MessageService]
})
export class VariantComponent implements OnInit {
  idProduct:any;
  // variant: Variant {};
  variantDialog: boolean = false;
  optionDialog: boolean = false;
  deleteVariantDialog:boolean = false;

  cols: any[] = [];
  statuses: any[] = [];

  opOption: boolean = false;
  option= new Optionn();
  sousOptionsList: SousOption[]=[];
  selectedOptionsList: any[] = [];
  

 
  selectedOption?: any;
  selectedSousOption?: any[];
  

  options?: Optionn[];

  sousOption?: SousOption;
  selectedOptionIds: number[] = [];

  selectedOptions: number[] = [];

  variantList: Variant[]=[];
  variant: any;
  variants:any;
  
  showButtons:boolean=false;
  id: any;
  selectedQuantity: number = 1
  quantityProd:any;

  constructor(private nodeService: NodeService,private act: ActivatedRoute,private productService: ProductService, private messageService: MessageService, private variantService: VariantService, private optionService:OptionService ) { }
 


  ngOnInit() {

    // this.dataArray.push(this.option);
  this.idProduct= this.act.snapshot.paramMap.get('id');
  console.log(this.idProduct);

  this.productService.getProductById(this.idProduct).subscribe(
    res=>{
      console.log(res);
      this.quantityProd=res.quantity;
    }
  )
  //this.optionService.getOption().then((data) => (this.files = data));
  this.optionService.getAllOption().subscribe((options: Optionn[]) => {
    this.options= options;
    console.log(this.options);
  
  });
  this.getVariantByIdProduit();
 

     
 
  this.cols = [
   
    { field: 'size', header: 'size' },
    // { field: 'rating', header: 'Reviews' },
    { field: 'color', header: 'color' }
];

this.statuses = [
  { label: 'ROUGE', value: 'rouge' },
  { label: 'JAUNE', value: 'jaune' },
  { label: 'Noir', value: 'noir' }
];


}

addForm(){
  this.opOption=!this.opOption;
 
}
// updateSelectedOption(moduleId: number) {
//   console.log("id",moduleId)
//   const index = this.selectedOptionIds.indexOf(moduleId);
//   if (index !== -1) {
//     this.selectedOptionIds.splice(index, 1); // Supprimer l'ID du module de la liste
//   } else {
//     this.selectedOptionIds.push(moduleId); // Ajouter l'ID du module à la liste
//   }
 
// }


// onCheckboxChange(id: number) {
//   console.log('Selected sousOption ID:', id);

// }
updateSelectedOption(optionId: any) {
  this.selectedSousOption = [];
}

// onCheckboxChange(sousOptionId: any) {
//   if (this.selectedSousOption) {
//     this.selectedSousOption.push(sousOptionId);
//   }
// }


onCheckboxChange(sousOptionId: any) {
  const selectedOption = this.options?.find(option => option.sousOptionsList.some(sousOption => sousOption.id === sousOptionId));
  const selectedSousOption = selectedOption?.sousOptionsList.find(sousOption => sousOption.id === sousOptionId);
  const index = this.selectedOptionsList.findIndex(selected => selected.optionId === selectedOption?.id && selected.sousOptionId === selectedSousOption?.id);
  if (index === -1) {
    this.selectedOptionsList.push(selectedSousOption?.id);
    console.log(this.selectedOptionsList)
  } else {
    this.selectedOptionsList.splice(index, 1);
  }
  
}

getVariantByIdProduit(){
  this.variantService.GetVariantByIdProduit(this.idProduct ).subscribe((variants: Variant[]) => {
    this.variantList = variants;

    

  });
  } 


  onSave(){
    const quantity = this.selectedQuantity; 
  
    this.variantService.saveVariant(this.selectedOptionsList,this.idProduct,quantity)
    .subscribe(
      (response) => {
      console.log("nshuuuf",response);
      this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'variant Created', life: 3000 });
      this.ngOnInit();
      this.variantDialog= false;
      
    },err =>{
      this.messageService.add({ severity: 'error', summary: 'error', detail: 'La quantité de variant dépasse celle de produit', life: 3000 });
      this.variantDialog= false;
      
    }
    );
}
delete(id:any){
  this.variantService.delete(id)
  .subscribe(
    res=>{
      this.messageService.add({ severity: 'error', summary: 'Successful', detail: 'client deleted', life: 3000 });
      // this.ngOnInit();
      // this.hideDialog();
      console.log(res);
    },
    err=>{
      console.log(err);
    }
  )

}
deleteUser(id:any) {
  this.deleteVariantDialog= true;
  this.id=id;
  
}




saveOption(){
  console.log("9bal",this.option.sousOptionsList);
  const data = {
    name: this.option.name,
    sousOptionsList: this.option.sousOptionsList,
  };
for (let i = 0; i < this.sousOptionsList.length; i++) {
  data.sousOptionsList.push({ "name": this.sousOptionsList[i]});
}
  console.log("Ba3d",data);
  console.log(this.option);     
  this.optionService.addOption(data)
        .subscribe(res=>{
            console.log(res);
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'option Created', life: 3000 });
            this.ngOnInit();
            this.optionDialog= false;
          
            
          },err=>{
            console.log(err);
          });
          this.sousOptionsList =[];
          this.option.sousOptionsList = [];
  
}


openNew() {
  this.variantDialog = true;
  
}
openOption() {
  this.optionDialog = true;
  
}

// save(){
  
//   this.variantService.add(this.variant)
//   .subscribe(res=>{
    
//       this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'gestionnaire Created', life: 3000 });
      
//     },err=>{
//       console.log(err);
//     });
// }

onsubmit(){
  // console.log(this.dataArray);

}

}


