import { Component } from '@angular/core';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-showfriends',
  templateUrl: './showfriends.component.html',
  styleUrls: ['./showfriends.component.css']
})
export class ShowfriendsComponent {
  requestlist:any;
  friendlist:any;
  requestedlist:any;

  constructor(private getfriend:ConnectivityService){

  }

  ngOnInit(){
    this.getfriend.getuserdetail().subscribe({next:data=>{
      this.friendlist=data.friends;
      this.requestedlist=data.requested;
      this.requestlist=data.request;
      console.log(this.friendlist);
      console.log(this.requestedlist);
      console.log(this.requestlist);
      console.log(data);
       
    },error:err=>{
      alert("Something went wrong")
    }})
  }
  acceptfriend(datas:any){
     this.getfriend.acceptByFriend(datas).subscribe({next:data=>{
       console.log(data)
       this.getfriend.getrequestlist(datas).subscribe({next:dataa=>{
        console.log(dataa)
        this.requestlist=dataa;
      },
    error: err=>{
      alert("something went wrong1")
    }})
       
     }})
  }
  rejectfriend(datas:any){
    this.getfriend.refusedFriendRequest(datas).subscribe({next:data=>{
           console.log(data)
           this.getfriend.getrequestlist(datas).subscribe({next:dataa=>{
            console.log(dataa)
            this.requestlist=dataa;
          },
        error: err=>{
          alert("something went wrong")
        }})

          
    },
    error: err=>{
      alert("something went wrong")
    }})
  }
  unfriend(datas:any){
    this.getfriend.DeleteFriend(datas).subscribe({next:data=>{
      console.log(data)
      this.getfriend.getfriendslist(datas).subscribe({next:dataa=>{
        console.log(dataa)
        this.friendlist=dataa;
      },
    error: err=>{
      alert("something went wrong")
    }})
    },
  error: err=>{
    alert("something went wrong")
  }})
  }

  cancelrequest(datas:any){
    this.getfriend.cancelRequest(datas).subscribe({next:data=>{
      console.log(data)
      this.getfriend.getrequestedlist(datas).subscribe({next:dataa=>{
        console.log(dataa)
        this.requestedlist=dataa;
      },
    error: err=>{
      alert("something went wrong")
    }})

},
error: err=>{
 alert("something went wrong")
}})
  }

}
