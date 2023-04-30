import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DetailedViewComponent } from '../detailed-view/detailed-view.component';
import { EdittodolistComponent } from '../edittodolist/edittodolist.component';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.css']
})
export class ArchiveComponent {
 
  @Input()
  dates:any
  @Input()
  cat?:string;
  @Input()
  cont:any=[];
  archivelist:any=[];
  constructor(private router:Router,private listservice:ConnectivityService,public dialog: MatDialog){}
  ngOnInit():void{
    // this.cont=[];
  
    // this.listservice.getdatabystatuscomplete().subscribe({next:data=>{
    //   console.log(data);
    //  this.cont=data;
    //   },
    // error : err=>{
    //   alert("Failed to fetch All Todo List")
    // }});
}


openDialog2(enterAnimationDuration: string, exitAnimationDuration: string, data:any): void {
  this.dialog.open(EdittodolistComponent,{
    width: '1000px',
    height:'580px',
    enterAnimationDuration,
    exitAnimationDuration,
    data

  });
}

openDialog3(enterAnimationDuration: string, exitAnimationDuration: string, data:any): void {
  this.dialog.open(DetailedViewComponent,{
    width: '582px',
    height:'750px',
    enterAnimationDuration,
    exitAnimationDuration,
    data

  });
}

delete(id:any){
  this.listservice.delete(id).subscribe({next:data=>{
    alert("The Content has been deleted")
      this.cont=data;
  }})
}
}
