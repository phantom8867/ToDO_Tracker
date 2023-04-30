import { Component, Inject } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { EdittodolistComponent } from '../edittodolist/edittodolist.component';
import { ConnectivityService } from '../service/connectivity.service';
import { RouteService } from '../service/route.service';

@Component({
  selector: 'app-detailed-view',
  templateUrl: './detailed-view.component.html',
  styleUrls: ['./detailed-view.component.css']
})
export class DetailedViewComponent {
  ids:any;
  lists: any={};
  isOpen = false;
  friendlist:any;
  userinfo:any;

  emails:any;
  constructor(private activateRoute:ActivatedRoute, private formservice:ConnectivityService,@Inject(MAT_DIALOG_DATA ) public data:any,private contservice:ConnectivityService, private router:RouteService,public dialog: MatDialog){
    this.lists=data;
  }

  ngOnInit():void{
    this.contservice.getuserdetail().subscribe({next:data=>{
      console.log(data)
      this.userinfo=data;
      this.emails=this.userinfo.email;
      this.friendlist=this.userinfo.friends
      console.log(this.emails)
    },
    error : err=>{
      alert("Failed to fetch All User Details")

    }})

     this.contservice.getfriendslist(this.emails).subscribe({next:dataa=>{
        console.log(dataa)
        
     }
    });
    }
    
    editcontent1(list:any){
      list.status="completed";
    this.contservice.editcontent(list.contentid,list).subscribe(data=>{
      alert("Content edited Successfully")
    })
   }
    editcontent(list:any){
      list.status="pending";
    this.contservice.editcontent(list.contentid,list).subscribe(data=>{
      alert("Content edited Successfully")
    })

   }
   target:any= {
    clicked: 0,
    currentFollowers: 90,
    btn: document.querySelector("a.btn"),
    fw: document.querySelector("span.followers")
  };
   follow:any = () => {
    this.target.clicked += 1;
    this.target.btn.innerHTML = 'Following <i class="fas fa-user-times"></i>';
    if (this.target.clicked % 2 === 0) {
        this.target.currentFollowers -= 1;
        this.target.btn.innerHTML = 'Follow <i class="fas fa-user-plus"></i>';
    }
    else {
        this.target.currentFollowers += 1;
    }
    this.target.fw.textContent = this.target.currentFollowers;
    this.target.btn.classList.toggle("following");
  };
  openDialog2(enterAnimationDuration: string, exitAnimationDuration: string, data:any): void {
    this.dialog.open(EdittodolistComponent,{
      width: '1000px',
      height:'580px',
      enterAnimationDuration,
      exitAnimationDuration,
      data
    });
  }
  closerdilog(){
    this.dialog.closeAll()
  }


  sharetodo(emils:any,todolist?:any){
    this.contservice.shareTodolistToFriend(emils,todolist).subscribe(data=>{
      alert("Task sent Successfully")
    })
  }
}
