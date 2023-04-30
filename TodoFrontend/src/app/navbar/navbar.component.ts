import { Component, HostListener } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { RouteService } from '../service/route.service';


@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  view:boolean=true;
    navbar:boolean=false;
    value:number=0;
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private authservice:AuthService, private router:Router,private service:RouteService) {}

  ngOnInit(){
  //  do{
  //   this.view=false;
      // this.authservice.logout();
  //  }
  this.buttonschange();
  }

  logOut(){

    this.authservice.logout();
    this.view=!this.view
    alert("Thank You.... You were logout now")
    this.buttonschange()
    this.router.navigateByUrl("/home");
  }

  buttonschange(){
    if(localStorage.getItem('jwt')== null){
      this.view=false;
     
  }else {
    this.view=true;
  }
  }

  rediecttohoem(){
    this.service.toHome();
  }


  home(){
    this.router.navigateByUrl("/home");

  }


  // @HostListener('window:scroll',['$event'])scrollFunc(e:Event){
    
  //   this.value=(e.target as Element).scrollHeight
  //   console.log(this.value)
  //   if(this.value<20){
  //      this.navbar=true;
  //   }else
  //   {
  //     this.navbar=false
  //   }
  // }
  header_variable=false;
  @HostListener("document:scroll")
  scrollfunction(){
    if(document.body.scrollTop>20 || document.documentElement.scrollTop>10){
      this.header_variable=true;
    }
    else{
      this.header_variable=false;
    }
  }
}
