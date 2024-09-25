import { registerPlugin } from '@capacitor/core';

import type { NativeActionSheetPlugin } from './definitions';

const NativeActionSheet = registerPlugin<NativeActionSheetPlugin>('NativeActionSheet', {
  web: () => import('./web').then((m) => new m.NativeActionSheetWeb()),
});

export * from './definitions';
export { NativeActionSheet };
