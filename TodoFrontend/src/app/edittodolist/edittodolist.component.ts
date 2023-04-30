import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, CanDeactivate } from '@angular/router';
//import { CanComponentDeactivate } from '../can-deactivate.guard';

import {todolist} from '../Model/Todolist';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';
import { Observable } from 'rxjs';
import { CanComponentDeactivate } from '../can-deactivate.guard';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
@Component({
  selector: 'app-edittodolist',
  templateUrl: './edittodolist.component.html',
  styleUrls: ['./edittodolist.component.css']
})
export class EdittodolistComponent implements CanComponentDeactivate {
  todolist:any={};
  constructor(private fb: FormBuilder,private formservice:ConnectivityService,private routeService:RouteService,private activatedRoute:ActivatedRoute,private noteservice:ConnectivityService,@Inject(MAT_DIALOG_DATA ) public data:any,private contservice:ConnectivityService, private router:RouteService,public dialog: MatDialog){
    this.todolist=data
  }
  
  
  lists: any={}
  edit:any;
  priority:string[]=["High Priority","Medium Priority","Low Priority"];
  submit:boolean=false;

  canDeactivate(): boolean  {
    if (!this.submit) {
       this.submit=confirm('Are you sure you want to discard your changes?');
    }
     return this.submit;
  }

  ngOnInit():void{
    // this.activatedRoute.paramMap.subscribe(params => {
    //   let id = params.get("id")?? 0;
    //   console.log(params);
    //   this.formservice.getdatabyid(+id).subscribe(data => {
    //     this.todolist = data;
    //     console.log(this.todolist);
    //   })
    //   console.log(this.todolist);
    // });
  }

  editCont(){
    if(this.todolist.status!="Active"){
      this.todolist.status="Active";
    }
    this.formservice.editcontent(this.todolist?.contentid,this.todolist).subscribe(data=>{
      alert("Content edited Successfully")
      this.lists=data;
      this.closerdilog();
      this.routeService.navigatetoview();
    })
  }

  closerdilog(){
    this.dialog.closeAll()
  }


}
