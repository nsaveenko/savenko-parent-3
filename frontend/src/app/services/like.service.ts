import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Like} from "../models/Like";
import {Post} from "../models/Post";

@Injectable()
export class LikeService {
  get currLike(): Like {
    return this._currLike;
  }

  set currLike(value: Like) {
    this._currLike = value;
  }
  private _currLike: Like;

  constructor(private http: HttpClient) {
  }

  getAllLike(): Observable<Like[]> {
    return this.http.get<Like[]>('/api/like/');
  }

  saveLike(like: Like): Observable<Like> {
    return this.http.post<Like>('/api/like/', like);
  }

  deleteLike(likeId: number): Observable<void> {
    return this.http.delete<void>('/api/like/' + likeId);
  }

  getLikeById(id: number): Observable<Like> {
    return this.http.get<Like>('/api/like/' + id);
  }
}

