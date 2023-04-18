import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorRoutingModule } from './error-routing.module';
import { ErrorComponent } from './error.component';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from 'primeng/password';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';

@NgModule({
    imports: [
        CommonModule,
         ErrorRoutingModule,
        ButtonModule,
        PasswordModule,
        InputTextModule,
        FormsModule,
        
        ToastModule,

    ],
    declarations: [ErrorComponent]
})
export class ErrorModule { }
