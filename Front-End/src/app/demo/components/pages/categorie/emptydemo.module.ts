import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmptyDemoRoutingModule } from './emptydemo-routing.module';
import { EmptyDemoComponent } from './emptydemo.component';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { SliderModule } from 'primeng/slider';
import { InputTextModule } from 'primeng/inputtext';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { DialogModule } from 'primeng/dialog';

@NgModule({
    imports: [
        CommonModule,
        EmptyDemoRoutingModule,
        FormsModule,
		TableModule,
		ButtonModule,
		SliderModule,
		TableModule,
		InputTextModule,
		ToggleButtonModule,
		DropdownModule,
		ToastModule,
		ToggleButtonModule,
		InputTextModule,
		DialogModule,
    ],
    declarations: [EmptyDemoComponent]
})
export class EmptyDemoModule { }
