import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscriber } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConnectivityService {
  URL:string="http://localhost:9500/todo/v2/";
  URL2:string="http://localhost:9500/todo/v1/";

  constructor(private http:HttpClient) { }

  logins(logindata:any){
    return this.http.post(this.URL2+"login",logindata);
  }
 

  adddata(content:any){
    return this.http.post<any>(this.URL+"registration",content);
  }
  getalluser():Observable<Array<any>>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    });
    let request={headers:httpHeaders}
    return this.http.get<Array<any>>(`${this.URL}admin/alluser`,request);

  }

  getdata():Observable<Array<any>>{
    
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    });
    let request={headers:httpHeaders}
    return this.http.get<Array<any>>(`${this.URL}user/Userdetails`,request);
  }

  getfriends(email:any):Observable<Array<any>>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    });
    let request={headers:httpHeaders}
    return this.http.get<Array<any>>(`${this.URL}user/getfriends/${email}`,request)
  }

  getdatabyid(id?:number):Observable<any>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<any>(`${this.URL}user/getById/${id}`,request);
  }

  addcontent(todo?:any){
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
     let request={headers:httpHeaders}
     return this.http.post<any>(`${this.URL}user/addlist`,todo,request);
  }

  getdatabypriority(priority?:any):Observable<any>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<any>(`${this.URL}user/getBysId/${priority}`,request);
  }

  sendmail(email:any):Observable<any>{
    return this.http.get<any>(`${this.URL}otp/${email}`);
  }

  getdatabyserach(title?:any):Observable<any>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<any>(`${this.URL}user/getTitle/${title}`,request);
  }

  getdatabystatusactive():Observable<any>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<any>(`${this.URL}user/getStatus/Active`,request);
  }
  getdatabystatuscomplete():Observable<any>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<any>(`${this.URL}user/getStatus/completed`,request);
  }
  getdatabystatuspending():Observable<any>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<any>(`${this.URL}user/getStatus/pending`,request);
  }
  getuserdetails():Observable<any>{
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.get<any>(`${this.URL}user/Userdetails`,request);
  }

  editcontent(id?:any,content?:any){
      let httpHeaders=new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
      })
       let requests={headers:httpHeaders}
    
    return this.http.put<any>(`${this.URL}user/update/${id}`,content,requests); 
  }

  verify(email?:any, verify?:any){
    return this.http.put<any>(`${this.URL}verify/${email}`,verify); 
  }

  delete(id?:number){
     
      let httpHeaders=new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
      })
       let requests={headers:httpHeaders}
     
    return this.http.delete<any>(`${this.URL}user/delete/${id}`,requests);
  }



// getdatabyid(id?:number):Observable<any>{
//   let httpHeaders=new HttpHeaders({
//     'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
//   })
//   let request={headers:httpHeaders}
//   return this.http.get<any>(`${this.URL}user/getById/${id}`,request);
// }

getotp(email:any):Observable<any>{
  return this.http.get<any>(`${this.URL}otp/${email}`);
}

updatepassword(email:any,user:any){
  return this.http.put<any>(`${this.URL}updatepassword/${email}`,user)
}


getuserdetail():Observable<any>{
  let httpHeaders=new HttpHeaders({
    'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
  })
  let request={headers:httpHeaders}
  return this.http.get<any>(`${this.URL}user/getuser`,request);
}


edituser(user:any){
  let httpHeaders=new HttpHeaders({
    'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
  })
  let request={headers:httpHeaders}
  return this.http.put<any>(`${this.URL}user/updateuserprofile`,user,request);
}


//friends

getfriendslist(email:any):Observable<any>{
  let httpHeaders=new HttpHeaders({
    'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
  })
  let request={headers:httpHeaders}
  return this.http.get<any>(`${this.URL}user/getinfofriends/${email}`,request);
}

getrequestlist(email:any):Observable<any>{
  let httpHeaders=new HttpHeaders({
    'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
  })
  let request={headers:httpHeaders}
  return this.http.get<any>(`${this.URL}user/getinforeject/${email}`,request);
}

getrequestedlist(email:any):Observable<any>{
  let httpHeaders=new HttpHeaders({
    'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
  })
  let request={headers:httpHeaders}
  return this.http.get<any>(`${this.URL}user/getinforejected/${email}`,request);
}

getnotificationtodolist(email:any):Observable<any>{
  let httpHeaders=new HttpHeaders({
    'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
  })
  let request={headers:httpHeaders}
  return this.http.get<any>(`${this.URL}user/getinforejectedtodolist/${email}`,request);
}

accepttodotask(todo?:any,msg?:string){
  let httpHeaders=new HttpHeaders({
    'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
  })
   let request={headers:httpHeaders}
   return this.http.post<any>(`${this.URL}user/addtodotofriends/${msg}`,todo,request);
}




  requestToFriend(email:any){

    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    console.log(httpHeaders)
    let request={headers:httpHeaders}
    console.log(request)
    return this.http.post<any>(`${this.URL}user/addfriend/${email}`,{responseType:'text'},request);
  }

  acceptByFriend(email:any){
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    console.log(request)
    return this.http.post<any>(`${this.URL}user/acceptfriend/${email}`,null,request);
  }

  refusedFriendRequest(email:any){
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders}
    return this.http.delete<any>(`${this.URL}user/deleterequest/${email}`,request);
  }
  cancelRequest(email:any) {
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders }
    return this.http.delete<any>(`${this.URL}user/deleterequested/${email}`, request);
    }


    DeleteFriend(email:any) {
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
    })
    let request={headers:httpHeaders }
    return this.http.delete<any>(`${this.URL}user/deletefriend/${email}`, request);
    }

    shareTodolistToFriend(email:any,todolist?:any){
      let httpHeaders=new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
      })
      let request={headers:httpHeaders}
      return this.http.post<any>(`${this.URL}user/sharetodo/${email}`,todolist,request);
      }
    
     getEmailsInfobySerach(email:any){    
      let httpHeaders=new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem('jwt')
      })
      let request={headers:httpHeaders}
      console.log(request)
      return this.http.get<any>(`${this.URL}user/getfriends/${email}`,request);
      }



}

