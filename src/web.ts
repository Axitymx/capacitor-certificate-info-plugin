import { WebPlugin } from '@capacitor/core';

import type { CertDate, CertExpiration, CertExpirations, CertFilename, CertificateInfoPlugin, CertValid } from './definitions';

export class CertificateInfoWeb extends WebPlugin implements CertificateInfoPlugin {
    async getExpirationDate(params: CertFilename): Promise<CertExpiration> {
      console.log("getExpirationDate: ", params);
      throw this.unavailable('ERROR: Certificate Info Plugin not Implemented')
    }

    async getExpirationDates(): Promise<CertExpirations> {

      throw this.unavailable('ERROR: Certificate Info Plugin not Implemented')
    }

    async checkValidation(params?: CertDate): Promise<CertValid> {
      console.log("checkValidation: ", params);
      throw this.unavailable('ERROR: Certificate Info Plugin not Implemented')
    }
}
