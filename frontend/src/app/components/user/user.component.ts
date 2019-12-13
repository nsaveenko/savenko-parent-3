import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {User} from "../../models/User";
import {Subscriber} from "../../models/Subscriber"
import {SubscriberService} from "../../services/subscriber.service";
import {Post} from "../../models/Post";
import {PostService} from "../../services/post.service";
import {Like} from "../../models/Like";
import {LikeService} from "../../services/like.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  subscribers: Subscriber[];
  public sub: Subscriber[];
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService,
              private postService: PostService,
              private likeService: LikeService,
              private subscriberService: SubscriberService) { }

  ngOnInit() {
    this.loadSub();
  }

  private loadSub(): void {
    this.subscriptions.push(this.subscriberService.getSubscribers().subscribe(subs => {
      this.subscribers = subs;
    }));
  }
}
