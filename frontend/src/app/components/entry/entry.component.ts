import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {User} from "../../models/User";
import {Subscription} from "rxjs";
import {LogInService} from "../../services/logIn.service";
import {LogInParam} from "../../models/LogInParam";
import {b} from "@angular/core/src/render3";

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.css']
})
export class EntryComponent implements OnInit {

  errorMassage: string;
  user: User;
  signInForm: FormGroup;
  errorSignIn: string;
  private subscriptions: Subscription[] = [];

  constructor(private  userService: UserService,
              private logInService: LogInService,
              private router: Router) {
  }

  ngOnInit() {
    this.signInForm = new FormGroup({
      username: new FormControl("", [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(25),
          Validators.pattern('^[A-Z0-9a-z\_]+$')
        ]
      ),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(25),
        Validators.pattern('^[0-9a-zA-Z]+$')
      ]),
      isRemember: new FormControl()
    });
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.signInForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  signIn(username: string) {
    //debugger;
    this.subscriptions.push(this.userService.isExistByUsername(username).subscribe(isExist => {
      if (isExist === true) {
        this.subscriptions.push(this.logInService.signIn(this.signInForm.controls['username'].value, this.signInForm.controls['password'].value)
          .subscribe(signIn => {
            if (signIn.error == null) {
              this.errorSignIn = null;
              localStorage.setItem("token", signIn.token);
              if (signIn.user) {
                this.userService.currUser = signIn.user;
                switch (signIn.user.roleUserByIdRole.id) {
                  case 2:
                    if (signIn.user.statusUserByIdStatus.id === 1) {
                      this.router.navigate(['/user']);
                    } else {
                      this.errorMassage = 'Your account was blocked by administration.';
                      localStorage.clear();
                    }
                    break;
                  case 1:
                    this.router.navigate(['/admin']);
                    break;
                }
              } else {
                this.errorMassage = 'Incorrect data. Recheck entered data';
              }
            } else {
              this.errorSignIn = signIn.error;
              this.signInForm.reset();
            }
          }));
      } else {
        alert("The username you entered does not belong to your account. Verify your username and try again.");
        this.signInForm.reset();
      }
    }))
  }
}
