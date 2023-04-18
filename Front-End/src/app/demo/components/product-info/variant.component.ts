import { Variant } from './../../api/variant';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../service/product.service';
import { MessageService } from 'primeng/api';
import { VariantService } from '../../service/variant.service';

@Component({
  selector: 'app-variant',
  templateUrl: './variant.component.html',
  styleUrls: ['./variant.component.scss'],
  providers: [MessageService]
})
export class VariantComponent implements OnInit {
  idProduct:any;
  variant: Variant ={};
  variantDialog: boolean = false;
  cols: any[] = [];
  statuses: any[] = [];
 

  constructor(private act: ActivatedRoute,private productService: ProductService, private messageService: MessageService, private variantService: VariantService) { }
 


  ngOnInit() {
  this.idProduct= this.act.snapshot.paramMap.get('id');
  console.log(this.idProduct);
  this.productService.getProductById(this.idProduct).subscribe(
    res=>{
      console.log(res);
    }
  )
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
openNew() {
  
  this.variantDialog = true;
  
}

save(){
  
  this.variantService.add(this.variant)
  .subscribe(res=>{
    
      this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'gestionnaire Created', life: 3000 });
      // this.ngOnInit();
      
    },err=>{
      console.log(err);
    });

}

}
