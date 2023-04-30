import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';
import { VoiceservicesService } from '../service/voiceservices.service';

@Component({
  selector: 'app-addfriend',
  templateUrl: './addfriend.component.html',
  styleUrls: ['./addfriend.component.css']
})
export class AddfriendComponent {
email:string="";
friendlist:any={};
viewbtn:boolean=true;
constructor(public service:VoiceservicesService, private routers:RouteService,private listservice:ConnectivityService, private Snack:MatSnackBar,public dialog: MatDialog){}
getfriendemail(){
   if(this.email.length>=3){
      this.listservice.getEmailsInfobySerach(this.email).subscribe({
        next:lists=>{
          this.friendlist=lists;
        }
       
   })
}else{
  this.friendlist=[];
}
}


addfriend(lists:any){
  console.log(lists)
  this.listservice.requestToFriend(lists).subscribe({next:data=>{
    this.viewbtn=false;
    this.openSnackBar();
    
  },error:e=>{
    this.openSnackBar();
  }});

}

openSnackBar() {
  this.Snack.open("Friend Request Sent Successfully", "Ok",{
       duration: 1000
});
}
}
