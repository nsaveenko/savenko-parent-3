import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Post} from "../../models/Post";
import {Subscription} from "rxjs";
import {User} from "../../models/User";
import {Subscriber} from "../../models/Subscriber"
import {SubscriberService} from "../../services/subscriber.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public users: User[];
  subscribers: Subscriber[];
  public sub: Subscriber[];
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService,
              private subscriberService: SubscriberService) { }

  ngOnInit() {
    this.loadSub();
  }

  private loadSub(): void {
    this.subscriptions.push(this.subscriberService.getSubscribers().subscribe(subs => {
      this.subscribers = subs;
    }));
  }

  // private getSubById(){
  //   this.subscriptions.push((this.subscriberService.getSubscriberById(this.userService.currUser.id).subscribe()));
  // }

}
