import { Component, OnInit } from '@angular/core';
import { Message,ChatService } from '../chat.service';
import { ConnectivityService } from '../service/connectivity.service';

@Component({
  selector: 'app-suggestion',
  templateUrl: './suggestion.component.html',
  styleUrls: ['./suggestion.component.css']
})
export class SuggestionComponent implements OnInit{
  messages: Message[] = [];
  todolist1:any={};
  value: string ="";

  constructor(public chatService: ChatService,private listservice:ConnectivityService) { }

  ngOnInit() {
      this.chatService.conversation.subscribe((val) => {
      this.messages = this.messages.concat(val);
    });
    this.listservice.getuserdetail().subscribe({next:data=>{
      console.log(data);
      this.todolist1=data
      }})
  }
  
  edituser(){
    this.todolist1.suggestion=this.value;
    this.listservice.edituser(this.todolist1).subscribe({next:data=>{
      console.log(data);
     
      
    },
  error:e=>{
    alert("Failed to Register");
  }
  });
}

  sendMessage() {

    this.chatService.getBotAnswer(this.value);
    this.edituser();
    this.value = '';
  }
  
}
