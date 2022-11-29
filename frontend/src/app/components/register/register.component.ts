import { Component, OnInit } from '@angular/core';
import {SignUpInfo} from "../../model/auth/signup-info";
import {AuthService} from "../../model/auth/auth.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MessageService} from "primeng/api";
import {StepsModule} from 'primeng/steps';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  providers: [MessageService]
})
export class RegisterComponent implements OnInit {
  signupInfo: SignUpInfo | undefined;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  form!: FormGroup;
  items: MenuItem[];

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private msg: MessageService) {
    this.items = [{
      label: 'Personal',
      routerLink: 'personal'
    },
      {
        label: 'Seat',
        routerLink: 'seat'
      },
      {
        label: 'Payment',
        routerLink: 'payment'
      },
      {
        label: 'Confirmation',
        routerLink: 'confirmation'
      }
    ];
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: null,
      password: null
    });
  }

  onSubmit() {
    this.signupInfo = new SignUpInfo(
      this.form.value.username,
      this.form.value.password,
      this.form.value.name,
      this.form.value.surname,
      this.form.value.patronymic,
      this.form.value.birthday,
      this.form.value.series,
      this.form.value.passport_number,
      this.form.value.registration,
      this.form.value.issue_place,
      this.form.value.issue_date,
      this.form.value.code_division,
      this.form.value.mail);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.msg.add({severity:'success', summary: 'Регистрация', detail: 'Пользователь успешно зарегистрирован!'});
      },
      error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
        this.msg.add({severity:'error', summary: 'Регистрация', detail: 'Существует пользователь с такими данными!'});
      }
    );
  }
}
