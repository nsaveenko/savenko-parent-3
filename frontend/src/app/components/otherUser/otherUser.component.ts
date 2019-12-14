import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {Subscriber} from "../../models/Subscriber"
import {SubscriberService} from "../../services/subscriber.service";
import {PostService} from "../../services/post.service";
import {LikeService} from "../../services/like.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../models/User";

@Component({
  selector: 'app-user',
  templateUrl: './otherUser.component.html',
  styleUrls: ['./otherUser.component.css']
})
export class OtherUserComponent implements OnInit {

  users: User[];
  userId: string;
  subscribers: Subscriber[];
  public sub: Subscriber[];
  private subscriptions: Subscription[] = [];
  private routeSubscription: Subscription;
  user: User;
  public editableSubscriber: Subscriber = new Subscriber();

  constructor(private userService: UserService,
              private postService: PostService,
              private likeService: LikeService,
              private subscriberService: SubscriberService,
              private activeRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    // this.loadSub();
    this.userId = this.router.url.substring(this.router.url.lastIndexOf('/') + 1);
    this.loadUserById();
    this.loadFollowers();
    this.loadFollowing();
  }

  private loadUserById():void {
    this.subscriptions.push(this.userService.getUserById(+this.userId).subscribe(user => {
      this.user = user;
    }));
  }

  public _updateUsers(): void {
    this.loadUsers();
  }

  public _updateSub():void{
    this.loadSub();
  }

  private loadUsers(): void {
    this.subscriptions.push(this.userService.getUsers().subscribe(users => {
      this._updateSub();
      this.users = users.filter((user: User) => user.roleUserByIdRole.id === 2 && user.id != this.userService.currUser.id);
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

  private loadFollowers(): void{
    this.subscriptions.push(this.userService.getFollowers(this.user.id).subscribe(followers =>{
      this.users = followers;
      this.user.followersCount = followers.length;
    }))
  }

  private loadFollowing(): void{
    this.subscriptions.push(this.userService.getFollowing(this.user.id).subscribe(following =>{
      this.users = following;
      this.user.followingCount = following.length;
    }))
  }
}
