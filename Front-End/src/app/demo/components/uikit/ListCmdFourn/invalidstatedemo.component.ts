import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { CommandeService } from 'src/app/demo/service/commande.service';
import { CountryService } from 'src/app/demo/service/country.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
    templateUrl: './invalidstatedemo.component.html',
    providers: [MessageService]
})
export class InvalidStateDemoComponent implements OnInit {

    value = 0;

    interval: any;
    commande: any[] = [];
   id:any;
    currentUser:any;
    clients:any[]=[];
    constructor(private service:LayoutService,private commandeService:CommandeService){}

    ngOnInit() {
        this.currentUser=this.service.getDataFromToken();
         console.log(this.currentUser.id);

        this.commandeService.getCmdFourbyIdSociete(this.currentUser.id).subscribe(
                
            res=>{
             
             this.commande = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )
       
        this.interval = setInterval(() => {
            this.value = this.value + Math.floor(Math.random() * 10) + 1;
            if (this.value >= 100) {
                this.value = 100;
                clearInterval(this.interval);
            }
        }, 2000);
    }

    ngOnDestroy() {
        clearInterval(this.interval);
    }

    
    
}
