import dayjs from 'dayjs/esm';

import { IMemberBank, NewMemberBank } from './member-bank.model';

export const sampleWithRequiredData: IMemberBank = {
  id: 24673,
};

export const sampleWithPartialData: IMemberBank = {
  id: 53520,
  ifsccode: 'success Operations',
  lastModified: dayjs('2022-08-04T14:30'),
  createdBy: 'Steel Portugal Security',
  createdOn: dayjs('2022-08-04T12:59'),
  isDeleted: false,
};

export const sampleWithFullData: IMemberBank = {
  id: 8546,
  bankName: 'Communications Land Dollar',
  branchName: 'Directives',
  accountNumber: 93322,
  ifsccode: 'Comoro Monitored',
  lastModified: dayjs('2022-08-05T05:37'),
  lastModifiedBy: 'SMTP',
  createdBy: 'Facilitator',
  createdOn: dayjs('2022-08-04T23:00'),
  isDeleted: true,
};

export const sampleWithNewData: NewMemberBank = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
