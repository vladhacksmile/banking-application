export class CardSettingsRequest {
  active: boolean;
  roundingStep: CardRoundingStep;
  cashback1: number;
  cashback2: number;
  cashback3: number;
  cashback4: number;

  constructor(active: boolean, roundingStep: CardRoundingStep, cashback1: number, cashback2: number, cashback3: number, cashback4: number) {
    this.active = active;
    this.roundingStep = roundingStep;
    this.cashback1 = cashback1;
    this.cashback2 = cashback2;
    this.cashback3 = cashback3;
    this.cashback4 = cashback4;
  }
}

enum CardRoundingStep {
  STEP10, STEP50,STEP100, STEP1000
}
