import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-getpriority',
  templateUrl: './getpriority.component.html',
  styleUrls: ['./getpriority.component.css']
})
export class GetpriorityComponent {
 
  constructor(private router:Router,private listservice:ConnectivityService,private rout:ActivatedRoute){
         this.rout.paramMap.subscribe(note =>{
      let ids=note.get('Highpriority') ?? ""
      console.log(ids)
     
      })
      this.rout.paramMap.subscribe(note =>{
        let ids=note.get('Mediumpriority') ?? ""
        console.log(ids)
       
        })
        this.rout.paramMap.subscribe(note =>{
          let ids=note.get('Lowpriority') ?? ""
          console.log(ids)
         
          })
    }
  }

