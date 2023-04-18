import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductInfoRoutingModule } from './product-info-routing.module';
import { VariantComponent } from './variant.component';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { RatingModule } from 'primeng/rating';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { RippleModule } from 'primeng/ripple';
import { DialogModule } from 'primeng/dialog';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { RadioButtonModule } from 'primeng/radiobutton';


@NgModule({

  imports: [
    CommonModule,
    ProductInfoRoutingModule,
		FormsModule,
		TableModule,
		ButtonModule,
		TableModule,
		InputTextModule,
		ToggleButtonModule,
		InputTextModule,
    RippleModule,
    RippleModule,
    DialogModule,
    InputTextModule,
    InputTextareaModule,
    DropdownModule,
    RadioButtonModule,
		
  ],
  declarations: [
    VariantComponent
  ],
})
export class ProductInfoModule { }
