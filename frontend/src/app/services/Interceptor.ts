import {Injectable} from "@angular/core";
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";

@Injectable()
export class Interceptor implements HttpInterceptor {
  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq;
    if (localStorage.getItem("token") != null) {
      authReq = req.clone({
        headers: req.headers.set('Authorization', 'Bearer_' + localStorage.getItem("token"))
      });
    } else {
      authReq = req.clone();
    }

    return next.handle(authReq)
      .pipe(
        tap(event => {
          if (event instanceof HttpResponse)
            console.log('Server response');
        }, err => {
          if (err instanceof HttpErrorResponse) {
            if (err.status == 401) console.log('Unauthorized');
          }
        })
      );
  }
}
