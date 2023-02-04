export class SignUpInfo {
  username: string;
  password: string;
  name: string;
  surname: string;
  patronymic: string;
  birthday: Date;
  series: number;
  passport_number: number;
  registration: string;
  issue_place: string;
  issue_date: Date;
  code_division: number;
  mail: string;

  constructor(username: string, password: string,  name: string, surname: string, patronymic: string, birthday: Date,
  series: number, passport_number: number, code_division: number, registration: string, issue_place: string, issue_date: Date, mail: string) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.patronymic = patronymic;
    this.birthday = birthday;
    this.series = series;
    this.passport_number = passport_number;
    this.registration = registration;
    this.issue_place = issue_place;
    this.issue_date = issue_date;
    this.code_division = code_division;
    this.mail = mail;
  }
}
