import { Component } from '@angular/core';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
email:any;
otp:any;
next:boolean=true;

constructor(private connection:ConnectivityService){}

ngOnInit(){

}

sendmail(){
  this.connection.getotp(this.email).subscribe({next:data=>{
    this.otp=data;
    if(this.otp!=0){
      this.next=true;
    }
  }})
}

}
