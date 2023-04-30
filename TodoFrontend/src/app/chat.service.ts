import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export class Message {
  constructor(public author: string, public content: string) {}
}

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor() { }
  conversation = new Subject<Message[]>();
  
  messageMap:any = {
    "Hi": "Hey, how can i help you",
    "hi": "Hey, how can i help you",
    "How can I add friends": "Within the side navbar, there is a 'Friends' button which allows you to search and add new friends. Simply click on the 'Add Friends' option and search for the person you want to add. Once you find them, you can send them a friend request to connect with them on the platform.",
    "how can i add friends": "Within the side navbar, there is a 'Friends' button which allows you to search and add new friends. Simply click on the 'Add Friends' option and search for the person you want to add. Once you find them, you can send them a friend request to connect with them on the platform.",
    "How can I share my task to my friends": "By clicking on a task card, you will be directed to a detailed view of the task. Within this view, you can click on the 'Share' button to send the task to your friends, allowing you to collaborate and work on the task together.",
    "how can i share my task to friends": "By clicking on a task card, you will be directed to a detailed view of the task. Within this view, you can click on the 'Share' button to send the task to your friends, allowing you to collaborate and work on the task together.", 
    "Who are you": "My name is Agular Bot",
    "What is Angular": "Angular is the best framework ever",
    "default": "I can't understand. Can you please repeat"
  }

  getBotAnswer(msg: string) {
    const userMessage = new Message('user', msg);  
    this.conversation.next([userMessage]);
    const botMessage = new Message('bot', this.getBotMessage(msg));
    
    setTimeout(()=>{
      this.conversation.next([botMessage]);
    }, 1500);
  }


  getBotMessage(question: string){
    let answer = this.messageMap[question];
    return answer || this.messageMap['default'];
  }
}
