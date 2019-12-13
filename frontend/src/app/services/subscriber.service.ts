import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Subscriber} from "../models/Subscriber";

@Injectable()
export class SubscriberService {

  constructor(private http: HttpClient) {
  }

  getFollowersById(id: number): Observable<Subscriber> {
    return this.http.get<Subscriber>('/api/subscriptions/' + id);
  }

  saveSubscriber(subscriber: Subscriber): Observable<Subscriber> {
    return this.http.post<Subscriber>('/api/subscriptions', subscriber);
  }

  getSubscribers(): Observable<Subscriber[]> {
    return this.http.get<Subscriber[]>('/api/subscriptions/');
  }

  deleteSubscriber(subscriberId: number): Observable<void> {
    return this.http.delete<void>('/api/subscriptions/' + subscriberId);
  }
}
