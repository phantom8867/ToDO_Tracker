import { Component, Injectable } from '@angular/core';
 
import { ConnectivityService } from '../service/connectivity.service';
import { Router } from '@angular/router';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {todolist} from '../Model/Todolist';
import { ViewTodoListComponent } from '../view-todo-list/view-todo-list.component';
import { ViewProfileComponent } from '../view-profile/view-profile.component';
import { AuthService } from '../service/auth.service';
import { Observable } from 'rxjs';
import { CanComponentDeactivate } from '../can-deactivate.guard'; 
import { NavbarComponent } from '../navbar/navbar.component';
import { AddtaskComponent } from '../addtask/addtask.component';
import { AddfriendComponent } from '../addfriend/addfriend.component';
import { ShowfriendsComponent } from '../showfriends/showfriends.component';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-todopage',
  templateUrl: './todopage.component.html',
  styleUrls: ['./todopage.component.css']
})
export class TodopageComponent   {
  date:any;
  filterdate:any=[];
  panelOpenState = false;
  cat?:string;
  requests:any=[];
  todo:any=[];
  lengths:number=0;
  length2:number=0;
  length3:number=0;
  notification:any;
  viewnotify:boolean=false;
  filter:any=[];
  archive:boolean=false;
  archivelist:any=[];
  addtodo:any=[];
  search:boolean=false;
  priority:string="";

  Highpriority:string="Highpriority";
  Mediumpriority:string="Mediumpriority";
  Lowpriority:string="LowMediumpriority";
  cont:any=[];
  // rxTime=new Date();

  todolist1:any={};
  lists: any={}
  constructor(private router:Router,private listservice:ConnectivityService,public dialog: MatDialog, private as:AuthService,private nav:NavbarComponent){}
  
  
  
  ngOnInit():void{
    // let hour = this.rxTime.getHours();
    // let minuts = this.rxTime.getMinutes()+5;
    // let time= hour + ":" + minuts;
    // console.log(time+"added");
    this.priority="All";
    this.nav.buttonschange();
    this.archive=false;
    this.listservice.getuserdetail().subscribe({next:data=>{
      console.log(data)
      this.todolist1=data;

      this.requests=data.request;
      if(this.requests!=null||undefined){
        this.length2=this.requests.length??0;
      }
      console.log(this.requests)
      
      console.log(this.length2)
      this.addtodo=data.requesttodolist;
      console.log(this.addtodo)
      if(this.addtodo!=null||undefined){
        this.length3=this.addtodo.length??0;
      }
      
      console.log(this.length3)
      console.log(this.todolist1)
    },
    error : err=>{
      alert("Failed to fetch All User Details")

    }})
    this.cont=[];
   this.date=new Date().toISOString().split("T").splice(0,1);
   console.log(this.date);
    this.listservice.getdatabystatusactive().subscribe({next:data=>{
      console.log(data);
      this.filterdate=data
      for(let lists of data){
        if(lists.date==this.date){
          this.cont.push(lists)
          console.log(this.cont)
        }
      }
      this.filter=this.cont
      
      },
    error : err=>{
      alert("Failed to fetch All Todo List")

    }});


  }

