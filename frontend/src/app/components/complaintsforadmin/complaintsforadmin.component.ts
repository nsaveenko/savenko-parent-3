import {Component, OnInit} from '@angular/core';
import {Complaint} from "../../models/Complaint";
import {Subscription} from "rxjs";
import {ComplaintService} from "../../services/complaint.service";
import {Post} from "../../models/Post";
import {PostService} from "../../services/post.service";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-complaintsforadmin',
  templateUrl: './complaintsforadmin.component.html',
  styleUrls: ['./complaintsforadmin.component.css']
})
export class ComplaintsforadminComponent implements OnInit {

  public complaints: Complaint[];
  public post: Post[];
  private subscriptions: Subscription[] = [];
  public editableComplaint: Complaint = new Complaint();

  constructor(private complaintService: ComplaintService,
              private userService: UserService,
              private postService: PostService) {
  }

  ngOnInit() {
    this.loadComplaint();
  }

  private blockUser(id: number): void {

  }

  private changeStatusComplaint(id: number): void {
    this.subscriptions.push(this.complaintService.getComplaintById(id).subscribe(complaint => {
      console.log(complaint);
      debugger;
      switch (complaint.idStatusComplaint) {
        case 1:
          this.editableComplaint.id = complaint.id;
          this.editableComplaint.idPost = complaint.idPost;
          this.editableComplaint.idUser = complaint.idUser;
          this.editableComplaint.dateComplaint = complaint.dateComplaint;
          this.editableComplaint.complaint = complaint.complaint;
          this.editableComplaint.idStatusComplaint = 2;
          this.subscriptions.push(this.complaintService.saveComplaint(this.editableComplaint).subscribe((complaint: Complaint) => {
            this._updateComplaint();
            this.refreshComplaint();
          }));
          break;
        case 2:
          this.editableComplaint.id = complaint.id;
          this.editableComplaint.idPost = complaint.idPost;
          this.editableComplaint.idUser = complaint.idUser;
          this.editableComplaint.dateComplaint = complaint.dateComplaint;
          this.editableComplaint.complaint = complaint.complaint;
          this.editableComplaint.idStatusComplaint = 2;
          this.editableComplaint.idStatusComplaint = 1;
          this.subscriptions.push(this.complaintService.saveComplaint(this.editableComplaint).subscribe((complaint: Complaint) => {
            this._updateComplaint();
            this.refreshComplaint();
          }));
          break;
      }
    }))
  }

  private refreshComplaint(): void {
    this.editableComplaint = new Complaint();
  }

  private loadComplaint(): void {
    this.subscriptions.push(this.complaintService.getComplaint().subscribe(complaints => {
      if (complaints.length > 0) {
        this.complaints = complaints as Complaint[];
      } else {
        console.log("empty");
      }
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
      }));
  }
}
