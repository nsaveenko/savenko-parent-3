import {Component, OnInit} from '@angular/core';
import {Post} from "../../models/Post";
import {Subscription} from "rxjs";
import {ComplaintService} from "../../services/complaint.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Complaint} from "../../models/Complaint";
import {PostService} from "../../services/post.service";
import {UserService} from "../../services/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-complaint-post',
  templateUrl: './complaintAndPost.component.html',
  styleUrls: ['./complaintAndPost.component.css']
})
export class ComplaintAndPostComponent implements OnInit {

  public complaint: Complaint[];
  public posts: Post[];
  //complaintCount: number;

  constructor(private complaintService: ComplaintService,
              private postService: PostService,
              private userService: UserService,
              private http: HttpClient,
              private router: Router) {
  }

  ngOnInit() {
    this.loadComplaintByPostId(122);
  }

  public loadComplaintByPostId(postId:number): void{
    this.complaintService.getComplaintOnPost(postId).subscribe((complaints) =>{
      //this.complaintCount = complaints.length;
      alert(complaints.length);
    })
  }

  // public _addComplaint(textValue: string): void {
  //   this.editableComplaint.dateComplaint = Date.now();
  //   this.editableComplaint.idPost = this.postService.currPost.id;
  //   this.editableComplaint.idUser = this.userService.currUser.id;
  //   this.editableComplaint.complaint = textValue;
  //   this.editableComplaint.idStatusComplaint = 2;
  //   this.subscriptions.push(this.complaintService.saveComplaint(this.editableComplaint).subscribe((complaint: Complaint) => {
  //     this.router.navigate(['/']);
  //   }));
  // }
}
