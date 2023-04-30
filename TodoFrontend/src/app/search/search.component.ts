import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
title:string="";

@Input()
lists:any=[]
@Output()
searchTextChanged: EventEmitter<string> = new EventEmitter<string>();
hide:boolean=false;
constructor(private activateRoute:ActivatedRoute, private formservice:ConnectivityService){}


ngOnInit(){
  console.log(this.lists);
}

search(){
  // this.formservice.getdatabyserach(this.title).subscribe(datas=>{
  //   console.log(datas);
  //   this.lists=datas; 
    this.searchTextChanged.emit(this.title);
    // this.hide=true;
    // console.log(this.lists)
  }
  

deleteCont(){
  this.formservice.delete(this.lists.contentid).subscribe(data=>{
    alert("The Content has been deleted")
    this.lists=data;
    this.hide=true;
    // this.routeService.navigatetoview();
  })
}
}


