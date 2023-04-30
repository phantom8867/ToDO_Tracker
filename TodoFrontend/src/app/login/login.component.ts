import { Component } from '@angular/core';
import {  AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Route, Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../service/auth.service';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';
import { TodoGuard } from '../service/todo.guard';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  isLogged?:boolean;
  next:boolean=false;
  logins:boolean=true;
  logout:boolean=false;
  email:any;
  otp:any;
  respons:any;
  constructor(private fb: FormBuilder,private authservice:AuthService,private listservice:ConnectivityService,private router:RouteService, private navbar:NavbarComponent, private Snack:MatSnackBar,private guard:TodoGuard, private route:Router){}
  
  log=this.fb.group({
    email:['',Validators.required],
    password:['',[Validators.required,Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/)]]
  });
  
  get eventemail(){return this.log.get('email');}
  get eventpassword(){return this.log.get('password');}

  login()
  {
    this.listservice.logins(this.log.value).subscribe({next: 
      data=>{
        this.respons=data
          localStorage.setItem("jwt",this.respons.token);
          localStorage.setItem("verify",this.respons.verify);
          localStorage.setItem("status",this.respons.role);          
          this.authservice.login(this.respons.token)
          this.navbar.buttonschange();
          this.openSnackBar();
          if(this.respons.role==="admin"){
            this.route.navigateByUrl("/admin");
          }else{
            this.router.navigatetoview();
          }
        },error:e=>{
          alert("Email or Password Is Wrong");
        }  
    });
  }
  openSnackBar() {
    this.Snack.open("Login Successfully", "Ok",{
       duration: 1000
  });
  }
  sendmail(){  
    this.listservice.getotp(this.email).subscribe({next:data=>{
      this.otp=data;
      if(this.otp!=0){
        this.next=true;
      }
    }})
  }
  forgot(){
    this.logins=false;
    this.logout=true;
  }

  backto(){
    this.logins=!this.logins;
    this.logout=!this.logout;
  }
}
