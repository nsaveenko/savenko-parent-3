import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {UserService} from "../user.service";
import {Observable} from "rxjs";

@Injectable()
export class UserGuardService implements CanActivate {
  constructor(private userService: UserService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.userService.currUser.roleUserByIdRole.id != 2) {
      localStorage.setItem('err', 'No permissions for this action!');
      this.router.navigate(['/admin']);
      return false;
    }
    return true;
  }
}
