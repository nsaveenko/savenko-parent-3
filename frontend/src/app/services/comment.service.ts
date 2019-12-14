import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/Comment";

@Injectable()
export class CommentService {

  constructor(private http: HttpClient) {
  }

  getAllComment(): Observable<Comment[]> {
    return this.http.get<Comment[]>('/api/comment/');
  }

  saveComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>('/api/comment', comment);
  }

  deleteComment(commentId: number): Observable<void> {
    return this.http.delete<void>('/api/comment/' + commentId);
  }

  getCommentById(id: number): Observable<Comment> {
    return this.http.get<Comment>('/api/comment/' + id);
  }
}
