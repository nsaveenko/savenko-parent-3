import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../models/Post";

@Injectable()
export class PostService {
  private _currPost: Post;

  get currPost(): Post {
    return this._currPost;
  }

  set currPost(value: Post) {
    this._currPost = value;
  }

  constructor(private http: HttpClient) {
  }

  getPost(): Observable<Post[]> {
    return this.http.get<Post[]>('/api/post/');
  }

  getPostBySub(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>('/api/post/followers/' + userId);
  }

  getPostByCurrUser(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>('/api/post/currUser/' + userId);
  }

  savePost(post: Post): Observable<Post> {
    return this.http.post<Post>('/api/post/', post);
  }

  deletePost(postId: number): Observable<void> {
    return this.http.delete<void>('/api/post/' + postId);
  }

  getPostById(id: number): Observable<Post> {
    return this.http.get<Post>('/api/post/' + id);
  }

  getCountPostByUserId(UserId: number): Observable<number> {
    return this.http.get<number>('api/post/count/user/' + UserId);
  }

  putFileToPostByPostId(postId: number, file: File): Observable<any> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    const req = new HttpRequest('POST', `/api/post/${postId.toString()}/image`, formdata, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }
}
