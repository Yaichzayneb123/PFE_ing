import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [RouterModule.forChild([
        { path: 'button', data: { breadcrumb: 'Button' }, loadChildren: () => import('./Fournisseur/buttondemo.module').then(m => m.ButtonDemoModule) },
        { path: 'charts', data: { breadcrumb: 'Charts' }, loadChildren: () => import('./valider/chartsdemo.module').then(m => m.ChartsDemoModule) },
        { path: 'file', data: { breadcrumb: 'File' }, loadChildren: () => import('./Client/filedemo.module').then(m => m.FileDemoModule) },
        { path: 'floatlabel', data: { breadcrumb: 'Float Label' }, loadChildren: () => import('./DepotGesti/floatlabeldemo.module').then(m => m.FloatlabelDemoModule) },
        { path: 'formlayout', data: { breadcrumb: 'Form Layout' }, loadChildren: () => import('./ClientGesti/formlayoutdemo.module').then(m => m.FormLayoutDemoModule) },
        { path: 'input', data: { breadcrumb: 'Input' }, loadChildren: () => import('./cmdClientGesti/inputdemo.module').then(m => m.InputDemoModule) },
        { path: 'invalidstate', data: { breadcrumb: 'Invalid State' }, loadChildren: () => import('./ListCmdFourn/invalidstatedemo.module').then(m => m.InvalidStateDemoModule) },
        { path: 'list', data: { breadcrumb: 'List' }, loadChildren: () => import('./list-product/listdemo.module').then(m => m.ListDemoModule) },
        { path: 'media', data: { breadcrumb: 'Media' }, loadChildren: () => import('./Depot/mediademo.module').then(m => m.MediaDemoModule) },
        { path: 'message', data: { breadcrumb: 'Message' }, loadChildren: () => import('./CmdFounisseur/messagesdemo.module').then(m => m.MessagesDemoModule) },
        { path: 'misc', data: { breadcrumb: 'Misc' }, loadChildren: () => import('./listCmdClient/miscdemo.module').then(m => m.MiscDemoModule) },
        { path: 'overlay', data: { breadcrumb: 'Overlay' }, loadChildren: () => import('./overlays/overlaysdemo.module').then(m => m.OverlaysDemoModule) },
        { path: 'panel', data: { breadcrumb: 'Panel' }, loadChildren: () => import('./CmdClient/panelsdemo.module').then(m => m.PanelsDemoModule) },
        { path: 'table', data: { breadcrumb: 'Table' }, loadChildren: () => import('./gestionnaire/tabledemo.module').then(m => m.TableDemoModule) },
        { path: 'tree', data: { breadcrumb: 'Tree' }, loadChildren: () => import('./listCmdCGest/treedemo.module').then(m => m.TreeDemoModule) },
        { path: 'menu', data: { breadcrumb: 'Menu' }, loadChildren: () => import('./menus/menus.module').then(m => m.MenusModule) },
        { path: '**', redirectTo: '/notfound' }
    ])],
    exports: [RouterModule]
})
export class UIkitRoutingModule { }
