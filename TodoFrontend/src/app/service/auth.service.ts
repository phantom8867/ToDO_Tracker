import { Injectable } from '@angular/core';

import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }
  isLoggedIn: boolean=false;

  login(logdetails:any){
    
    this.isLoggedIn = logdetails===localStorage.getItem('jwt');
    console.log(this.isLoggedIn)
    return this.isLoggedIn;
  }
  logout(){
     localStorage.removeItem('jwt');
  }
  

}
