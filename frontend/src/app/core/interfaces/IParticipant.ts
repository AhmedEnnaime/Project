import TYPE from '../enums/TYPE';

interface IParticipant {
  code: string;
  bic: string;
  name: string;
  shortName: string;
  type: string;
  logo: string;
  settlementBank: string;
}

export default IParticipant;
