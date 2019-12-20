import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {UserService} from "../user.service";
import {Observable} from "rxjs";

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(private userService: UserService,
              public router: Router) {
  }

  public isAuthenticated(): boolean {
    if (this.userService.currUser) {
      console.log(this.userService.currUser);
      return true;
    } else {
      console.log(this.userService.currUser);
      return false;
    }
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.isAuthenticated()) {
      localStorage.setItem('err', 'Not authorized! Log in with your account');
      this.router.navigate(['/entry']);
      return false;
    }
    return true;
  }
}
