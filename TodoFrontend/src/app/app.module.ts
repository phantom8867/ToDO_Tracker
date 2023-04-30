 
import { HttpClientModule } from '@angular/common/http';
 
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table'; 
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTabsModule} from '@angular/material/tabs';
import {MatBadgeModule} from '@angular/material/badge';

import { MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDialogModule} from '@angular/material/dialog';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatInputModule } from '@angular/material/input';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './navbar/navbar.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { TodofrontpageComponent } from './todofrontpage/todofrontpage.component';
import { RegisterComponent } from './register/register.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms' ;
import { FormsModule } from '@angular/forms';
import {MatRadioModule} from '@angular/material/radio';
import { LoginComponent } from './login/login.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

import { TodopageComponent } from './todopage/todopage.component';
import { AddtaskComponent } from './addtask/addtask.component';

import { ViewTodoListComponent } from './view-todo-list/view-todo-list.component';

import { SuccessfullyregisterationComponent } from './successfullyregisteration/successfullyregisteration.component';
import { EdittodolistComponent } from './edittodolist/edittodolist.component';
import { GetpriorityComponent } from './getpriority/getpriority.component';
import { DetailedViewComponent } from './detailed-view/detailed-view.component';
import { DateComponent } from './date/date.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { OTPComponent } from './otp/otp.component';
import { SearchComponent } from './search/search.component';
import { NotificationComponent } from './notification/notification.component';
import { ViewProfileComponent } from './view-profile/view-profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';

import { VerifyComponent } from './verify/verify.component';

import { ArchiveComponent } from './archive/archive.component';
import { SuggestionComponent } from './suggestion/suggestion.component';
import { AdminmainpageComponent } from './adminmainpage/adminmainpage.component';
import { AddfriendComponent } from './addfriend/addfriend.component';
import { ShowfriendsComponent } from './showfriends/showfriends.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {OverlayModule} from '@angular/cdk/overlay';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    TodofrontpageComponent,
    RegisterComponent,
    LoginComponent,
    ResetPasswordComponent,
    TodopageComponent,
    AddtaskComponent,
    ViewTodoListComponent,
    ResetPasswordComponent,
    TodopageComponent,
    AddtaskComponent,
    SuccessfullyregisterationComponent,
    EdittodolistComponent,
    AboutUsComponent,
    ContactUsComponent,
    GetpriorityComponent,
    DetailedViewComponent,
    DateComponent,
    ForgotPasswordComponent,
    OTPComponent,
    SearchComponent,
    NotificationComponent,
    ViewProfileComponent,
    EditProfileComponent,
    VerifyComponent,
    ArchiveComponent,
    SuggestionComponent,
    AdminmainpageComponent,
    AddfriendComponent,
    ShowfriendsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatRadioModule,
    MatInputModule,
    HttpClientModule,
    MatSelectModule,
    MatTableModule,
    MatDatepickerModule,
    MatSnackBarModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatCardModule,
    MatChipsModule,
    MatDialogModule,
    MatSidenavModule,
    MatTooltipModule,
    MatTabsModule,

    MatBadgeModule,

    DragDropModule,
    OverlayModule

    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
