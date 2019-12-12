import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Post} from "../../models/Post";
import {Subscription} from "rxjs";
import {User} from "../../models/User";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public user: User[];

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

}
