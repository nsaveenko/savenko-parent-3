import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {SignIn} from "../models/SignIn";
import {LogInParam} from "../models/LogInParam";

@Injectable()
export class LogInService {
  constructor(private http: HttpClient) { }

  signIn (email: string, password: string): Observable<SignIn> {
    let signInParam: LogInParam = new LogInParam(email, password);
    return this.http.post<SignIn>('/api/user/signin', signInParam);
  }
}
