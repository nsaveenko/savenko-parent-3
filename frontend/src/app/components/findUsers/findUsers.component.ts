import {Component, OnInit} from '@angular/core';
import {User} from "../../models/User";
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {Subscriber} from "../../models/Subscriber";
import {Comment} from "../../models/Comment";
import {SubscriberService} from "../../services/subscriber.service";
import {Like} from "../../models/Like";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-findUsers',
  templateUrl: './findUsers.component.html',
  styleUrls: ['./findUsers.component.css']
})
export class FindUsersComponent implements OnInit {

  usernameParam: string;
  users: User[];
  subscribers: Subscriber[];
  public editableSubscriber: Subscriber = new Subscriber();
  private routeSubscription: Subscription;
  private querySubscription: Subscription;
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService,
              private subscriberService: SubscriberService,
              private activeRoute: ActivatedRoute,
              private router: Router) {
    this.routeSubscription = this.activeRoute.params.subscribe(params => this.usernameParam = params['username']);
  }

  ngOnInit() {
    this.loadSub();
    this.activeRoute.queryParams.subscribe(params => {
      this.usernameParam = params.username;
    });
    this._findUserByUsername(this.usernameParam);

  }

  public _findUserByUsername(textValue: string): void {
    this.subscriptions.push(this.userService.findUserByUsername(textValue).subscribe(users => {
      this._updateSub();
      this.users = users;
    }))
  }

  public _updateUsers(): void {
    this._findUserByUsername(this.usernameParam);
  }

  public _updateSub():void{
    this.loadSub();
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
    if(userSubscribe){
      this.subscriptions.push(this.subscriberService.deleteSubscriber(userSubscribe.id).subscribe(()=>{
        this._updateSub();
        this._updateUsers();
      }))
    } else {
      this.subscriptions.push(this.subscriberService.saveSubscriber(this.editableSubscriber).subscribe(() => {
        this._updateSub();
        this._updateUsers();
      }));
    }
  }
}
