import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "./user.service";

export function initApp(http: HttpClient, userService: UserService) {
  return () => {
    // return http.get('/api/user/' + 8)
    //   .toPromise()
    //   .then((resp) => {
    //     console.log('Response 1 - ', resp);
    //   });
  };
}
