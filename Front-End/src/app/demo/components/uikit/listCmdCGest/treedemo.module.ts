import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TreeDemoComponent } from './treedemo.component';
import { TreeDemoRoutingModule } from './treedemo-routing.module';
import { TreeModule } from 'primeng/tree';
import { TreeTableModule } from 'primeng/treetable';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';

@NgModule({
	imports: [
		CommonModule,
		TreeDemoRoutingModule,
		FormsModule,
		TreeModule,
		TreeTableModule,
		TableModule,
		ToastModule
	],
	declarations: [TreeDemoComponent],
})
export class TreeDemoModule { }
