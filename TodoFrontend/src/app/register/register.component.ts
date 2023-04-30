import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  sent:boolean=true;
  sent1:boolean=false;
  imgUrl:string="../../assets/png-transparent-face-icon-user-icon-design-user-profile-share-icon-avatar-black-and-white-silhouette.png"
  fileToUplode:any=null;

  constructor(private formbuilder:FormBuilder, private listservice:ConnectivityService, private router:Router,private Routers:RouteService, private Snack:MatSnackBar){}

  registration=this.formbuilder.group({
    firstname:['',Validators.required],
    email:['',[Validators.required,Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]],
    username:['',Validators.required],
    profileimg:[''],
    password:['',[Validators.required,Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]]
    
  });

    // PasswordmustMatchValidator(fg: AbstractControl) {
    //   const passwordValue = fg.get("password")?.value;
      
    //   const confirmPasswordValue = fg.get("confirmPassword")?.value;
    //   if (!passwordValue || !confirmPasswordValue) {
    //     return null;
    //   }
    //   if (passwordValue != confirmPasswordValue) {
    //       return { mustMatch: false }
    //   }
    //   return null;
    // }
  

    get eventfirstName(){
      return this.registration.get("firstname")
    }
    
   
    
    get eventemail(){
      return this.registration.get("email")
    }
    
    get eventpassword(){
      return this.registration.get("password")
    }
    get eventprofileimg(){
      return this.registration.get("profileimg")
    }
    
   

    
    uploadfile(event:any){
      if(event.target.files){
        let file=new FileReader();
        file.readAsDataURL(event.target.files[0]);
        file.onload=(photo:any)=>{
          this.imgUrl=photo.target.result;
          console.log(photo.target.result);
          this.registration.patchValue({profileimg:photo.target.result});
          console.log(this.registration.value.profileimg);
          this.openSnackBar2();
          
        }
      }
    }

   

    adduser(){
      
            console.log(this.registration.value);
            
            this.listservice.adddata(this.registration.value).subscribe({next:data=>{
              console.log(data);
              this.sent=false;
              this.sent1=true;
              
              
            },
          error:e=>{
            alert("Failed to Register");
          }
          });
    }

    openSnackBar() {
      this.Snack.open("User Registered Successfully", "Ok",{
           duration: 1000
    });
    }

    openSnackBar2() {
      this.Snack.open("profile updated successfully", "Ok",{
           duration: 1000
    });
    }
    
    
  



   
    
}
