
import { Component, EventEmitter, Input, OnInit, Output ,ViewChild,TemplateRef,
  AfterViewInit,ViewContainerRef ,OnDestroy} from '@angular/core';
import { MatDialog, MatDialogRef} from '@angular/material/dialog';
import { map, share, Subscription, timer } from 'rxjs';
import { AddtaskComponent } from '../addtask/addtask.component';
import { DetailedViewComponent } from '../detailed-view/detailed-view.component';
import { EdittodolistComponent } from '../edittodolist/edittodolist.component';
import { NotificationComponent } from '../notification/notification.component';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';
import { TodopageComponent } from '../todopage/todopage.component';
import {Overlay, OverlayRef} from '@angular/cdk/overlay';
import {TemplatePortal} from '@angular/cdk/portal';

@Component({
  selector: 'app-view-todo-list',
  templateUrl: './view-todo-list.component.html',
  styleUrls: ['./view-todo-list.component.css']
})
export class ViewTodoListComponent  {

  title:string="";
  date:any={};
  todo:any=[];
  filterdate:any=[];
  alert:any;
  details:any;
  timelist:any={};
  @Input()
  cont?:any[];
  @Input()
  search:any;
  @Input()
  dates?:any;
  @Input()
  priority?:any;
  notify:boolean=false

  

  @Output()
  searchText: EventEmitter<string> = new EventEmitter<string>();
  @Output()
 notifications:EventEmitter<boolean>=new EventEmitter<boolean>();

  searchlist?:any[];
  today = new Date();
  intervalId:any;
  time:any;
  serach:boolean=false;
  hide:boolean=false;
  interval:any;
  
  countdown:number=60;
  isOpen = false;
  notification:boolean=false;
  rxTime = new Date();
  subscription: Subscription | undefined;
  
  constructor(private contservice:ConnectivityService, private router:RouteService,public dialog: MatDialog ,public priorities:TodopageComponent,private _overlay: Overlay, private _viewContainerRef: ViewContainerRef){
   
  }
  

  
  openDialog(enterAnimationDuration: string, exitAnimationDuration: string, data:any): void {
    this.dialog.open(NotificationComponent,{
      width: '550px',
      height:'700px',
      enterAnimationDuration,
      exitAnimationDuration,
      data

    });
  }

  openDialog1(enterAnimationDuration: string, exitAnimationDuration: string): void {
    this.dialog.open(AddtaskComponent,{
      width: '1000px',
      height:'580px',
      enterAnimationDuration,
      exitAnimationDuration,
     

    });
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

  closerDialog():void{
    this.dialog.closeAll();
  }
   image(){
    let avatar=["cat","deer","gorilla","koala","lion","meerkat","owl","panda","rhinocerous","tiger"]
    let random=Math.floor(Math.random()*avatar.length);
    return avatar[random]
   }

   searchs(){
  
    this.searchText.emit(this.title);  
   }

   outputpriority(prio:string){
    if(prio=="High Priority"){
      this.priorities.highpri();
    }else if(prio=="Medium Priority"){
      this.priorities.mediumpri();
    }else if(prio=="Low Priority"){
      this.priorities.lowpri();
    }else{
      this.priorities.ngOnInit();
    }
     
   }


   searchdate(date:any){
    
    this.priorities.searchByDate(date);
   }
  //  onSearchTextChanged(notes:string){
    
  //     this.contservice.getdatabyserach(notes).subscribe(datas=>{
  //       console.log(datas);
  //       this.searchlist=datas; 
  //       this.hide=true;
        
        // this.filterdate=datas;
        
// console.log(notes);
// this.searchtext=notes;
//         this.searchText.emit(this.searchtext);
         
//         console.log(this.searchText)
//   })
// }



   ngOnInit():void{
    
    console.log(this.filterdate)
    this.contservice.getuserdetails().subscribe({next:data=>{
      this.details=data;
      console.log(this.details);
      
    },
    error : err=>{
      alert("Failed to fetch All User Details")

    }})
    
    console.log(this.cont);
    console.log(new Date().toISOString().split("T").splice(0,1));
    
    this.intervalId = setInterval(() => {
      this.time = new Date();
    }, 1000);

    // Using RxJS Timer
    this.subscription = timer(0, 1000)
      .pipe(
        map(() => new Date()),
        share()
      )
      .subscribe(time => {
        let hour = String(this.rxTime.getHours());
        let minuts = String(this.rxTime.getMinutes());
        
        if(hour.length<=1){
           hour='0'+hour;
        }
        if(minuts.length<=1){
          minuts='0'+minuts;
       }
        //let a = time.toLocaleString('en-US', { hour: 'numeric', hour12: true });
        let NewTime = hour + ":" + minuts 
        this.date=new Date().toISOString().split("T").splice(0,1);
        this.todo=this.cont;
        // console.log(this.todo)
        for(let lists of this.todo){
          // console.log(lists.date)
          // console.log(this.date)
            
          if(lists.date==this.date){

           
            // console.log(lists.time)
            // console.log(NewTime)
            if(this.notification==false){
            if(NewTime==lists.time)
            {
              console.log("Passed")
              this.timelist={}
              this.timelist=lists
              // // this.notifylist.emit(this.timelist)
              // // console.log("Passed")
              this.openDialog('3000ms', '1500ms',this.timelist);
              this.notification=true;
              this.interval=setInterval(()=>{
                this.countdown--
                if(this.countdown==0){
                  this.notification=false;
                  this.closerDialog() 
                }
              },1000)
              console.log(this.notification);
              
            }
        }
            
            this.filterdate.push(lists)
          }
        }
        this.rxTime = time;
      });
      console.log(this.notification+' end')
    }

    editcontent(list:any){
       list.status="pending";
     this.contservice.editcontent(list.contentid,list).subscribe(data=>{
       alert("Content edited Successfully")
     })
 
    }
  } 
  
  
  
  
  


  function openDialog(enterAnimationDuration: any, string: any, exitAnimationDuration: any, string1: any) {
    throw new Error('Function not implemented.');
  }






