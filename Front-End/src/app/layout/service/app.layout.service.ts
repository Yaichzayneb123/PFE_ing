import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, map, of } from 'rxjs';

export interface AppConfig {
    inputStyle: string;
    colorScheme: string;
    theme: string;
    ripple: boolean;
    menuMode: string;
    scale: number;
}

interface LayoutState {
    staticMenuDesktopInactive: boolean;
    overlayMenuActive: boolean;
    profileSidebarVisible: boolean;
    configSidebarVisible: boolean;
    staticMenuMobileActive: boolean;
    menuHoverActive: boolean;
}

@Injectable({
    providedIn: 'root',
})
export class LayoutService {
    constructor(private http:HttpClient, private router:Router) { 

    }

    apiurl='http://localhost:8086/api/v1/auth';

    config: AppConfig = {
        ripple: false,
        inputStyle: 'outlined',
        menuMode: 'static',
        colorScheme: 'light',
        theme: 'lara-light-indigo',
        scale: 14,
    };

    state: LayoutState = {
        staticMenuDesktopInactive: false,
        overlayMenuActive: false,
        profileSidebarVisible: false,
        configSidebarVisible: false,
        staticMenuMobileActive: false,
        menuHoverActive: false
    };

    private configUpdate = new Subject<AppConfig>();

    private overlayOpen = new Subject<any>();

    configUpdate$ = this.configUpdate.asObservable();

    overlayOpen$ = this.overlayOpen.asObservable();

    onMenuToggle() {
        if (this.isOverlay()) {
            this.state.overlayMenuActive = !this.state.overlayMenuActive;
            if (this.state.overlayMenuActive) {
                this.overlayOpen.next(null);
            }
        }

        if (this.isDesktop()) {
            this.state.staticMenuDesktopInactive = !this.state.staticMenuDesktopInactive;
        }
        else {
            this.state.staticMenuMobileActive = !this.state.staticMenuMobileActive;

            if (this.state.staticMenuMobileActive) {
                this.overlayOpen.next(null);
            }
        }
    }

    showProfileSidebar() {
        this.state.profileSidebarVisible = !this.state.profileSidebarVisible;
        if (this.state.profileSidebarVisible) {
            this.overlayOpen.next(null);
        }
    }

    showConfigSidebar() {
        this.state.configSidebarVisible = true;
    }

    isOverlay() {
        return this.config.menuMode === 'overlay';
    }

    isDesktop() {
        return window.innerWidth > 991;
    }

    isMobile() {
        return !this.isDesktop();
    }

    onConfigUpdate() {
        this.configUpdate.next(this.config);
    }


    LoginUser(inputdata:any){
        return this.http.post(this.apiurl+'/auth',inputdata)
      }

    RegisterUser(inputdata:any){
        return this.http.post(this.apiurl+'/register',inputdata)
      }

    //   getCurrentUser(token: string): Observable<any> {
    //     const headers = new HttpHeaders({
    //       Authorization: `Bearer ${token}`
    //     });
    //     return this.http.get<any>(this.apiurl+'/auth', { headers }).pipe(
    //         map(Response => Response.user)
    //       );
    //     }



    getDataFromToken(){
        let token = localStorage.getItem('token');
    
         if (token){
           let data = JSON.parse(window.atob(token.split('.')[1]));
           return data;
        }    
      }
      isLoggedIn(){
        let token = localStorage.getItem('token');
        if (token){
          return true ;
        }else{
          return false;
        }
      }
      logOut(){
        localStorage.removeItem('token');
        this.router.navigate(['/auth/login']);
      }
    
    
    }


