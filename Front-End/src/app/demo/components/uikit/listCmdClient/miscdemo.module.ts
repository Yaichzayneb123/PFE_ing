import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MiscDemoComponent } from './miscdemo.component';
import { MiscDemoRoutingModule } from './miscdemo-routing.module';
import { ProgressBarModule } from 'primeng/progressbar';
import { BadgeModule } from 'primeng/badge';
import { AvatarModule } from 'primeng/avatar';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { TagModule } from 'primeng/tag';
import { ChipModule } from 'primeng/chip';
import { SkeletonModule } from 'primeng/skeleton';
import { ButtonModule } from 'primeng/button';
import { AvatarGroupModule } from 'primeng/avatargroup';
import { ScrollTopModule } from 'primeng/scrolltop';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ToolbarModule } from 'primeng/toolbar';
import { InputTextModule } from 'primeng/inputtext';
import { DialogModule } from 'primeng/dialog';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { ToastModule } from 'primeng/toast';
import { DropdownModule } from 'primeng/dropdown';

@NgModule({
	imports: [
		CommonModule,
		MiscDemoRoutingModule,
		ProgressBarModule,
		BadgeModule,
		AvatarModule,
		ScrollPanelModule,
		TagModule,
		ChipModule,
		ButtonModule,
		SkeletonModule,
		AvatarGroupModule,
		ScrollTopModule,
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
	declarations: [MiscDemoComponent]
})
export class MiscDemoModule { }
