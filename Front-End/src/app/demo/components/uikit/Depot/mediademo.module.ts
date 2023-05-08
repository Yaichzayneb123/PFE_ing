import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MediaDemoComponent } from './mediademo.component';
import { MediaDemoRoutingModule } from './mediademo-routing.module';
import { ButtonModule } from 'primeng/button';
import { ImageModule } from 'primeng/image';
import { GalleriaModule } from 'primeng/galleria';
import { CarouselModule } from 'primeng/carousel';
import { ToolbarModule } from 'primeng/toolbar';
import { DialogModule } from 'primeng/dialog';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';

@NgModule({
	imports: [
		CommonModule,
		MediaDemoRoutingModule,
		ButtonModule,
		ImageModule,
		GalleriaModule,
		CarouselModule,
		ToolbarModule,
		DialogModule,
		FormsModule,
		InputTextModule,
		ToastModule
	],
	declarations: [MediaDemoComponent]
})
export class MediaDemoModule { }
