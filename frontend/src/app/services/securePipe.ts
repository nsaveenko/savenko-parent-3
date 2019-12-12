import {Pipe, PipeTransform} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {Observable} from "rxjs";
import {map, tap} from "rxjs/operators";

@Pipe({
  name: 'secure'
})
export class SecurePipe implements PipeTransform {

  constructor(private http: HttpClient, private domSanitizer: DomSanitizer) { }

  transform(url): Observable<SafeUrl> {
    return this.http
      .get(url, {responseType: 'blob'})
      .pipe(
        map(e => this.domSanitizer.bypassSecurityTrustUrl(URL.createObjectURL(e))),
        tap(value => console.log(value))
      )
  }
}
