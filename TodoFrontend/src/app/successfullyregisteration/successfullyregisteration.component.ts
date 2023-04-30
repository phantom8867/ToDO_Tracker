import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-successfullyregisteration',
  templateUrl: './successfullyregisteration.component.html',
  styleUrls: ['./successfullyregisteration.component.css']
})
export class SuccessfullyregisterationComponent implements OnInit {
  todolist:any={};
  verify:any=null;

  constructor(private connection:ConnectivityService, private activatedRoute:ActivatedRoute){
  
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(param=>{
      let id=param.get("id")?? 0;
      console.log(param);
      this.verify="OK";
      this.connection.verify(id,this.verify).subscribe(data=>{
        this.todolist=data;
        console.log(this.todolist);
      })
    })

    
  }



}
