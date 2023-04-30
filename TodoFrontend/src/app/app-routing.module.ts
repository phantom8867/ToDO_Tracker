import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddtaskComponent } from './addtask/addtask.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { SuccessfullyregisterationComponent } from './successfullyregisteration/successfullyregisteration.component';
import { TodofrontpageComponent } from './todofrontpage/todofrontpage.component';
import { TodopageComponent } from './todopage/todopage.component';
import { GetpriorityComponent } from './getpriority/getpriority.component';
import { EdittodolistComponent } from './edittodolist/edittodolist.component';
import { DetailedViewComponent } from './detailed-view/detailed-view.component';
import { DateComponent } from './date/date.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { OTPComponent } from './otp/otp.component';
import { SearchComponent } from './search/search.component';
import { NotificationComponent } from './notification/notification.component';
import { ViewProfileComponent } from './view-profile/view-profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { AuthGuard } from './services/auth.guard';
import { CanDeactivateGuard } from './can-deactivate.guard';
import { VerifyComponent } from './verify/verify.component';
import { ArchiveComponent } from './archive/archive.component';
import { SuggestionComponent } from './suggestion/suggestion.component';
import { AdminmainpageComponent } from './adminmainpage/adminmainpage.component';
import { TodoGuard } from './service/todo.guard';
import { ShowfriendsComponent } from './showfriends/showfriends.component';


const routes: Routes = [
  {path:"home",component:TodofrontpageComponent},
  {path:"registeration",component:RegisterComponent},
  {path:"login",component:LoginComponent},
  {path:"todopage",component:TodopageComponent,canActivate:[AuthGuard]},
  {path:"addtodo",component:AddtaskComponent,canActivate:[AuthGuard]},
  {path:"verified/:id",component:SuccessfullyregisterationComponent},
  {path:"about-us",component:AboutUsComponent},
  {path:"contact-us",component:ContactUsComponent},
  {path:"reset",component:ResetPasswordComponent},
  {path:"edit/:id",component:EdittodolistComponent,canActivate:[AuthGuard]},
  {path:"detail/:id",component:DetailedViewComponent,canActivate:[AuthGuard]},
  {path:"",redirectTo:"/home",pathMatch:"full"},
  {path:"Date/:date",pathMatch:'full',component:DateComponent,canActivate:[AuthGuard]},
  {path:"todopage",component:TodopageComponent,canActivate:[AuthGuard]},
   
  {path:"successmsg/:id",component:SuccessfullyregisterationComponent},
  {path:"about-us",component:AboutUsComponent},
  {path:"contact-us",component:ContactUsComponent},
  {path:"priority/:priority",pathMatch:'full',component:GetpriorityComponent},
  {path:"Date/:date",pathMatch:'full',component:DateComponent},
  {path:"forget-password",component:ForgotPasswordComponent},
  {path:"otp",component:OTPComponent},
  {path:"forget-password",component:ForgotPasswordComponent},
  {path:"search",component:SearchComponent,canActivate:[AuthGuard]},
  {path:"pending",component:VerifyComponent},
  {path:"notification",component:NotificationComponent},
  {path:"viewprofile",component:ViewProfileComponent,canActivate:[AuthGuard]},
  {path:"editprofile",component:EditProfileComponent,canActivate:[AuthGuard]},
  
  {path:"reset/:id",component:ResetPasswordComponent},

  {path:"archieve",component:ArchiveComponent,canActivate:[AuthGuard]},
  {path:"service",component:SuggestionComponent,canActivate:[AuthGuard]},
  {path:"admin",component:AdminmainpageComponent,canActivate:[AuthGuard]},
  {path:"friends",component:ShowfriendsComponent,canActivate:[AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
