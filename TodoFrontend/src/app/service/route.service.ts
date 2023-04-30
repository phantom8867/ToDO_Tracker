import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private routerService:Router) { }

  navigatetoLogin(){
    this.routerService.navigate(["/login"])
  }

  toHome(){
    this.routerService.navigate(["/home"]);

  }
  navigatetoview(){
    this.routerService.navigate(["/todopage"])
  }

  navigatetosucessflly(){
    this.routerService.navigate(["successmsg"]);
  }
  navigatetoreset(){
    this.routerService.navigate(["successmsg"]);
  }

}
