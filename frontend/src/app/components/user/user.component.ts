import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {Subscriber} from "../../models/Subscriber"
import {SubscriberService} from "../../services/subscriber.service";
import {User} from "../../models/User";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  subscribers: Subscriber[];
  users: User[];
  public sub: Subscriber[];
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService,
              private subscriberService: SubscriberService) { }

  ngOnInit() {
    this.loadFollowers();
    this.loadFollowing();
    //console.log(this.activeRoute.snapshot.queryParams['id']);
  }

  private loadFollowers(): void{
    this.subscriptions.push(this.userService.getFollowers(this.userService.currUser.id).subscribe(followers =>{
      this.users = followers;
      this.userService.currUser.followersCount = followers.length;
    }))
  }

  private loadFollowing(): void{
    this.subscriptions.push(this.userService.getFollowing(this.userService.currUser.id).subscribe(following =>{
      this.users = following;
      this.userService.currUser.followingCount = following.length;
    }))
  }

}
