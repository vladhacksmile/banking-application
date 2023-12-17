export class SignUpInfo {
  username: string;
  password: string;
  name: string;
  surname: string;
  patronymic: string;
  birthday: Date;
  series: number;
  passportNumber: number;
  registration: string;
  issuePlace: string;
  issueDate: Date;
  codeDivision: number;
  mail: string;


  constructor(username: string, password: string, name: string, surname: string, patronymic: string, birthday: Date, series: number, passportNumber: number, registration: string, issuePlace: string, issueDate: Date, codeDivision: number, mail: string) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.patronymic = patronymic;
    this.birthday = birthday;
    this.series = series;
    this.passportNumber = passportNumber;
    this.registration = registration;
    this.issuePlace = issuePlace;
    this.issueDate = issueDate;
    this.codeDivision = codeDivision;
    this.mail = mail;
  }
}
