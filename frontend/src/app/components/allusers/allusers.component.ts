import {Component, OnInit} from '@angular/core';
import {User} from "../../models/User";
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {Subscriber} from "../../models/Subscriber";
import {Comment} from "../../models/Comment";
import {SubscriberService} from "../../services/subscriber.service";
import {Like} from "../../models/Like";

@Component({
  selector: 'app-subscriptions',
  templateUrl: './allusers.component.html',
  styleUrls: ['./allusers.component.css']
})
export class AllUsersComponent implements OnInit {

  users: User[];
  subscribers: Subscriber[];
  public editableSubscriber: Subscriber = new Subscriber();
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService,
              private subscriberService: SubscriberService) {
  }

  ngOnInit() {
    this.loadUsers();
    this.loadSub();
  }

  public _updateUsers(): void {
    this.loadUsers();
  }

  public _updateSub():void{
    this.loadSub();
  }

  private loadUsers(): void {
    this.subscriptions.push(this.userService.getUsers().subscribe(users => {
      // if(this.selectedPost){
      //   this.comments = comments.filter((comment: Comment) => comment.idPost === this.selectedPost.id) ;
      // } else{
      this.users = users.filter((user: User) => user.roleUserByIdRole.id === 2 && user.id != this.userService.currUser.id);
      //  }
    }));
  }

  private loadSub(): void {
    this.subscriptions.push(this.subscriberService.getSubscribers().subscribe(subs => {
      this.subscribers = subs;
      this.users.forEach((us)=> {
        us.isSubscriber = this.subscribers.some((sb)=> sb.userByIdFollowers.id === this.userService.currUser.id && sb.userByIdFollowing.id === us.id)
      });
    }));
  }

  private _subscribe(followers: User, following: User) {
    this.editableSubscriber.userByIdFollowers = followers;
    this.editableSubscriber.userByIdFollowing = following;
    const userSubscribe = this.subscribers.find((subscriber: Subscriber) =>
      subscriber.userByIdFollowers.id === followers.id && subscriber.userByIdFollowing.id === following.id);
    debugger;
    if(userSubscribe){
      this.subscriptions.push(this.subscriberService.deleteSubscriber(userSubscribe.id).subscribe(()=>{
        this._updateUsers();
        this._updateSub();
      }))
    } else {
      this.subscriptions.push(this.subscriberService.saveSubscriber(this.editableSubscriber).subscribe(() => {
        this._updateUsers();
        this._updateSub();
      }));
    }
  }
}
