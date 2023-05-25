import { Component, OnInit } from '@angular/core';
import { NodeService } from 'src/app/demo/service/node.service';
import { MessageService, TreeNode} from 'primeng/api';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { CommandeService } from 'src/app/demo/service/commande.service';
import { GestionnaireService } from 'src/app/demo/service/gestionnaire.service';

@Component({
    templateUrl: './treedemo.component.html',
    providers: [MessageService]
})
export class TreeDemoComponent implements OnInit {

    value = 0;

    interval: any;
    commande: any[] = [];
   id:any;
    currentUser:any;
    clients:any[]=[];
    gesti:any;
    constructor(private service:LayoutService,private messageService: MessageService,private commandeService:CommandeService,private gestService:GestionnaireService){}

    ngOnInit() {
        this.currentUser=this.service.getDataFromToken();
         console.log(this.currentUser.id);

      

            this.gestService.getGestbyId(this.currentUser.id ).subscribe(
                
                res=>{
                 
                 this.gesti = res
                    console.log(res);
                    this.commandeService.getCmdbyIdSociete(this.gesti.societe).subscribe(
                
                        res=>{
                         
                         this.commande = res
                            console.log(res);
                    
                          },
                          err=>{
                            console.log(err);
                    
                          }
                        )
            
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
