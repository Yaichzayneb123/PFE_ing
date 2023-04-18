
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

    
    
    }
  








