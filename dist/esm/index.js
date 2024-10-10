import { registerPlugin } from '@capacitor/core';
const CertificateInfo = registerPlugin('CertificateInfo', {
    web: () => import('./web').then((m) => new m.CertificateInfoWeb()),
});
export * from './definitions';
export { CertificateInfo };
//# sourceMappingURL=index.js.map