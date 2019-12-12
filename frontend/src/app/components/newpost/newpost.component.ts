import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PostService} from "../../services/post.service";
import {Subscription} from "rxjs";
import {Post} from "../../models/Post";
import {Router} from "@angular/router";
import {flatMap, skip, switchMapTo, take} from "rxjs/operators";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-newpost',
  templateUrl: './newpost.component.html',
  styleUrls: ['./newpost.component.css']
})
export class NewpostComponent implements OnInit {

  selectedFile: File = null;

  public post: Post[];
  private subscriptions: Subscription[] = [];
  public editablePost: Post = new Post();
  constructor(private postService: PostService,
              private userService: UserService,
              private http: HttpClient,
              private router: Router) {
  }

  ngOnInit() {
    this.editablePost.datePost = Date.now();
    this.editablePost.idUser = this.userService.currUser.id;
  }

  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  public _updatePost(): void {
    this.loadPost();
    this.editablePost.idUser = this.userService.currUser.id;
  }

  public _addPost(): void {
    debugger;
    this.postService.savePost(this.editablePost)
      .pipe(
        flatMap((post: Post) => this.postService.putFileToPostByPostId(post.id, this.selectedFile)),
        skip(4)
      )
      .subscribe(post => {
        console.log(post);
        this.router.navigate(['/']);
      })
  }

  private refreshPost(): void {
    this.editablePost = new Post();
  }

  private loadPost(): void {
    this.subscriptions.push(this.postService.getPost().subscribe(posts => {
      this.post = posts as Post[];
      console.log(this.post);
    }));
  }

}
