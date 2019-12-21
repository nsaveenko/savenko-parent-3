import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PostService} from "../../services/post.service";
import {Subscription} from "rxjs";
import {Post} from "../../models/Post";
import {Router} from "@angular/router";
import {flatMap, skip, switchMapTo, take} from "rxjs/operators";
import {UserService} from "../../services/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

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
  form: FormGroup;

  constructor(private postService: PostService,
              private userService: UserService,
              private http: HttpClient,
              private router: Router) {
  }

  ngOnInit() {
    this.editablePost.datePost = Date.now();
    this.editablePost.idUser = this.userService.currUser.id;
    this.form = new FormGroup({
      file:new FormControl("", []),
      caption: new FormControl("", [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(200),
      ]),
      isRemember: new FormControl()
    });
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.form.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  onSubmit() {
    alert(JSON.stringify(this.form.value));
  }

  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
  }

  public _updatePost(): void {
    this.loadPost();
    this.editablePost.idUser = this.userService.currUser.id;
  }

  public _addPost(): void {
    this.postService.savePost(this.editablePost)
      .pipe(
        flatMap((post: Post) => this.postService.putFileToPostByPostId(post.id, this.selectedFile)),
        skip(4)
      )
      .subscribe(post => {
        this.router.navigate(['/user']);
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
