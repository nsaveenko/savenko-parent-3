import {Component, OnInit} from '@angular/core';
import {Complaint} from "../../models/Complaint";
import {Subscription} from "rxjs";
import {ComplaintService} from "../../services/complaint.service";
import {PostService} from "../../services/post.service";
import {RestPageModel} from "../../models/RestPage.model";
import {PageChangedEvent} from "ngx-bootstrap";
import {Post} from "../../models/Post";

@Component({
  selector: 'app-complaintsforadmin',
  templateUrl: './complaintsforadmin.component.html',
  styleUrls: ['./complaintsforadmin.component.css']
})
export class ComplaintsForAdminComponent implements OnInit {

  public complaints: Complaint[];
  private subscriptions: Subscription[] = [];
  public editableComplaint: Complaint = new Complaint();
  private statusComplaintId: number;
  public complaint: Complaint;
  public currentPage: number = 1;
  private subs: any;
  private size: number = 2;
  private page: RestPageModel;
  postId: number[];
  public post: Post;

  //private post: Post;

  constructor(private complaintService: ComplaintService,
              private postService: PostService) {
  }

  ngOnInit() {
    this.subs = [];
    this.subs[this.subs.length] = this.getComplaint(2, this.currentPage);
    // this.getPost(this.postService.currPost.id);
    // console.log(this.complaint.post);
    //this.getPost(this.complaint.postId);
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage = event.page;
    this.getComplaint(this.statusComplaintId, this.currentPage);
  }

  private getComplaint(statusId: number, page: number = 1) {
    this.statusComplaintId = statusId;
    this.currentPage = page;
    this.complaintService.getComplaintsByStatusId(this.statusComplaintId, this.currentPage - 1, this.size)
      .subscribe((pageModel: RestPageModel) => {
        this.page = pageModel;
        this.complaints = pageModel.content;
        this.subs.length = pageModel.content.length;
        console.log(this.complaints);

        this.complaints.forEach((complaint, index) => {
          this.postService.getPostById(complaint.postId).subscribe((post: Post) => {
            this.complaints[index].post = post;
          })
        })
      });
  }

  private changeStatusComplaint(id: number): void {
    this.subscriptions.push(this.complaintService.getComplaintById(id).subscribe(complaint => {
      this.editableComplaint.id = complaint.id;
      this.editableComplaint.post = complaint.post;
      this.editableComplaint.idUser = complaint.idUser;
      this.editableComplaint.dateComplaint = complaint.dateComplaint;
      this.editableComplaint.complaint = complaint.complaint;
      switch (complaint.idStatusComplaint) {
        case 1:
          this.editableComplaint.idStatusComplaint = 2;
          this.subscriptions.push(this.complaintService.saveComplaint(this.editableComplaint)
            .subscribe((complaint: Complaint) => {
              this.updateComplaint(1);
            }));
          break;
        case 2:
          this.editableComplaint.idStatusComplaint = 1;
          this.subscriptions.push(this.complaintService.saveComplaint(this.editableComplaint)
            .subscribe((complaint: Complaint) => {
              this.updateComplaint(2);
            }));
          break;
      }
    }))
  }

  public updateComplaint(statusId: number): void {
    this.getComplaint(statusId, 1);
  }

  public deleteComplaint(complaintId: number, statusId: number): void {
    this.subscriptions.push(this.complaintService.deleteComplaint(complaintId).subscribe(() => {
      this.updateComplaint(statusId);
    }));
  }

  public deletePost(postId: number, statusId: number): void {
    this.subscriptions.push(this.complaintService.deletePost(postId).subscribe(() => {
      this.updateComplaint(statusId);
    }));
  }


  public loadComplaintByPostId(postId: number): void {
    this.complaintService.getComplaintOnPost(postId).subscribe((complaints) => {
      alert(complaints.length);
    })
  }
}
