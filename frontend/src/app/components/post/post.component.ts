import {AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {Post} from "../../models/Post";
import {Comment} from "../../models/Comment";
import {PostService} from "../../services/post.service";
import {Subscription} from "rxjs";
import {Config} from "../../services/config";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {CommentService} from "../../services/comment.service";
import {LikeService} from "../../services/like.service";
import {Like} from "../../models/Like";
import {User} from "../../models/User";
import {Subscriber} from "../../models/Subscriber";
import {SubscriberService} from "../../services/subscriber.service";
import {RestPageModel} from "../../models/RestPage.model";
import {PageChangedEvent} from "ngx-bootstrap";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit, OnChanges {

  @Input() selectedUserId: number;
  @Input() parentPage: string;
  @Input() selectedUser: User;

  @Input()
  set postId(postId: number) {
    this._updatePostCurrUser();
  };

  public user: User;
  public subscribers: Subscriber[];
  public editableComment: Comment = new Comment();
  public likes: Like[];
  public editableLike: Like = new Like();
  public posts: Post[];
  private subscriptions: Subscription[] = [];
  public pathUrl = `${Config.POSTFIX}`;
  public commentId;
  form: FormGroup;
  message: string;
  errorsMapComment: Map<string, string> = new Map<string, string>();
  commentCreatedFlag: boolean = false;

  constructor(private postService: PostService,
              private userService: UserService,
              private likeService: LikeService,
              private subscriberService: SubscriberService,
              private commentService: CommentService,
              private router: Router) {
  }

  ngOnInit() {
    this.loadSub();
    this.form = new FormGroup({
      comment: new FormControl("", [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
          Validators.pattern('^[A-Z\'\\-.,:)(!?;a-z0-9]{1}[A-Z \'\\-.,:)(!?;a-z0-9]+$'),
        ]
      ),
      isRemember: new FormControl()
    });
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.form.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  routUser(userId: number) {
    if (userId === this.userService.currUser.id) {
      this.router.navigate(['user']);
      this.message = null;
    } else {
      this.router.navigate(['/otherUser'], { queryParams: { id: userId } });
      this.message = null;
      this.loadPostOtherUser();
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.parentPage.currentValue === 'feed') {
      this.loadPost();
    } else if (changes.parentPage.currentValue === 'user') {
      this.loadPostByCurrUser();
    } else if (changes.parentPage.currentValue === '/otherUser?id=' + this.selectedUserId) {
      this.loadPostOtherUser();
    }
  }

  public _updatePostCurrUser(): void {
    this.loadPostByCurrUser();
  }

  public _deletePost(postId: number): void {
    this.subscriptions.push(this.postService.deletePost(postId).subscribe(() => {
      this._updatePostCurrUser();
    }));
  }

  private loadPost(): void {
    this.subscriptions.push(this.postService.getPostBySub(this.userService.currUser.id)
      .subscribe(posts => {
        if (posts.length !== 0) {
          this.posts = posts;
          this.loadLike();
        } else {
          this.posts = null;
          this.message = "Welcome to Photogram. When you follow people, you'll see the photos they post here.";
        }
      }));
  }

  private loadPostByCurrUser(): void {
    this.subscriptions.push(this.postService.getPostByCurrUser(this.userService.currUser.id)
      .subscribe(posts => {
        if (posts.length !== 0) {
          this.posts = posts;
          this.userService.currUser.postsCount = this.posts.length;
          this.loadLike();
        } else {
          this.posts = null;
          this.message = "Share Photos. When you share photos, they'll appear on your profile.";
        }
      }));
  }

  private loadPostOtherUser(): void {
    this.subscriptions.push(this.postService.getPostByCurrUser(this.selectedUserId)
      .subscribe(posts => {
        if (posts.length !== 0) {
          this.posts = posts;
          this.loadLike();
        } else {
          this.posts = null;
          this.message = "No Posts Yet";
        }
      }));
  }

  _complaint(id: number) {
    const post = new Post();
    post.id = id;
    this.postService.currPost = post;
    this.router.navigate(['complaint']);
  }

  _addComment(postId: number, textValue: string): void {
    this.editableComment.dataPost = Date.now();
    this.editableComment.idPost = postId;
    this.editableComment.tex = textValue;
    this.editableComment.userByIdUser = this.userService.currUser;
    this.subscriptions.push(this.commentService.saveComment(this.editableComment).subscribe((comment) => {
      this.commentId = comment.id;
    }));
    this.form.reset();
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
        post.likesCount = this.likes.filter((like: Like) => like.idPost === post.id).length;
        post.isLiked = this.likes.some((like: Like) => like.userByIdUser.id === this.userService.currUser.id && like.idPost === post.id);
      });
    }));
  }

  private loadSub(): void {
    this.subscriptions.push(this.subscriberService.getSubscribers().subscribe(subs => {
      this.subscribers = subs;
    }));
  }

  private loadUserById(id: number): void {
    this.subscriptions.push(this.userService.getUserById(id).subscribe(user => {
      this.user = user;
    }));
  }

  public _updateUser(id: number): void {
    this.loadUserById(id);
  }
}
