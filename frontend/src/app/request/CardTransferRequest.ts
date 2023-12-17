export class CardTransferRequest {
  amount: number;
  number: string;

  constructor(amount: number, number: string) {
    this.amount = amount;
    this.number = number;
  }
}
