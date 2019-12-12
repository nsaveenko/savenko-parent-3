import { Component, OnInit } from '@angular/core';
import {Complaint} from "../../models/Complaint";
import {Subscription} from "rxjs";
import {ComplaintService} from "../../services/complaint.service";
import {Post} from "../../models/Post";
import {PostService} from "../../services/post.service";

@Component({
  selector: 'app-complaintsforadmin',
  templateUrl: './complaintsforadmin.component.html',
  styleUrls: ['./complaintsforadmin.component.css']
})
export class ComplaintsforadminComponent implements OnInit {

  public complaint: Complaint[];
  public post: Post[];
  private subscriptions: Subscription[] = [];

  constructor(private complaintService: ComplaintService,
              private postService: PostService) {
  }

  ngOnInit() {
    this.loadComplaint();
  }

  private loadComplaint(): void {
    this.subscriptions.push(this.complaintService.getComplaint().subscribe(complaints => {
      this.complaint = complaints as Complaint[];
      console.log(this.complaint);
    }));
  }

  public _updateComplaint(): void {
    this.loadComplaint();
  }

  public _deleteComplaint(complaintId: number): void {
    this.subscriptions.push(this.complaintService.deleteComplaint(complaintId).subscribe(() => {
      this._updateComplaint();
    }));
  }

  public _deletePost(postId: number): void {
    this.subscriptions.push(this.complaintService.deletePost(postId).subscribe(() => {
      this._updateComplaint();
    }));
  }

  public _updatePost(): void {
    this.loadPost();
  }

  private loadPost(): void {
    this.subscriptions.push(this.postService.getPost()
      .subscribe(posts => {
        this.post = posts as Post[];
        console.log(this.post);
      }));
  }
}
