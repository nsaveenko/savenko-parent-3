import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Complaint} from "../models/Complaint";
import {RequestOptions, Headers} from "@angular/http";

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
    return this.http.delete<void>( '/api/complaint/' + complaintId);
  }

  deletePost(postId: number): Observable<void> {
    return this.http.delete<void>('/api/post/' + postId);
  }

  getComplaintById(id: number): Observable<Complaint> {
    return this.http.get<Complaint>('/api/complaint/' + id);
  }
}
