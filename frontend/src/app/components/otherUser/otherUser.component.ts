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

  postsOtherUserCount: number;
  userIdParam: number;
  users: User[];
  subscribers: Subscriber[];
  private subscriptions: Subscription[] = [];
  private routeSubscription: Subscription;
  user: User;
  public editableSubscriber: Subscriber = new Subscriber();
  followersCount: number;
  followingCount: number;

  constructor(private userService: UserService,
              private postService: PostService,
              private likeService: LikeService,
              private subscriberService: SubscriberService,
              private activeRoute: ActivatedRoute,
              private router: Router) {
    this.routeSubscription = this.activeRoute.params.subscribe(params => this.userIdParam = params['id']);
  }

  ngOnInit() {
    this.activeRoute.queryParams.subscribe(params => {
      this.userIdParam = params.id;
      this.loadUserById(this.userIdParam);
      this.loadFollowing(this.userIdParam);
      this.postCount(this.userIdParam);
    });

  }

  // public _updateUser(id: number) {
  //   this.loadUserById(id);
  // }

  private loadUserById(id: number): void {
    this.subscriptions.push(this.userService.getUserById(id).subscribe(user => {
      this.user = user;
      this.loadFollowers(this.userIdParam);
    }));
  }

  private loadFollowers(userId: number): void {
    this.subscriptions.push(this.userService.getFollowers(userId).subscribe(followers => {
      this.users = followers;
      this.followersCount = followers.length;
      followers.forEach((fl) => {
        if (fl.id === this.userService.currUser.id) {
          this.user.isSubscriber = true;
        }
      })
    }))
  }

  private loadFollowing(id: number): void {
    this.subscriptions.push(this.userService.getFollowing(id).subscribe(following => {
      // this.users = following;
      this.followingCount = following.length;
    }))
  }

  private postCount(id: number): void {
    this.subscriptions.push(this.postService.getCountPostByUserId(id)
      .subscribe(postCount => {
        this.postsOtherUserCount = postCount;
      }))
  }

  // public _updateSub(): void {
  //   //this.loadSub();
  // }
  //
  // private loadSub(): void {
  //   this.subscriptions.push(this.subscriberService.getSubscribers().subscribe(followers => {
  //     this.subscribers = followers;
  //   }));
  // }

  private _subscribe(currUser: User, following: User) {
    this.editableSubscriber.userByIdFollowers = currUser;
    this.editableSubscriber.userByIdFollowing = following;
    if (this.user.isSubscriber) {
      this.subscriptions.push(this.subscriberService.getSubIdByCurrUserAndOtherUser(currUser.id, following.id)
        .subscribe(subId => {
          this.subscriptions.push(this.subscriberService.deleteSubscriber(subId).subscribe(() => {
            this.user.isSubscriber = false;
            this.loadFollowers(this.userIdParam);
          }))
        }))
    } else {
      this.subscriptions.push(this.subscriberService.saveSubscriber(this.editableSubscriber).subscribe(() => {
        this.loadFollowers(this.userIdParam);
      }));
    }
  }
}
