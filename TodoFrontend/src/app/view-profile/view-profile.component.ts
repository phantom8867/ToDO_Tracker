import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent {

  todolist1:any={};
  constructor(private router:Router,private listservice:ConnectivityService,public dialog: MatDialog){}

  ngOnInit():void{
    this.listservice.getuserdetail().subscribe({next:data=>{
      console.log(data);
      this.todolist1=data
      }})
  }

  closerdilog(){
    this.dialog.closeAll()
  }
}
