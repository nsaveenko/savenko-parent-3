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
  errorMassage: string;

  user = new User();
  registrationForm: FormGroup;
  errors: ErrorModel[] = [];

  constructor(private  userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    // this.registrationForm = new FormGroup({
    //   flName: new FormControl("", [
    //     Validators.required,
    //     Validators.minLength(2),
    //     Validators.maxLength(40)
    //   ]),
    //   username: new FormControl('', [
    //     Validators.required,
    //     Validators.minLength(2),
    //     Validators.maxLength(20)
    //   ]),
    //   password: new FormControl('', [
    //     Validators.required,
    //     Validators.minLength(2),
    //     Validators.maxLength(20)
    //   ]),
    //   isRemember: new FormControl()
    // })
    this.registrationForm = new FormGroup({
      flName: new FormControl("", [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(25),
          Validators.pattern('^[A-Z]{1}[a-z]+\\s[A-Z]{1}[a-z]+$')
        ]
      ),
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
    const control = this.registrationForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  createUser(username: string) {
    this.userService.isExistByUsername(username).subscribe( isExist =>{
      if(isExist == false){
        this.user.username = this.registrationForm.controls['username'].value;
        this.user.flName = this.registrationForm.controls['flName'].value;
        this.user.password = this.registrationForm.controls['password'].value;
        this.user.statusUserByIdStatus = {id: 1, status: 'ACTIVE'};
        this.user.roleUserByIdRole = {id: 2, role: 'USER'};
        this.userService.saveUser(this.user).subscribe((data: User) => {
            this.user = data as User;
            if (this.user !== null) {
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
      } else {
        alert("Sorry, an error occurred while creating your account. Please try again later.");
        this.registrationForm.reset();
      }
    })
  }

  // createUser() {
  //   this.user.username = this.registrationForm.controls['username'].value;
  //   this.user.flName = this.registrationForm.controls['flName'].value;
  //   this.user.password = this.registrationForm.controls['password'].value;
  //   this.user.statusUserByIdStatus = {id: 1, status: 'ACTIVE'};
  //   this.user.roleUserByIdRole = {id: 2, role: 'USER'};
  //   this.userService.saveUser(this.user).subscribe((data: User) => {
  //       this.user = data as User;
  //       if (this.user !== null) {
  //         this.router.navigate(['/entry']);
  //       }
  //     }, response => {
  //       if (this.errors != null)
  //         this.errors = [];
  //       let resError;
  //       for (let i = 0; i < response.error.length; i++) {
  //         resError = new ErrorModel();
  //         resError.field = (response as HttpErrorResponse).error[i].field;
  //         resError.defaultMessage = (response as HttpErrorResponse).error[i].defaultMessage;
  //         this.errors.push(resError);
  //       }
  //     }
  //   );
  // }
}
