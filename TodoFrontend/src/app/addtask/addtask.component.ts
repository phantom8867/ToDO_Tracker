import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import {todolist} from '../Model/Todolist';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';
import { VoiceservicesService } from '../service/voiceservices.service';
import { TodopageComponent } from '../todopage/todopage.component';

@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.css']
})
export class AddtaskComponent {
  todolist:any={};
  text: string="";
  priority:string[]=["High Priority","Medium Priority","Low Priority"];


     constructor(public service:VoiceservicesService, private route:Router , private routers:RouteService,private listservice:ConnectivityService, private Snack:MatSnackBar,public dialog: MatDialog,public refresh:TodopageComponent){
      // this.service.init()
     }

  
  // startService(){
  //   this.service.start()
  // }

  // stopService(){
  //   this.service.stop()
  // }
  addtodo(){
    
    console.log(this.todolist)
    this.todolist.contentid=new Date().getTime();
    console.log("id"+this.todolist.contentid)

    if(this.todolist.title==null && this.todolist.date==null&& this.todolist.time==null){
      alert("Content is required")
      

      // this.routers.navigatetoview();
    }else{
      this.listservice.addcontent(this.todolist).subscribe({next:data=>{
        alert("Task Added Successfully")
        this.openSnackBar();
        this.todolist={};
        this.refresh.ngOnInit();
        this.dialog.closeAll();
        
        // this.route.navigateByUrl("/todopage");
      },error:e=>{
        alert("Failed to Add Task")
      }});

    }


  }
  closerdilog(){
    this.dialog.closeAll()
  }
  openSnackBar() {
    this.Snack.open("Task Added Successfully", "Ok",{
         duration: 1000
  });
  }

}
