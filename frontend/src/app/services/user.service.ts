import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/User";
import {SignIn} from "../models/SignIn";
import {LogInParam} from "../models/LogInParam";
import {Post} from "../models/Post";

@Injectable()
export class UserService {

  private _reqHeader: HttpHeaders;
  private _currUser: User;

  constructor(private http: HttpClient){
    this._reqHeader=new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer_' + localStorage.getItem('token')
    });
  }

  get currUser(): User {
    return this._currUser;
  }

  set currUser(value: User) {
    this._currUser = value;
  }

  get reqHeader(): HttpHeaders {
    return this._reqHeader;
  }

  set reqHeader(value: HttpHeaders) {
    this._reqHeader = value;
  }

  getUserByUsernameAndPassword(signIn: LogInParam): Observable<User> {
    return this.http.post<User>('/api/user/signin', signIn);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>('/api/user/' + id);
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>('/api/user/registration', user);
  }
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/user/');
  }
}
