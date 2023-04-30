import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-date',
  templateUrl: './date.component.html',
  styleUrls: ['./date.component.css']
})
export class DateComponent {
  cont:any=[];
  const:any=[];
  date:any;
  todo:any=[];
  filterdate:any=[];
  details:any;
  time = new Date();
  rxTime = new Date();
  subscription: Subscription | undefined;

  constructor(private router:Router,private listservice:ConnectivityService,private rout:ActivatedRoute){}
  ngOnInit():void{
    this.listservice.getdata().subscribe({next:data=>{
      console.log(data);
      this.cont=data},
    error : err=>{
      alert("Failed to fetch All Details")
    }
  })
this.rout.paramMap.subscribe(note =>{
  let ids=note.get('date') ?? ""
  console.log(ids)
  })

  this.date=new Date().toISOString().split("T").splice(0,1);
    this.todo=this.cont;
    console.log(this.todo)
    for(let lists of this.todo){
      if(lists.date==this.date){
        this.filterdate.push(lists)
      }
    }
    console.log(this.filterdate)
    console.log(this.cont);
    console.log(new Date().toISOString().split("T").splice(0,1));
}
searchByDate(){
  this.filterdate=[];
  this.date;
  console.log(this.date);
  this.todo=this.cont;
  console.log(this.todo)
  for(let lists of this.todo){
    if(lists.date==this.date){
      this.filterdate.push(lists)
    }
  }
  console.log(this.filterdate)
}
}
