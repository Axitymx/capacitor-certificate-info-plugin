var capacitorCertificateInfo = (function (exports, core) {
    'use strict';

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

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
