import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-adminmainpage',
  templateUrl: './adminmainpage.component.html',
  styleUrls: ['./adminmainpage.component.css']
})
export class AdminmainpageComponent {
  
  panelOpenState = false;
  showFiller = false;
  getusers=false;
  getuser:any={};
  date:any;
  getsuggestion=false;
  constructor(private router:Router,private listservice:ConnectivityService){}

  ngOnInit():void{
    this.date=new Date().toDateString()
    this.listservice.getalluser().subscribe({next:data=>{
      console.log(data);
      this.getuser=data
      }})
}

activat(){
  this.getsuggestion=false;
  this.getusers=true;
 
}
activat1(){
  this.getusers=false;
  this.getsuggestion=true;
}
}