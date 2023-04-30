import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { RouteService } from './route.service';

@Injectable({
  providedIn: 'root'
})
export class TodoGuard implements CanActivate {
  constructor(private service:AuthService,private router:RouteService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      console.log(this.service.isLoggedIn);
      // if(this.service.isLoggedIn){
      //  return true;
      // }
      // else
      // console.log(this.service.isLoggedIn);
      // alert("Invalid entry ")
      // this.router.toHome();
      return false;
    }
    
  
}
