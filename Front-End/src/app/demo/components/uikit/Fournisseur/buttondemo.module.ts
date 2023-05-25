import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonDemoRoutingModule } from './buttondemo-routing.module';
import { ButtonDemoComponent } from './buttondemo.component';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { SplitButtonModule } from 'primeng/splitbutton';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ToolbarModule } from 'primeng/toolbar';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { DialogModule } from 'primeng/dialog';

@NgModule({
	imports: [
		CommonModule,
		ButtonDemoRoutingModule,
		ButtonModule,
		RippleModule,
		SplitButtonModule,
		ToggleButtonModule,
		FormsModule,
		TableModule,
		ToolbarModule,
		InputTextModule,
		ToggleButtonModule,
		DropdownModule,
		ToastModule,
		ToggleButtonModule,
		InputTextModule,
		DialogModule
	],
	declarations: [ButtonDemoComponent]
})
export class ButtonDemoModule { }
