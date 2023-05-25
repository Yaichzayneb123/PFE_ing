
import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Register } from 'src/app/Models/Register';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
    selector: 'app-error',
    templateUrl: './error.component.html',
    providers: [MessageService]
})
export class ErrorComponent { 
    admin : Register={};
    image : any;
    userFile : any;
    imageURL : any;


    constructor(private builder: FormBuilder,public layoutService: LayoutService,private router: Router,private messageService: MessageService) { 
        // sessionStorage.clear();
    }


    proceedregister() {
        
          this.layoutService.RegisterUser((this.admin))
          .subscribe(res => {
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products updated', life: 3000 });
            //  this.router.navigate(['auth'])
            this.router.navigate(['/auth/login']); 
          
      },err=>{
        console.log(err);
       
      })
}
onSelectedImage(e: any){
  this.image = e.target.files[0];
  // @ts-ignore
  this.image = document.querySelector("input[type=file]").files[0];
  var readerimage = new FileReader();
  readerimage.readAsDataURL(this.image);
  readerimage.onload = (res=>{this.imageURL= readerimage.result})
  console.log(this.image); 
 }

    
    
    }
  








