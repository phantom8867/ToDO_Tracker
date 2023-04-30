import { Component, Input } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../service/auth.service';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {
  logins:boolean=true;
  logout:boolean=false;
  next:boolean=true;
  @Input()
  email:any;

  constructor(private fb: FormBuilder,private authservice:AuthService,private listservice:ConnectivityService,private router:Router, private navbar:NavbarComponent, private activateRoute:ActivatedRoute){}

  reset=this.fb.group({
    
    password:['',[Validators.required,Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
    confirmpassword:['',[Validators.required,Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]]
  },{Validators:[this.PasswordmustMatchValidator]});

  PasswordmustMatchValidator(fg: AbstractControl) {
    const passwordValue = fg.get("password")?.value;
    
    const confirmPasswordValue = fg.get("confirmPassword")?.value;
    if (!passwordValue || !confirmPasswordValue) {
      return null;
    }
    if (passwordValue != confirmPasswordValue) {
        return { mustMatch: false }
    }
    return null;
  }

  get password(){return this.reset.get('confirmpassword');}
  get confirmpassword(){return this.reset.get('password');}

  respons:any;

  // ngOnInit():void{
  //   this.activateRoute.paramMap.subscribe(params=>{
  //     let id =params.get("id")?? 0;
  //     console.log(params);
  //     console.log(id);
  //     this.email=id})}

  resetpassword(){
    this.logout=true;
    this.listservice.updatepassword(this.email,this.reset.value).subscribe(data=>{
      this.logout=true;

    })
  }

}
