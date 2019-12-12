import { Component, OnInit } from '@angular/core';
import {Post} from "../../models/Post";
import {Subscription} from "rxjs";
import {ComplaintService} from "../../services/complaint.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Complaint} from "../../models/Complaint";
import {PostService} from "../../services/post.service";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  public complaint: Complaint[];
  public post: Post[];
  private subscriptions: Subscription[] = [];
  public editableComplaint: Complaint = new Complaint();

  constructor(private complaintService: ComplaintService,
              private postService: PostService,
              private userService: UserService,
              private http: HttpClient,
              private router: Router) {
  }

  ngOnInit() {
  }

  public _updateComplaint(): void {
    this.loadComplaint();
  }

  public _addComplaint(): void {
    this.editableComplaint.dateComplaint = Date.now();
    this.editableComplaint.idPost = this.postService.currPost.id;
    this.editableComplaint.idUser = this.userService.currUser.id;
    this.editableComplaint.idStatusComplaint =1;
    this.subscriptions.push(this.complaintService.saveComplaint(this.editableComplaint).subscribe((complaint: Complaint) => {
       this._updateComplaint();
       this.refreshComplaint();
       this.router.navigate(['/']);
    }));
  }

  private refreshComplaint(): void {
    this.editableComplaint = new Complaint();
  }

  private loadComplaint(): void {
    this.subscriptions.push(this.complaintService.getComplaint().subscribe(complaints => {
      this.complaint = complaints as Complaint[];
      console.log(this.complaint);
    }));
  }

}
