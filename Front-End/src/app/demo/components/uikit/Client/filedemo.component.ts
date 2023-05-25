import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Client } from 'src/app/demo/modéle/client';
import { ClientService } from 'src/app/demo/service/client.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Component({
    templateUrl: './filedemo.component.html',
    providers: [MessageService]
})
export class FileDemoComponent {

    uploadedFiles: any[] = [];
    clientDialog: boolean = false;
    clientDialogupdate: boolean = false;
    submitted: boolean = false;
    deleteClientDialog: boolean = false;
    client: Client[] = [];
    clientt:Client={};
    id: any;
    currentUser:any;

    constructor(private messageService: MessageService,private clientService: ClientService,private  Service: LayoutService) {}
    ngOnInit() {
        this.currentUser=this.Service.getDataFromToken();
        console.log(this.currentUser.id);



        this.clientService.getClientbyIdSociete(this.currentUser.id ).subscribe(
                
            res=>{
             
             this.client = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )
    }
    openNew() {
        
        // this.submitted = false;
        this.clientDialog = true;
    }

    hideDialog() {
        this.clientDialog = false;
        this.clientDialogupdate = false;
        this.submitted = false;
        this.deleteClientDialog= false;
    }

    saveEvent(){
        this.clientt.idEntreprise = this.currentUser.id;
        this.clientService.addClient(this.clientt)
        .subscribe(res=>{
          
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Client Created', life: 3000 });
            this.hideDialog();
            this.ngOnInit();
            
          },err=>{
            console.log(err);
          });
      
}
modifier(){
    // var eventData = JSON.stringify(this.product);
    
   
    this.clientService.update(this.clientt, this.clientt.id ).subscribe(
        
    res=>{

         this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Client updated', life: 3000 });
         this.ngOnInit()
         this.hideDialog()
      },
      err=>{
        console.log(err);

      }
    )

  }
  
    editClient(clientt: Client) {
        this.clientt= {...clientt};
        
        this.clientDialogupdate = true;
         
    }
 

    deleteUser(id:any) {
        this.deleteClientDialog = true;
        this.id=id;
        
    }

    delete(id: any){
        this.clientService.delete(id)
        .subscribe(
          res=>{
            this.messageService.add({ severity: 'error', summary: 'Successful', detail: 'Client deleted', life: 3000 });
            this.ngOnInit();
            this.hideDialog();
            console.log(res);
          },
          err=>{
            console.log(err);
          }
        )
    
      }

}
