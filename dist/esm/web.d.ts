import { WebPlugin } from '@capacitor/core';
import type { CertDate, CertExpiration, CertExpirations, CertFilename, CertificateInfoPlugin, CertValid } from './definitions';
export declare class CertificateInfoWeb extends WebPlugin implements CertificateInfoPlugin {
    getExpirationDate(params: CertFilename): Promise<CertExpiration>;
    getExpirationDates(): Promise<CertExpirations>;
    checkValidation(params?: CertDate): Promise<CertValid>;
}
