import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {Post} from "../../models/Post";
import {Comment} from "../../models/Comment";
import {PostService} from "../../services/post.service";
import {Subscription} from "rxjs";
import {Config} from "../../services/config";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {CommentService} from "../../services/comment.service";
import {LikeService} from "../../services/like.service";
import {Like} from "../../models/Like";
import {User} from "../../models/User";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input() selectedUser: User;
  public comments: Comment[];
  public editableComment: Comment = new Comment();
  public likes: Like[];
  public editableLike: Like = new Like();
  public posts: Post[];
  private subscriptions: Subscription[] = [];
  public pathUrl = `${Config.POSTFIX}`;
  public commentId;

  constructor(private postService: PostService,
              private userService: UserService,
              private likeService: LikeService,
              private commentService: CommentService,
              private router: Router) { }

  ngOnInit() {
    this.loadPost();
  }

  public _updatePost(): void {
    this.loadPost();
  }

  public _deletePost(postId: number): void {
    this.subscriptions.push(this.postService.deletePost(postId).subscribe(() => {
      this._updatePost();
    }));
  }

  private loadPost(): void {
    this.subscriptions.push(this.postService.getPost()
      .subscribe(posts => {
        if(this.selectedUser){
          this.posts = posts.filter((post: Post) => post.userByIdUser.id === this.selectedUser.id);
          this.selectedUser.postCount = this.posts.filter((post : Post) => post.userByIdUser.id === this.selectedUser.id).length;
        } else{
          this.posts = posts;
        }
        this.loadLike();
    }));
  }

  _complaint(id: number) {
    const post = new Post();
    post.id = id;
    this.postService.currPost = post;
    this.router.navigate(['complaint']);
  }

  _addComment(postId: number, textValue: string) : void{
    this.editableComment.dataPost = Date.now();
    this.editableComment.idPost = postId;
    this.editableComment.tex = textValue;
    this.editableComment.userByIdUser = this.userService.currUser;
    debugger;
    this.subscriptions.push(this.commentService.saveComment(this.editableComment).subscribe((comment) => {
      this.commentId = comment.id;
      }));
  }

  public _onLikeClick(postId: number): void {
    this.editableLike.idPost = postId;
    const currUser = this.userService.currUser.id;
    this.editableLike.idUser = currUser;
    const userLike = this.likes.find((like: Like) => like.userByIdUser.id === currUser && like.idPost === postId);
    if (userLike) {
      this.subscriptions.push(this.likeService.deleteLike(userLike.id).subscribe(() => {
        this._updateLike();
      }));
    } else {
      this.subscriptions.push(this.likeService.saveLike(this.editableLike).subscribe(() => {
        this._updateLike();
      }));
    }
  }

  public _updateLike(): void {
    this.loadLike();
  }

  private loadLike(): void {
    this.subscriptions.push(this.likeService.getAllLike().subscribe(likes => {
      this.likes = likes as Like[];
      this.posts.forEach((post) => {
        post.likesCount = this.likes.filter((like : Like) => like.idPost === post.id).length;
        post.isLiked = this.likes.some((like: Like) => like.userByIdUser.id === this.userService.currUser.id && like.idPost === post.id);
      });
      console.log(this.likes);
    }));
  }
}
