import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FileUploadModule } from 'primeng/fileupload';
import { FileDemoRoutingModule } from './filedemo-routing.module';
import { FileDemoComponent } from './filedemo.component';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToolbarModule } from 'primeng/toolbar';
import { InputTextModule } from 'primeng/inputtext';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { DialogModule } from 'primeng/dialog';


@NgModule({
	imports: [
		CommonModule,
		FormsModule,
		FileDemoRoutingModule,
		FormsModule,
		TableModule,
		ButtonModule,
		ToolbarModule,
		TableModule,
		InputTextModule,
		ToggleButtonModule,
		DropdownModule,
		ToastModule,
		ToggleButtonModule,
		InputTextModule,
		DialogModule,
	],
	declarations: [FileDemoComponent],
})
export class FileDemoModule { }
