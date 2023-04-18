import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/Models/model';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [`
        :host ::ng-deep .pi-eye,
        :host ::ng-deep .pi-eye-slash {
            transform:scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }
    `]
})
export class LoginComponent {

    currentUser: any;

    valCheck: string[] = ['remember'];

    password!: string;

result: any;

admin : User={};
 

dataUser : any;
token : any ;
    

    constructor(private builder: FormBuilder,public layoutService: LayoutService,private router: Router) { 
        sessionStorage.clear();
    }


      ngOnInit() {
       this.dataUser = this.layoutService.getDataFromToken();
       console.log(this.dataUser);
       
      }


proceedlogin(){
  
  this.layoutService.LoginUser((this.admin))
  .subscribe(
    res=>{
       this.token = res;
    //    console.log(this.token.token)

       localStorage.setItem('token',this.token.token);  
    //    sessionStorage.setItem('username',this.token.token);  
       sessionStorage.setItem('role',this.token.token); 
      
    
       console.log()

       this.router.navigate(['/dash']); 

        console.log(res); 
    },
    err=>{
      console.log(err);
     
    }) 
    }

}
