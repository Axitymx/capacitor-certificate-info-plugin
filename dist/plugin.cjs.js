'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var core = require('@capacitor/core');

const CertificateInfo = core.registerPlugin('CertificateInfo', {
    web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.CertificateInfoWeb()),
});

class CertificateInfoWeb extends core.WebPlugin {
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

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    CertificateInfoWeb: CertificateInfoWeb
});

exports.CertificateInfo = CertificateInfo;
//# sourceMappingURL=plugin.cjs.js.map
