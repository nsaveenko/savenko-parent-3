import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "../user.service";
import {Injectable} from "@angular/core";

@Injectable()
export class AdminGuardService implements CanActivate {
  constructor(private userService: UserService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.userService.currUser.roleUserByIdRole.id != 1) {
      localStorage.setItem('err', 'No permissions for this action!');
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
