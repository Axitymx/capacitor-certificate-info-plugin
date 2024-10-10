import { registerPlugin } from '@capacitor/core';

import type { CertificateInfoPlugin } from './definitions';

const CertificateInfo = registerPlugin<CertificateInfoPlugin>('CertificateInfo', {
  web: () => import('./web').then((m) => new m.CertificateInfoWeb()),
});

export * from './definitions';
export { CertificateInfo };
