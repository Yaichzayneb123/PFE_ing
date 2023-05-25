import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessagesDemoComponent } from './messagesdemo.component';
import { MessagesDemoRoutingModule } from './messagesdemo-routing.module';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { RippleModule } from 'primeng/ripple';
import { MenuModule } from 'primeng/menu';
import { ToolbarModule } from 'primeng/toolbar';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';

@NgModule({
	imports: [
		CommonModule,
		MessagesDemoRoutingModule,
		FormsModule,
		MessagesModule,
		MessageModule,
		ButtonModule,
		ToastModule,
		InputTextModule,
		AutoCompleteModule,
		ToastModule,
		ToolbarModule,
		ButtonModule,
		RippleModule,
		MenuModule,
		InputTextModule,
		InputNumberModule,
		
	],
	declarations: [MessagesDemoComponent]
})
export class MessagesDemoModule { }
