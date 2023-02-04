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

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private msg: MessageService) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: null,
      password: null,
      name: null,
      surname: null,
      patronymic: null,
      birthday: null,
      passport_series: null,
      passport_number: null,
      passport_code: null,
      passport_registration: null,
      passport_issue_place: null,
      passport_issue_date: null,
      mail: null
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
      this.form.value.passport_series,
      this.form.value.passport_number,
      this.form.value.passport_code,
      this.form.value.passport_registration,
      this.form.value.passport_issue_place,
      this.form.value.passport_issue_date,
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
