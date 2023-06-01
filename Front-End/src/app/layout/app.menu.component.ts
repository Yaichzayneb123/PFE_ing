import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    admin: boolean = false;
    gest: boolean = false;
    superAdmin: boolean = false;
    login:  boolean = false;
    private roles: string[]=[]; 

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {

        this.login = this.layoutService.isLoggedIn();
        if(this.login) {
            const user = this.layoutService.getDataFromToken();
        
            this.roles = user.role[0].authority
            console.log(this.roles);
            this.admin= this.roles.includes('ADMIN');
            this.gest= this.roles.includes('GestionnaireDeStock');
            this.superAdmin=this.roles.includes('SUPERADMIN');
            console.log(this.superAdmin)

           


            if(this.admin) {
                this.model=[
                    {
                                label: 'Home Partenaire',
                                items: [
                                    { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/dash'] },
                                    { label: 'Depot', icon: 'pi pi-fw pi-image', routerLink: ['/dash/uikit/media'] },
                                    {
                                        label: 'category',
                                        icon: 'pi pi-fw pi-server',
                                        routerLink: ['/dash/pages/empty']
                                    },
                                    { label: 'management User', icon: 'pi pi-fw pi-user', routerLink: ['/dash/uikit/table'] },
                                    // {
                                    //     label: 'Gestion produit',
                                    //     icon: 'pi pi-fw pi-shopping-cart',
                                    //     routerLink: ['/dash/pages/crud']
                                    // },
                                   
                                    { label: 'management Client', icon: 'pi pi-fw pi-user', routerLink: ['/dash/uikit/file'] },
                                    { label: 'Customer order', icon: 'pi pi-fw pi-shopping-bag', routerLink: ['/dash/uikit/panel'] },
                                    { label: 'List Customer order', icon: 'pi pi-shopping-cart', routerLink: ['/dash/uikit/misc'] },
                                    { label: 'management supplier', icon: 'pi pi-fw pi-user', routerLink: ['/dash/uikit/button'] },
                                    { label: 'Supplier Order', icon: 'pi pi-fw pi-shopping-bag', routerLink: ['/dash/uikit/message'] },
                                    { label: 'List Supplier Order', icon: 'pi pi-shopping-cart', routerLink: ['/dash/uikit/invalidstate'] },


                                    { label: 'Product List', icon: 'pi pi-fw pi-list', routerLink: ['/dash/uikit/list'] },
                                    // {
                                    //     label: 'Landing',
                                    //     icon: 'pi pi-fw pi-globe',
                                    //     routerLink: ['/dash/landing']
                                    // },
                                    // { label: 'Menu', icon: 'pi pi-fw pi-bars', routerLink: ['/dash/uikit/menu'], routerLinkActiveOptions: { paths: 'subset', queryParams: 'ignored', matrixParams: 'ignored', fragment: 'ignored' } },
                                    // { label: 'Input', icon: 'pi pi-fw pi-check-square', routerLink: ['/dash/uikit/input'] },
                                    
                                    
                                    // {
                                    //     label: 'Auth',
                                    //     icon: 'pi pi-fw pi-user',
                                    //     items: [
                                    //         {
                                    //             label: 'Login',
                                    //             icon: 'pi pi-fw pi-sign-in',
                                    //             routerLink: ['/auth/login']
                                    //         },
                                    //         {
                                    //             label: 'Register',
                                    //             icon: 'pi pi-fw pi-user',
                                    //             routerLink: ['/auth/error']
                                    //         },
                                    //         {
                                    //             label: 'Access Denied',
                                    //             icon: 'pi pi-fw pi-lock',
                                    //             routerLink: ['/auth/access']
                                    //         },
                                            
                                    //     ]
                                    // },
                                ]
                            },
                ]
            

            }

            if(this.gest){
                this.model=[
                    {
                                label: 'home Gestionnaire',
                                items: [
                                    { label: 'Depot', icon: 'pi pi-fw pi-image', routerLink: ['/dash/uikit/floatlabel'] },
                                   
                                    
                                    { label: 'Product List', icon: 'pi pi-fw pi-list', routerLink: ['/dash/uikit/list'] },
                                    { label: 'management Client', icon: 'pi pi-fw pi-user', routerLink: ['/dash/uikit/formlayout'] },
                                    { label: 'Customer order', icon: 'pi pi-fw pi-shopping-bag', routerLink: ['/dash/uikit/input'] },
                                    { label: 'List Customer order', icon: 'pi pi-fw pi-shopping-cart', routerLink: ['/dash/uikit/tree'] },
                                    { label: 'management supplier', icon: 'pi pi-fw pi-user', routerLink: ['/dash/uikit/button'] },
                                    { label: 'Supplier Order', icon: 'pi pi-fw pi-shopping-bag', routerLink: ['/dash/uikit/message'] },
                                    { label: 'List Supplier Order', icon: 'pi pi-shopping-cart', routerLink: ['/dash/uikit/invalidstate'] },
                                ]
                            },
                ]
            }

            
            if(this.superAdmin) {
                this.model=[
                    {
                        label: 'home Super-Admin',
                                items: [
                                   

                                   
                                      { label: 'company management', icon: 'pi pi-fw pi-user', routerLink: ['/dash/uikit/charts'] },
                                    
                                   
                                ],
                            },
                ];
            }


        }

       





        
        // this.model = [
        //     {
        //         label: 'Home Admin',
        //         items: [
        //             { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/dash'] },
        //             { label: 'Gestion User', icon: 'pi pi-fw pi-user', routerLink: ['/dash/uikit/table'] },
        //             {
        //                 label: 'Gestion produit',
        //                 icon: 'pi pi-fw pi-shopping-cart',
        //                 routerLink: ['/dash/pages/crud']
        //             },
        //             { label: 'Product List', icon: 'pi pi-fw pi-list', routerLink: ['/dash/uikit/list'] },
        //             {
        //                 label: 'Landing',
        //                 icon: 'pi pi-fw pi-globe',
        //                 routerLink: ['/dash/landing']
        //             },
        //             {
        //                 label: 'Auth',
        //                 icon: 'pi pi-fw pi-user',
        //                 items: [
        //                     {
        //                         label: 'Login',
        //                         icon: 'pi pi-fw pi-sign-in',
        //                         routerLink: ['/auth/login']
        //                     },
        //                     {
        //                         label: 'Register',
        //                         icon: 'pi pi-fw pi-user',
        //                         routerLink: ['/auth/error']
        //                     },
        //                     {
        //                         label: 'Access Denied',
        //                         icon: 'pi pi-fw pi-lock',
        //                         routerLink: ['/auth/access']
        //                     }
        //                 ]
        //             },
        //         ]
        //     },
        //     {
        //         label: 'home Gestionnaire',
        //         items: [
        //             //{ label: 'form Layout', icon: 'pi pi-fw pi-id-card', routerLink: ['/uikit/formlayout'] },
        //             //{ label: 'Input', icon: 'pi pi-fw pi-check-square', routerLink: ['/uikit/input'] },
        //             //{ label: 'Float Label', icon: 'pi pi-fw pi-bookmark', routerLink: ['/uikit/floatlabel'] },
        //             //{ label: 'Invalid State', icon: 'pi pi-fw pi-exclamation-circle', routerLink: ['/uikit/invalidstate'] },
        //             //{ label: 'Button', icon: 'pi pi-fw pi-box', routerLink: ['/uikit/button'] },
                    
                    
        //             // { label: 'Tree', icon: 'pi pi-fw pi-share-alt', routerLink: ['/uikit/tree'] },
        //             //{ label: 'Panel', icon: 'pi pi-fw pi-tablet', routerLink: ['/uikit/panel'] },
        //             //{ label: 'Overlay', icon: 'pi pi-fw pi-clone', routerLink: ['/uikit/overlay'] },
        //             //{ label: 'Media', icon: 'pi pi-fw pi-image', routerLink: ['/uikit/media'] },
        //             //{ label: 'Menu', icon: 'pi pi-fw pi-bars', routerLink: ['/uikit/menu'], routerLinkActiveOptions: { paths: 'subset', queryParams: 'ignored', matrixParams: 'ignored', fragment: 'ignored' } },
        //             //{ label: 'Message', icon: 'pi pi-fw pi-comment', routerLink: ['/uikit/message'] },
        //             //{ label: 'File', icon: 'pi pi-fw pi-file', routerLink: ['/uikit/file'] },
        //             //{ label: 'Chart', icon: 'pi pi-fw pi-chart-bar', routerLink: ['/uikit/charts'] },
        //             //{ label: 'Misc', icon: 'pi pi-fw pi-circle', routerLink: ['/uikit/misc'] }
        //             {
        //                 label: 'Gestion produit',
        //                 icon: 'pi pi-fw pi-shopping-cart',
        //                 routerLink: ['/dash/pages/crud']
        //             },
        //             { label: 'Product List', icon: 'pi pi-fw pi-list', routerLink: ['/dash/uikit/list'] },
        //         ]
        //     },
        //     // {
        //     //     label: 'Prime Blocks',
        //     //     items: [
        //     //         { label: 'Free Blocks', icon: 'pi pi-fw pi-eye', routerLink: ['/blocks'], badge: 'NEW' },
        //     //         { label: 'All Blocks', icon: 'pi pi-fw pi-globe', url: ['https://www.primefaces.org/primeblocks-ng'], target: '_blank' },
        //     //     ]
        //     // },
        //     // {
        //     //     label: 'Utilities',
        //     //     items: [
        //     //         { label: 'PrimeIcons', icon: 'pi pi-fw pi-prime', routerLink: ['/utilities/icons'] },
        //     //         { label: 'PrimeFlex', icon: 'pi pi-fw pi-desktop', url: ['https://www.primefaces.org/primeflex/'], target: '_blank' },
        //     //     ]
        //     // },


        //     {
        //         //label: 'Gestion de stock',
        //         icon: 'pi pi-fw pi-briefcase',
        //         items: [
        //             {
        //                 label: 'Landing',
        //                 icon: 'pi pi-fw pi-globe',
        //                 routerLink: ['/dash/landing']
        //             },
        //             {
        //                 label: 'Auth',
        //                 icon: 'pi pi-fw pi-user',
        //                 items: [
        //                     {
        //                         label: 'Login',
        //                         icon: 'pi pi-fw pi-sign-in',
        //                         routerLink: ['/auth/login']
        //                     },
        //                     {
        //                         label: 'Register',
        //                         icon: 'pi pi-fw pi-user',
        //                         routerLink: ['/auth/error']
        //                     },
        //                     {
        //                         label: 'Access Denied',
        //                         icon: 'pi pi-fw pi-lock',
        //                         routerLink: ['/auth/access']
        //                     }
        //                 ]
        //             },
                   
        //             // {
        //             //     label: 'Timeline',
        //             //     icon: 'pi pi-fw pi-calendar',
        //             //     routerLink: ['/pages/timeline']
        //             // },
        //             {
        //                 label: 'Not Found',
        //                 icon: 'pi pi-fw pi-exclamation-circle',
        //                 routerLink: ['/home/notfound']
        //             },
        //             {
        //                 label: 'Empty',
        //                 icon: 'pi pi-fw pi-circle-off',
        //                 routerLink: ['/home/pages/empty']
        //             },
        //         ]
        //     },
        //     // {
        //     //     label: 'Hierarchy',
        //     //     items: [
        //     //         {
        //     //             label: 'Submenu 1', icon: 'pi pi-fw pi-bookmark',
        //     //             items: [
        //     //                 {
        //     //                     label: 'Submenu 1.1', icon: 'pi pi-fw pi-bookmark',
        //     //                     items: [
        //     //                         { label: 'Submenu 1.1.1', icon: 'pi pi-fw pi-bookmark' },
        //     //                         { label: 'Submenu 1.1.2', icon: 'pi pi-fw pi-bookmark' },
        //     //                         { label: 'Submenu 1.1.3', icon: 'pi pi-fw pi-bookmark' },
        //     //                     ]
        //     //                 },
        //     //                 {
        //     //                     label: 'Submenu 1.2', icon: 'pi pi-fw pi-bookmark',
        //     //                     items: [
        //     //                         { label: 'Submenu 1.2.1', icon: 'pi pi-fw pi-bookmark' }
        //     //                     ]
        //     //                 },
        //     //             ]
        //     //         },
        //     //         {
        //     //             label: 'Submenu 2', icon: 'pi pi-fw pi-bookmark',
        //     //             items: [
        //     //                 {
        //     //                     label: 'Submenu 2.1', icon: 'pi pi-fw pi-bookmark',
        //     //                     items: [
        //     //                         { label: 'Submenu 2.1.1', icon: 'pi pi-fw pi-bookmark' },
        //     //                         { label: 'Submenu 2.1.2', icon: 'pi pi-fw pi-bookmark' },
        //     //                     ]
        //     //                 },
        //     //                 {
        //     //                     label: 'Submenu 2.2', icon: 'pi pi-fw pi-bookmark',
        //     //                     items: [
        //     //                         { label: 'Submenu 2.2.1', icon: 'pi pi-fw pi-bookmark' },
        //     //                     ]
        //     //                 },
        //     //             ]
        //     //         }
        //     //     ]
        //     // },
        //     //  {
        //     //     label: 'Get Started',
        //     //     items: [
        //     //         {
        //     //             label: 'Documentation', icon: 'pi pi-fw pi-question', routerLink: ['/documentation']
        //     //         },
        //     //         {
        //     //             label: 'View Source', icon: 'pi pi-fw pi-search', url: ['https://github.com/primefaces/sakai-ng'], target: '_blank'
        //     //         }
        //     //     ]
        //     // }
        // ];
      
    }
    
}
