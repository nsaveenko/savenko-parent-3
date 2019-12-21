import {Component, OnInit} from '@angular/core';
import {Complaint} from "../../models/Complaint";
import {Subscription} from "rxjs";
import {ComplaintService} from "../../services/complaint.service";
import {Post} from "../../models/Post";
import {PostService} from "../../services/post.service";
import {UserService} from "../../services/user.service";
import {User} from "../../models/User";
import {RestPageModel} from "../../models/RestPage.model";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-complaintsforadmin',
  templateUrl: './complaintsforadmin.component.html',
  styleUrls: ['./complaintsforadmin.component.css']
})
export class ComplaintsforadminComponent implements OnInit {

  public complaints: Complaint[];
  private subscriptions: Subscription[] = [];
  public editableComplaint: Complaint = new Complaint();
  public editableUser: User = new User();
  public message: string;
  private statusComplaintId: number;
  public complaint: Complaint;
  public currentPage: number = 1;
  private subs: any;
  private size: number = 2;
  private page: RestPageModel;

  constructor(private complaintService: ComplaintService,
              private userService: UserService,
              private postService: PostService) {
  }

  ngOnInit() {
    this.subs = [];
    this.subs[this.subs.length] = this.getComplaint(2, this.currentPage);
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
      });
  }

  private changeStatusComplaint(id: number): void {
    this.subscriptions.push(this.complaintService.getComplaintById(id).subscribe(complaint => {
      this.editableComplaint.id = complaint.id;
      this.editableComplaint.idPost = complaint.idPost;
      this.editableComplaint.idUser = complaint.idUser;
      this.editableComplaint.dateComplaint = complaint.dateComplaint;
      this.editableComplaint.complaint = complaint.complaint;
      switch (complaint.idStatusComplaint) {
        case 1:
          this.editableComplaint.idStatusComplaint = 2;
          this.subscriptions.push(this.complaintService.saveComplaint(this.editableComplaint)
            .subscribe((complaint: Complaint) => {
              this._updateComplaint(1);
            }));
          break;
        case 2:
          this.editableComplaint.idStatusComplaint = 1;
          this.subscriptions.push(this.complaintService.saveComplaint(this.editableComplaint)
            .subscribe((complaint: Complaint) => {
              this._updateComplaint(2);
            }));
          break;
      }
    }))
  }

  public _updateComplaint(statusId: number): void {
    this.getComplaint(statusId, 1);
  }

  public _deleteComplaint(complaintId: number, statusId: number): void {
    this.subscriptions.push(this.complaintService.deleteComplaint(complaintId).subscribe(() => {
      this._updateComplaint(statusId);
    }));
  }

  public _deletePost(postId: number, statusId: number): void {
    this.subscriptions.push(this.complaintService.deletePost(postId).subscribe(() => {
      this._updateComplaint(statusId);
    }));
  }

  // private loadComplaintByStatusId(id: number): void {
  //   this.subscriptions.push(this.complaintService.getComplaintsByStatusId(id)
  //     .subscribe(complaints => {
  //       this.complaints = complaints;
  //     }));
  // }

  // private blockUserByPostId(id: number): void {
  //   this.subscriptions.push(this.complaintService.getUserIdByPostId(id)
  //     .subscribe(userId => {
  //       this.userId = userId;
  //       this.subscriptions.push(this.userService.getUserById(userId).subscribe(user => {
  //         this.statusUser = user.statusUserByIdStatus.status;
  //         this.editableUser.id = user.id;
  //         this.editableUser.username = user.username;
  //         this.editableUser.flName = user.flName;
  //         this.editableUser.password = user.password;
  //         this.editableUser.roleUserByIdRole = user.roleUserByIdRole;
  //         switch (user.statusUserByIdStatus.id) {
  //           case 1:
  //             this.editableUser.statusUserByIdStatus = {id: 2, status: 'BLOCK'};
  //             this.subscriptions.push(this.userService.saveUser(this.editableUser)
  //               .subscribe((user: User) => {
  //                 this._updateComplaintByStatusId(2);
  //               }));
  //             break;
  //           case 2:
  //             this.editableUser.statusUserByIdStatus = {id: 1, status: 'ACTIVE'};
  //             this.subscriptions.push(this.userService.saveUser(this.editableUser)
  //               .subscribe((user: User) => {
  //                 this._updateComplaintByStatusId(2);
  //               }));
  //             break;
  //         }
  //       }))
  //     }))
  // }

  // public getUsername(id: number): void {
  //   this.subscriptions.push(this.complaintService.getUsernameByComplaintId(id).subscribe(username => {
  //     this.complaint.username = username;
  //   }))
  // }

  // public getComplaintById(id: number): void{
  //   this.subscriptions.push(this.complaintService.getComplaintById(id).subscribe(complaint =>{
  //     this.complaintId = complaint.id;
  //   }))
  // }
}
