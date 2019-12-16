import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {Subscriber} from "../../models/Subscriber"
import {SubscriberService} from "../../services/subscriber.service";
import {User} from "../../models/User";
import {PostService} from "../../services/post.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  postsCount: number;
  subscribers: Subscriber[];
  users: User[];
  public sub: Subscriber[];
  private subscriptions: Subscription[] = [];
  public postId;
  user: User;
  userId: number = this.userService.currUser.id;

  constructor(private userService: UserService,
              private postService: PostService,
              private subscriberService: SubscriberService) { }

  ngOnInit() {
    this.loadUserById(this.userId);
    this.loadFollowers(this.userId);
    this.loadFollowing(this.userId);
    this.postCount(this.userService.currUser.id);
    //console.log(this.activeRoute.snapshot.queryParams['id']);
  }

  private loadFollowers(id: number): void{
    this.subscriptions.push(this.userService.getFollowers(id).subscribe(followers =>{
      this.users = followers;
      this.userService.currUser.followersCount = followers.length;
    }))
  }

  private loadFollowing(id: number): void{
    this.subscriptions.push(this.userService.getFollowing(id).subscribe(following =>{
      this.users = following;
      this.userService.currUser.followingCount = following.length;
    }))
  }

  private postCount(id: number): void{
    this.subscriptions.push(this.postService.getCountPostByUserId(id)
      .subscribe(postCount =>{
        this.postsCount = postCount;
      }))
  }

  private loadUserById(id: number):void {
    this.subscriptions.push(this.userService.getUserById(id).subscribe(user => {
      this.user = user;
    }));
  }

  public _updateUser(id: number): void {
    this.loadUserById(id);
  }
}
