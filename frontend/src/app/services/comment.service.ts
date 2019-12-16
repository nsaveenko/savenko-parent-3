import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/Comment";
import {RestPageModel} from "../models/RestPage.model";

@Injectable()
export class CommentService {

  constructor(private http: HttpClient) {
  }

  // getAllComment(): Observable<Comment[]> {
  //   return this.http.get<Comment[]>('/api/comment/');
  // }

  saveComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>('/api/comment', comment);
  }

  deleteComment(commentId: number): Observable<void> {
    return this.http.delete<void>('/api/comment/' + commentId);
  }

  getCommentById(id: number): Observable<Comment> {
    return this.http.get<Comment>('/api/comment/' + id);
  }

  // Ajax request for billing account data
  getAllCommentByPostId(id: number, page: number, size: number): Observable<RestPageModel> {
    return this.http.get<RestPageModel>("/api/comment/?postId="+ id + "&page=" + page + "&size=" + size);
  }
}
