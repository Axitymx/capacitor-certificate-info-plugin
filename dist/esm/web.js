import { WebPlugin } from '@capacitor/core';
export class CertificateInfoWeb extends WebPlugin {
    async getExpirationDate(params) {
        console.log("getExpirationDate: ", params);
        throw this.unavailable('ERROR: Certificate Info Plugin not Implemented');
    }
    async getExpirationDates() {
        throw this.unavailable('ERROR: Certificate Info Plugin not Implemented');
    }
    async checkValidation(params) {
        console.log("checkValidation: ", params);
        throw this.unavailable('ERROR: Certificate Info Plugin not Implemented');
    }
}
//# sourceMappingURL=web.js.map