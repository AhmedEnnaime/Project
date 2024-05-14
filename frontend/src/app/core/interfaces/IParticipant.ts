import TYPE from '../enums/TYPE';

interface IParticipant {
  code: number;
  bic: string;
  name: string;
  shortName: string;
  type: string;
  logo: string;
  settlementBank: string;
}

export default IParticipant;
