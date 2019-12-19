import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Complaint} from "../models/Complaint";
import {RequestOptions, Headers} from "@angular/http";
import {RestPageModel} from "../models/RestPage.model";

@Injectable()
export class ComplaintService {

  constructor(private http: HttpClient) {
  }

  getComplaint(): Observable<Complaint[]> {
    return this.http.get<Complaint[]>('/api/complaint');
  }

  saveComplaint(complaint: Complaint): Observable<Complaint> {
    return this.http.post<Complaint>('/api/complaint', complaint);
  }

  deleteComplaint(complaintId: number): Observable<void> {
    return this.http.delete<void>('/api/complaint/' + complaintId);
  }

  deletePost(postId: number): Observable<void> {
    return this.http.delete<void>('/api/post/' + postId);
  }

  getComplaintById(id: number): Observable<Complaint> {
    return this.http.get<Complaint>('/api/complaint/' + id);
  }

  // getComplaintsByStatusId(id: number): Observable<Complaint[]> {
  //   return this.http.get<Complaint[]>('/api/complaint/status/' + id);
  // }

  // Ajax request for billing account data
  getComplaintsByStatusId(id: number, page: number, size: number): Observable<RestPageModel> {
    return this.http.get<RestPageModel>("/api/complaint/status?id="+ id + "&page=" + page + "&size=" + size);
  }

  getUserIdByPostId(postId: number): Observable<number>{
    return this.http.get<number>('api/complaint/user/post/' + postId);
  }

  getUsernameByComplaintId(complaintId: number): Observable<string>{
    return  this.http.get<string>('api/complaint/username/' + complaintId);
  }
  getStatusComplaint(complaintId: number): Observable<string>{
    return  this.http.get<string>('api/statusComplaint/username/' + complaintId);
  }
}
