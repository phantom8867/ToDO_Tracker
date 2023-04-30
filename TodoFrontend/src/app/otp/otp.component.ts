import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-otp',
  templateUrl: './otp.component.html',
  styleUrls: ['./otp.component.css']
})
export class OTPComponent {

  i1?:string;
  i2?:string;
  i3?:string;
  i4?:string;
  i5?:string;
  i6?:string;
  reset:boolean=false;

  otp2:any;
  @Input()
  otp?:number;
  @Input()
  email?:string;

  constructor(private router:Router, private connection:ConnectivityService){

  }
  ngOnInit(){

    console.log(this.otp);
  }

  submit(){
    //  let otp1 = this.i1!.concat(this.i2!,this.i3!,this.i4!,this.i5!,this.i6!);
    // this.otp=parseInt(otp1!)
    console.log(this.otp);

    if(this.otp2==this.otp){
      this.reset=true;
      console.log(this.email)  
    }else{
      alert("Invalid OTP")
    }

  }

  sendmail(){
    this.connection.getotp(this.email).subscribe({next:data=>{
      console.log(data)
      this.otp=data;
     
    }})
  }
  

}
