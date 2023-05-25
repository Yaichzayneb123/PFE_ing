import { Component, OnInit, OnDestroy } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Product } from '../../modéle/product';
import { ProductService } from '../../service/product.service';
import { Subscription } from 'rxjs';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { ClientService } from '../../service/client.service';
import { DepotService } from '../../service/depot.service';
import { GestionnaireService } from '../../service/gestionnaire.service';

@Component({
    templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit, OnDestroy {

    items!: MenuItem[];

    products!: Product[];

    chartData: any;

    chartOptions: any;

    subscription!: Subscription;
    totalClients?: number;
    totalProds?: number;
    totalDepots?:number;
    totalGests?:number;

    constructor(private gestService:GestionnaireService,private depotService:DepotService,private prodService:ProductService,private clientService:ClientService,private productService: ProductService, public layoutService: LayoutService) {
        this.subscription = this.layoutService.configUpdate$.subscribe(() => {
            this.initChart();
        });
    }

    ngOnInit() {
        this.depotService.getNbDepots().subscribe(
                
            res=>{
             
             this.totalDepots = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )

        this.clientService.getNbClients().subscribe(
                
            res=>{
             
             this.totalClients = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )
           this.totalPros();
                this. totalGest();
        this.initChart();
        this.productService.getProductsSmall().then(data => this.products = data);

        this.items = [
            { label: 'Add New', icon: 'pi pi-fw pi-plus' },
            { label: 'Remove', icon: 'pi pi-fw pi-minus' }
        ];
    }
    totalGest(){
        this.gestService.getNbGests().subscribe(
                
            res=>{
             
             this.totalGests = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )

    }
    totalPros(){
        this.prodService.getNbProds().subscribe(
                
            res=>{
             
             this.totalProds = res
                console.log(res);
        
              },
              err=>{
                console.log(err);
        
              }
            )
    }

    initChart() {
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');
        const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
        const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

        this.chartData = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'First Dataset',
                    data: [65, 59, 80, 81, 56, 55, 40],
                    fill: false,
                    backgroundColor: documentStyle.getPropertyValue('--bluegray-700'),
                    borderColor: documentStyle.getPropertyValue('--bluegray-700'),
                    tension: .4
                },
                {
                    label: 'Second Dataset',
                    data: [28, 48, 40, 19, 86, 27, 90],
                    fill: false,
                    backgroundColor: documentStyle.getPropertyValue('--green-600'),
                    borderColor: documentStyle.getPropertyValue('--green-600'),
                    tension: .4
                }
            ]
        };

        this.chartOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: textColor
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: textColorSecondary
                    },
                    grid: {
                        color: surfaceBorder,
                        drawBorder: false
                    }
                },
                y: {
                    ticks: {
                        color: textColorSecondary
                    },
                    grid: {
                        color: surfaceBorder,
                        drawBorder: false
                    }
                }
            }
        };
    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
}
