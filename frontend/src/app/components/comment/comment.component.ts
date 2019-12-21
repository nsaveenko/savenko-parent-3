import {Component, Input, OnInit} from '@angular/core';
import {Comment} from "../../models/Comment";
import {Post} from "../../models/Post";
import {PostService} from "../../services/post.service";
import {UserService} from "../../services/user.service";
import {CommentService} from "../../services/comment.service";
import {Subscription} from "rxjs";
import {User} from "../../models/User";
import {PageChangedEvent} from "ngx-bootstrap";
import {map, tap} from "rxjs/operators";
import {RestPageModel} from "../../models/RestPage.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input() selectedUser: User;
  @Input() selectedPost: Post;

  @Input()
  set commentId(commentId: number) {
    this._updateComment();
  };

  public comments: Comment[];
  public posts: Post[];
  private subscriptions: Subscription[] = [];
  public currentPage: number = 1;
  private subs: any;
  private size: number = 3;
  private page: RestPageModel;
  message: string;

  constructor(private postService: PostService,
              private userService: UserService,
              private commentService: CommentService,
              private router: Router) {
  }

  ngOnInit() {
    // this.loadComment();
    this.subs = [];
    this.subs[this.subs.length] = this.getComment(this.currentPage);
  }

  routUser(userId: number) {
    if (userId === this.userService.currUser.id) {
      this.router.navigate(['user']);
      this.message = null;
    } else {
      this.router.navigate(['/otherUser'], { queryParams: { id: userId } });
      this.message = null;
    }
  }

  public _deleteComment(CommentId: number): void {
    this.subscriptions.push(this.commentService.deleteComment(CommentId).subscribe(() => {
      this._updateComment();
    }));
  }

  public _updateComment(): void {
    this.getComment(1);
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage = event.page;
    console.log(this.currentPage);
    this.getComment(this.currentPage);
  }

  private getComment(page: number) {
    this.currentPage = page;
    console.log(this.currentPage);
    this.commentService.getAllCommentByPostId(this.selectedPost.id, this.currentPage - 1, this.size)
      .subscribe((pageModel: RestPageModel) => {
        this.page = pageModel;
        this.comments = pageModel.content;
        this.subs.length = pageModel.content.length;
      });
  }
  // private getComment(page: number) {
  //   this.currentPage = page;
  //   //this.subs[this.subs.length] =
  //   console.log(this.currentPage);
  //   this.commentService.getAllCommentByPostId(this.selectedPost.id, this.currentPage - 1, this.size)
  //     .pipe(
  //       map((pageResponse: RestPageModel) => pageResponse.content)
  //     )
  //     .subscribe((comments: Comment[]) => {
  //       this.comments = comments;
  //       this.subs.length = comments.length;
  //     });
  // }

  // private loadComment(): void {
  //   this.subscriptions.push(this.commentService.getAllComment().subscribe(comments => {
  //     if(this.selectedPost){
  //       this.comments = comments.filter((comment: Comment) => comment.idPost === this.selectedPost.id) ;
  //     } else{
  //       this.comments = comments;
  //     }
  //    }));
  // }
}
