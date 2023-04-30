import { Component, ElementRef, Inject, Input, ViewChild } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';


@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent {
  
  todolist:any={};
public audio = new Audio();
  lists: any=[];
  status="completed"
  // @Input()
  timelist:any=[];
  rxTime = new Date();
  constructor(@Inject(MAT_DIALOG_DATA ) public data:any,private contservice:ConnectivityService, private router:RouteService,public dialog: MatDialog){
   this.timelist=data
  }

  @ViewChild('notificationSound') notificationSound?: HTMLAudioElement;

  playNotification() {
    this.notificationSound?.play();
  }
   

  ngOnInit():void{
    this.contservice.getuserdetail().subscribe({next:data=>{
      console.log(data);
      this.playNotification();
      this.todolist=data
      }})
      this.audio.src ="../../assets/tone/notification.mp3";
    this.audio.play();
    this.audio.play();
    
}

audios = {
  src: "https://webaudioapi.com/samples/metering/sounds/chrono.mp3",
  extension: "mp3",
  type: "audio/mp3"
}

snooze(){
  let hour = this.rxTime.getHours();
  let minuts = this.rxTime.getMinutes()+5;
  let time= hour + ":" + minuts;

  console.log(time); 
  console.log(this.timelist.contentid)
  console.log(this.timelist)
  this.timelist.time=time;
  this.contservice.editcontent(this.timelist?.contentid,this.timelist).subscribe(data=>{
    console.log(data)
    
    this.lists=data;
    this.audio.pause();
    this.closerdilog();
    
  })

}


closerdilog(){
  this.dialog.closeAll()
}

  


editcomplete(){
  console.log("Outside")
  this.timelist.status="completed";
  console.log(this.timelist?.status);
  console.log(this.timelist?.contentid)
  this.contservice.editcontent(this.timelist?.contentid,this.timelist).subscribe(data=>{
    console.log("inside")
    alert("Content edited Successfully")
    this.lists=data;
    this.audio.pause();
    this.closerdilog();
    
  })
}
}

  

