import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VariantComponent } from './variant.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forChild([
    { path: ':id', component: VariantComponent }
])],
exports: [RouterModule]
})
export class ProductInfoRoutingModule { }
