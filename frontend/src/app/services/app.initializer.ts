import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "./user.service";
import {User} from "../models/User";
import {Router} from "@angular/router";

export function initApp(http: HttpClient, userService: UserService, router: Router) {
  return () => {
    if (localStorage.getItem('token') != null) {
      return http.get<any>('/api/user/loadByToken?token=' + localStorage.getItem('token'))
        .toPromise()
        .then((resp) => {
          userService.currUser=resp;
        },errorResponse=>{
          if (errorResponse.status == '403') {
            console.log(errorResponse);
            localStorage.removeItem('token');
          } else {
            console.log('12333',errorResponse);
            //userService.currUser = resp;
          }
        });
    }
  }
}
