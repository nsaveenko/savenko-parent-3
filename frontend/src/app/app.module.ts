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
import { EditComponent } from './components/edit/edit.component';
import { ActivityComponent } from './components/activity/activity.component';
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
import {SubscriptionsComponent} from "./components/subscriptions/subscriptions.component";

const appRoutes: Routes = [
  {path: "", component: FeedComponent},
  {path: "entry", component: EntryComponent},
  {path: "registration", component: RegistrationComponent},
  {path: "user", component: UserComponent},
  {path: "edit", component: EditComponent},
  {path: "activity", component: ActivityComponent},
  {path: "post", component: PostComponent},
  {path: "comment", component: CommentComponent},
  {path: "like", component: LikeComponent},
  {path: "complaint", component: ComplaintComponent},
  {path: "newpost", component: NewpostComponent},
  {path: "complaintsforadmin", component: ComplaintsforadminComponent},
  {path: "admin", component: AdminComponent},
  {path: "subscriptions", component: SubscriptionsComponent}
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
    EditComponent,
    ActivityComponent,
    HeaderComponent,
    CommentComponent,
    LikeComponent,
    ComplaintComponent,
    NewpostComponent,
    ComplaintsforadminComponent,
    AdminComponent,
    SubscriptionsComponent
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
    ReactiveFormsModule
  ],
  providers: [
    PostService,
    UserService,
    ComplaintService,
    LikeService,
    CommentService,
    LogInService,
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
