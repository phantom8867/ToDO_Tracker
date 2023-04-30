import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';
import { CanComponentDeactivate } from '../can-deactivate.guard';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements CanComponentDeactivate {
  imgUrl:string="../../assets/png-transparent-face-icon-user-icon-design-user-profile-share-icon-avatar-black-and-white-silhouette.png"
  fileToUplode:any=null;
  todolist1:any={};
  status:string="";
  alldatalist:any={};
  filter:any=[];
  constructor(private formbuilder:FormBuilder, private listservice:ConnectivityService, private router:Router,private Routers:RouteService){}
  ngOnInit():void{
    this.listservice.getuserdetail().subscribe({next:data=>{
      console.log(data);
      this.todolist1=data
      }})
  }
  registration=this.formbuilder.group({
    firstname:[''],
    profileimg:[''],
    phonenumber:['',Validators.pattern('[7-9]{1}[0-9]{9}')],
    password:['',[Validators.required,Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/)]],
  })

  

    get eventfirstname(){
      return this.registration.get("firstname")
    }
    
    get eventphonenumber(){
      return this.registration.get("phonenumber")
    }
    
    get eventprofileimg(){
      return this.registration.get("profileimg")
    }
  
    get eventpassword(){
      return this.registration.get("password")
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
          
        }
      }
    }

    submit:boolean=false;

  canDeactivate(): boolean  {
    if (!this.submit) {
       this.submit=confirm('Are you sure you want to discard your changes?');
    }
     return this.submit;
  }

    edituser(){
      
      console.log(this.registration.value);
      if(this.registration.value.firstname==''){
        this.registration.value.firstname=this.todolist1.firstname;
      }
    
      if(this.registration.value.password==''){
        this.registration.value.password=this.todolist1.password;
      }
      if(this.registration.value.profileimg==''){
        this.registration.value.profileimg=this.todolist1.profileimg;
      }
      if(this.registration.value.phonenumber==''){
        this.registration.value.phonenumber=this.todolist1.phonenumber;
      }
      console.log(this.registration.value);
      this.listservice.edituser(this.registration.value).subscribe({next:data=>{
        console.log(data);
        alert("Profile updated succesfully");
        this.Routers.navigatetoview();
      },
    error:e=>{
      alert("Failed to Register");
    }
    });
}
 getallstatus(){
  this.status="All";
  this.listservice.getuserdetail().subscribe({next:data=>{
    console.log(data);
    this.alldatalist=data;
    console.log(this.alldatalist)
  }})
}
 activestatus(){

  this.alldatalist=[]
  for(let lists of this.filter){
    if(lists.status=="Active"){
      this.status="Active";
        this.alldatalist.push(lists);
    }
  }
}
}
