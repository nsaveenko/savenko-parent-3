import {Component, OnInit} from '@angular/core';
import {User} from "../../models/User";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorModel} from "../../models/Error.model";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user = new User();
  registrationForm: FormGroup;
  errors: ErrorModel[] = [];

  constructor(private  userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    this.registrationForm = new FormGroup({
      flName: new FormControl("", [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(40)
      ]),
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20)
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20)
      ])
    })
  }

  createUser() {
    this.user.username = this.registrationForm.controls['username'].value;
    this.user.flName = this.registrationForm.controls['flName'].value;
    this.user.password = this.registrationForm.controls['password'].value;
    this.user.statusUserByIdStatus = {id: 1, status: 'ACTIVE'};
    this.user.roleUserByIdRole = {id: 2, role: 'USER'};
    // this.user = {
    //   id: null,
    //   username: this.registrationForm.controls['username'].value,
    //   flName: this.registrationForm.controls['flName'].value,
    //   password: this.registrationForm.controls['password'].value,
    //   statusUserByIdStatus: {id: 1, status: 'ACTIVE'},
    //   roleUserByIdRole: {id: 2, role: 'USER'},
    // };
    console.log(this.user);
    this.userService.saveUser(this.user).subscribe((data: User) => {
        this.user = data as User;
        if (this.user !== null) {
          console.log(this.user);
          this.router.navigate(['/entry']);
        }
      }, response => {
        if (this.errors != null)
          this.errors = [];
        let resError;
        for (let i = 0; i < response.error.length; i++) {
          resError = new ErrorModel();
          resError.field = (response as HttpErrorResponse).error[i].field;
          resError.defaultMessage = (response as HttpErrorResponse).error[i].defaultMessage;
          this.errors.push(resError);
        }
      }
    );
  }
}
