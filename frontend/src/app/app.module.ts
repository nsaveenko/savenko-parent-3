import { BrowserModule } from "@angular/platform-browser";
import {APP_INITIALIZER, NgModule} from "@angular/core";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import { AppComponent } from "./app.component";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from "@angular/router";
import { EntryComponent } from './components/entry/entry.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FeedComponent } from './components/feed/feed.component';
import { UserComponent } from './components/user/user.component';
import { PostComponent } from './components/post/post.component';
import { HeaderComponent } from './components/header/header.component';
import { CommentComponent } from './components/comment/comment.component';
import { LikeComponent } from './components/like/like.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { NewpostComponent } from './components/newpost/newpost.component';
import {PostService} from "./services/post.service";
import { ComplaintsforadminComponent } from './components/complaintsforadmin/complaintsforadmin.component';
import { AdminComponent } from './components/admin/admin.component';
import { UserService } from "./services/user.service";
import {ComplaintService} from "./services/complaint.service";
import {LikeService} from "./services/like.service";
import {CommentService} from "./services/comment.service";
import {LogInService} from "./services/logIn.service";
import {Interceptor} from "./services/Interceptor";
import {initApp} from "./services/app.initializer";
import {SecurePipe} from "./services/securePipe";
import {SubscriberComponent} from "./components/subscriber/subscriber.component";
import {SubscriberService} from "./services/subscriber.service";
import {OtherUserComponent} from "./components/otherUser/otherUser.component";
import {NotFoundComponent} from "./components/404/not-found.component";
import {PaginationModule} from "ngx-bootstrap";
import {FindUsersComponent} from "./components/findUsers/findUsers.component";
import {AuthGuardService} from "./services/guards/guard.service";
import {AdminGuardService} from "./services/guards/admin.guard.service";
import {UserGuardService} from "./services/guards/user.guard.service";
import {NotAuthGuardService} from "./services/guards/notAuth.guard.service";

const appRoutes: Routes = [
  {path: "", component: FeedComponent, canActivate:[AuthGuardService, UserGuardService]},
  {path: "entry", component: EntryComponent, canActivate: [NotAuthGuardService]},
  {path: "registration", component: RegistrationComponent, canActivate: [NotAuthGuardService]},
  {path: "complaint", component: ComplaintComponent, canActivate[UserGuardService]},
  {path: "user", component: UserComponent, canActivate:[AuthGuardService, UserGuardService]},
  {path: "newpost", component: NewpostComponent, canActivate:[AuthGuardService, UserGuardService]},
  {path: "admin", component: AdminComponent, canActivate:[AuthGuardService, AdminGuardService]},
  {path: "otherUser", component: OtherUserComponent, canActivate:[AuthGuardService, UserGuardService]},
  {path: "findUsers", component: FindUsersComponent, canActivate:[AuthGuardService, UserGuardService]},
  {path: "**", component: NotFoundComponent}
];

@NgModule({
  declarations: [
    SecurePipe,
    AppComponent,
    EntryComponent,
    RegistrationComponent,
    FeedComponent,
    UserComponent,
    PostComponent,
    HeaderComponent,
    CommentComponent,
    LikeComponent,
    ComplaintComponent,
    NewpostComponent,
    ComplaintsforadminComponent,
    AdminComponent,
    SubscriberComponent,
    OtherUserComponent,
    NotFoundComponent,
    FindUsersComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule,
    PaginationModule,
    PaginationModule.forRoot()
  ],
  providers: [
    PostService,
    UserService,
    ComplaintService,
    LikeService,
    SubscriberService,
    CommentService,
    LogInService,
    AuthGuardService,
    AdminGuardService,
    UserGuardService,
    NotAuthGuardService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: initApp,
      multi: true,
      deps: [HttpClient, UserService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
