import {Component, OnInit} from '@angular/core';
import {Complaint} from "../../models/Complaint";
import {Subscription} from "rxjs";
import {ComplaintService} from "../../services/complaint.service";
import {Post} from "../../models/Post";
import {PostService} from "../../services/post.service";
import {UserService} from "../../services/user.service";
import {User} from "../../models/User";
import {b} from "@angular/core/src/render3";
import {RestPageModel} from "../../models/RestPage.model";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-complaintsforadmin',
  templateUrl: './complaintsforadmin.component.html',
  styleUrls: ['./complaintsforadmin.component.css']
})
export class ComplaintsforadminComponent implements OnInit {

  public complaints: Complaint[];
  public posts: Post[];
  private subscriptions: Subscription[] = [];
  public editableComplaint: Complaint = new Complaint();
  public editableUser: User = new User();
  public message: string;
  private postId: number;
  private userId: number;
  private statusUser: string;
  private statusComplaint: string;
  private statusComplaintId: number;
  private username: string;
  private complaintId: number;
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
    //this.loadComplaint();
    //this.loadComplaintByStatusId(2);
    // this.getStatusComplaint(this.complaint.id);
    // this.getUsername(this.complaint.id);
    this.subs = [];
    this.subs[this.subs.length] = this.getComplaint(this.currentPage, 1);
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage = event.page;
    console.log(this.currentPage);
    this.getComplaint(this.currentPage, 1);
  }

  private getComplaint(page: number, statusId: number) {
    this.currentPage = page;
    console.log(this.currentPage);
    this.complaintService.getComplaintsByStatusId(statusId, this.currentPage - 1, this.size)
      .subscribe((pageModel: RestPageModel) => {
        this.page = pageModel;
        this.complaints = pageModel.content;
        this.subs.length = pageModel.content.length;
      });
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

  // private refreshComplaint(): void {
  //   this.editableComplaint = new Complaint();
  // }
  //
  // private loadComplaint(): void {
  //   this.subscriptions.push(this.complaintService.getComplaint().subscribe(complaints => {
  //     if (complaints.length > 0) {
  //       this.complaints = complaints as Complaint[];
  //     }
  //   }));
  // }

  public _updateComplaint(id: number): void {
    this.getComplaint(this.currentPage, id);
  }

  public _deleteComplaint(complaintId: number): void {
    this.subscriptions.push(this.complaintService.deleteComplaint(complaintId).subscribe(() => {
      this._updateComplaint(2);
    }));
  }

  public _deletePost(postId: number): void {
    this.subscriptions.push(this.complaintService.deletePost(postId).subscribe(() => {
      this._updateComplaint(2);
    }));
  }

  // public getStatusComplaint(id: number): void {
  //   this.subscriptions.push(this.complaintService.getStatusComplaint(id).subscribe(statusComplaint => {
  //     this.complaint.statusComplaint = statusComplaint;
  //   }))
  // }
  //
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
