import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LayoutService } from 'src/app/layout/service/app.layout.service';

@Injectable({
  providedIn: 'root'
})
export class PassGuard implements CanActivate {
  constructor(private auth : LayoutService , private router : Router){}
 
  canActivate(){

  if (this.auth.isLoggedIn()==true){
    return true ;
  }else{
    this.router.navigate(['/auth/login']);
    return false;
  }

}
}
