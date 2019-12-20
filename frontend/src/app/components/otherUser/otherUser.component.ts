import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {Subscriber} from "../../models/Subscriber"
import {SubscriberService} from "../../services/subscriber.service";
import {PostService} from "../../services/post.service";
import {LikeService} from "../../services/like.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../models/User";
import {Post} from "../../models/Post";

@Component({
  selector: 'app-user',
  templateUrl: './otherUser.component.html',
  styleUrls: ['./otherUser.component.css']
})
export class OtherUserComponent implements OnInit {

  postsOtherUserCount: number;
  userIdParam: number;
  users: User[];
  userId: string;
  subscribers: Subscriber[];
  followers: User[];
  public sub: Subscriber[];
  private subscriptions: Subscription[] = [];
  private routeSubscription: Subscription;
  user: User;
  public editableSubscriber: Subscriber = new Subscriber();
  followersCount: number;
  followingCount: number;
  public posts: Post[];


  constructor(private userService: UserService,
              private postService: PostService,
              private likeService: LikeService,
              private subscriberService: SubscriberService,
              private activeRoute: ActivatedRoute,
              private router: Router) {
    this.routeSubscription = this.activeRoute.params.subscribe(params => this.userIdParam = params['id']);
  }

  ngOnInit() {
    this.loadSub();
    this.activeRoute.queryParams.subscribe(params => {
      this.userIdParam = params.id;
      this._updateUser(this.userIdParam);
      // this.userIdParam = this.activeRoute.snapshot.queryParams['id'];
      this.loadUserById(this.userIdParam);
      this.loadFollowing(this.userIdParam);
      this.loadFollowers(this.userIdParam);
      this.postCount(this.userIdParam);
    });
  }

  public _updateUser(id: number){
    this.loadUserById(id);
  }

  private loadUserById(id: number):void {
    this.subscriptions.push(this.userService.getUserById(id).subscribe(user => {
      this.user = user;
    }));
  }

  private loadFollowers(id: number): void{
    this.subscriptions.push(this.userService.getFollowers(id).subscribe(followers =>{
      this.users = followers;
      this.followersCount = followers.length;
    }))
  }

  private loadFollowing(id: number): void{
    this.subscriptions.push(this.userService.getFollowing(id).subscribe(following =>{
      this.users = following;
      this.followingCount = following.length;
    }))
  }

  private postCount(id: number): void{
    this.subscriptions.push(this.postService.getCountPostByUserId(id)
      .subscribe(postCount =>{
        this.postsOtherUserCount = postCount;
      }))
  }

  public _updateSub():void{
    this.loadSub();
  }

  private loadSub(): void {
    this.subscriptions.push(this.subscriberService.getSubscribers().subscribe(followers => {
      this.subscribers = followers;
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
      }))
    } else {
      this.subscriptions.push(this.subscriberService.saveSubscriber(this.editableSubscriber).subscribe(() => {
        this._updateSub();
      }));
    }
  }
}
