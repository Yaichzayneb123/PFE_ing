import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartsDemoRoutingModule } from './chartsdemo-routing.module';
import { ChartModule } from 'primeng/chart'
import { ChartsDemoComponent } from './chartsdemo.component';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { SliderModule } from 'primeng/slider';
import { RippleModule } from 'primeng/ripple';
import { ToolbarModule } from 'primeng/toolbar';
import { DropdownModule } from 'primeng/dropdown';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { MultiSelectModule } from 'primeng/multiselect';

@NgModule({
	imports: [
		CommonModule,
		ChartsDemoRoutingModule,
		ChartModule,
		FormsModule,
		TableModule,

		ButtonModule,
		SliderModule,
		RippleModule,
		ToolbarModule,
	
		InputTextModule,
		ToggleButtonModule,
		RippleModule,
		MultiSelectModule,
		DropdownModule,
		ToastModule,
		ToggleButtonModule,
		InputTextModule,
		DialogModule,
	],
	declarations: [ChartsDemoComponent]
})
export class ChartsDemoModule { }