  openDialog1(enterAnimationDuration: string, exitAnimationDuration: string): void {
    this.dialog.open(AddtaskComponent,{
      width: '1000px',
      height:'580px',
      enterAnimationDuration,
      exitAnimationDuration,
      

    });
    // this.dialog.afterAllClosed.subscribe(data=>{
    //   console.log("dilog closed")
    //   window.location.href="https://localhost:4200/todopage";
    // })
  }


filterbydate(){
  if(this.cont.date==new Date){}
}
searchByDate(dates:any){
 this.cont=[];
  this.date=dates
  console.log(this.date);
  
  console.log(this.filterdate)
  for(let lists of this.filterdate){
    if(lists.date==this.date){
      this.cont.push(lists)
    }
  }
  this.filter=this.cont;
  console.log(this.cont)
}

highpri(){

  this.cont=[]
  for(let lists of this.filter){
    if(lists.priority=="High Priority" && lists.date==this.date){
      this.priority="High Priority";
        this.cont.push(lists)
    }
  }


 
  console.log(this.cont)
//   this.listservice.getdatabypriority("High Priority").subscribe({next:data=>{
//     console.log(data)
//   },
// error: err=>{alert("Failed to fetch high priority todo list")}})
  
}

openDialog(enterAnimationDuration: string, exitAnimationDuration: string): void {
  this.dialog.open(ViewProfileComponent, {
    width: '550px',
    height:'200px',
    enterAnimationDuration,
    exitAnimationDuration,
  });
}
openDialog3(enterAnimationDuration: string, exitAnimationDuration: string): void {
  this.dialog.open(AddfriendComponent,{
    width: '1000px',
    height:'580px',
    enterAnimationDuration,
    exitAnimationDuration,
  });
}
openDialog4(enterAnimationDuration: string, exitAnimationDuration: string): void {
  this.dialog.open(ShowfriendsComponent,{
    width: '1000px',
    height:'580px',
    enterAnimationDuration,
    exitAnimationDuration,
  });
}
 
mediumpri(){
  this.cont=[]
  for(let lists of this.filter){
    if(lists.priority=="Medium Priority"&& lists.date==this.date){
        this.cont.push(lists)
        this.priority="Medium Priority";
    }
  }
  console.log(this.cont)
 
//   this.listservice.getdatabypriority("Medium Priority").subscribe({next:data=>{
//     console.log(data)
//   },
// error: err=>{alert("Failed to fetch medium priority todo list")}})
  
}
lowpri(){
  this.cont=[];
  for(let lists of this.filter){
    if(lists.priority=="Low Priority" && lists.date==this.date){
        this.cont.push(lists)
        this.priority="Low Priority";
    }
  }
  console.log(this.cont)
 
//   this.listservice.getdatabypriority("Low Priority").subscribe({next:data=>{
//     console.log(data)
//   },
// error: err=>{alert("Failed to fetch medium low todo list")}})  
}

pending(){
  this.archivelist=[];
  this.cat="Pending"
  this.archive=true;
  this.listservice.getdatabystatuspending().subscribe({next:data=>{
    console.log(data);
    this.cont=data;
    this.filter=data;
    
  }})
}

complited(){
  this.archivelist=[];
  this.cat="Complited"
  this.archive=true;
  this.listservice.getdatabystatuscomplete().subscribe({next:data=>{
    console.log(data);
    this.cont=data;
    this.filter=data;
     
  }})
}

notifyInNav(notify:any){
  this.notification=notify
  this.viewnotify=true;

}

// onSearchTextChanged(notetitle:string){
//   this.listservice.searchbytitle(notetitle).subscribe({next:data=>{
//     this.filter=data;
//     this.cont=data;
//     alert("Search is done") 
//     console.log(this.cont);
//   },error: err=>{
//   alert("Search Not Found")}})
// }

  todolist:todolist={};

  submit:boolean=false;

  canDeactivate(): boolean  {
    if (!this.submit) {
       this.submit=confirm('Are you sure you want to logout?');
    }
     return this.submit;
  }

  logout(){
    this.as.logout();
    this.nav.buttonschange();
    this.canDeactivate();
    this.router.navigateByUrl("/home");
  }
  searchbar(){
    this.search=!this.search;
  }

  onSearchText(notes:any){
    this.cont=[];
    this.filter=[];
    
    this.listservice.getdatabyserach(notes).subscribe(datas=>{
    console.log(datas);
    this.filter=datas;
    this.cont=[datas];
    console.log(this.cont)
    

  })
}


request(){
  this.listservice.getrequestlist(this.todolist1.email).subscribe(data=>{
    
    // this.length2=data.length
    // this.length=this.length2+this.length;

    // console.log(length);
    this.requests=data;
    this.length2=this.requests.length

  })
}
requesttodolist(){
  this.listservice.getnotificationtodolist(this.todolist1.email).subscribe(data=>{
    console.log(data)
    this.addtodo=data;
    this.length3=this.addtodo.length
    // this.length=this.length3+this.length;
  })
}


acceptfriend(datas:any){
  this.listservice.acceptByFriend(datas).subscribe({next:data=>{
    console.log(data)
    this.request();
//     this.listservice.getrequestlist(datas).subscribe({next:dataa=>{
//      console.log(dataa)
//      this.requests=dataa;
//    },
//  error: err=>{
//    alert("something went wrong1")
//  }})
    
  }})
}
rejectfriend(datas:any){
 this.listservice.refusedFriendRequest(datas).subscribe({next:data=>{
        console.log(data)
        this.request();
    //     this.listservice.getrequestlist(datas).subscribe({next:dataa=>{
    //      console.log(dataa)
    //      this.requests=dataa;
    //    },
    //  error: err=>{
    //    alert("something went wrong")
    //  }})

       
 },
 error: err=>{
   alert("something went wrong")
 }})
}




addlist(todo:any){
  console.log(todo)
  this.listservice.accepttodotask(todo,"Accept").subscribe({next:data=>{
      this.requesttodolist();
      this.ngOnInit()
  }})
}

cancel(todo:any){
  console.log(todo)
  this.listservice.accepttodotask(todo,"Cancel").subscribe({next:data=>{
    this.requesttodolist();
    this.ngOnInit()
  }})
}

  onprioritytext(priority:any){
    console.log(priority);
    if(priority=="High Priority"){
      this.highpri();
    }else if(priority=="Medium Priority"){
      this.mediumpri();
    }else if(priority=="Low Priority"){
      this.lowpri();
    }else{
      this.ngOnInit();
    }
  }
 
}


