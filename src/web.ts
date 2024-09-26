import { WebPlugin } from '@capacitor/core';

import type { NativeActionSheetPlugin } from './definitions';

export class NativeActionSheetWeb extends WebPlugin implements NativeActionSheetPlugin {
  async open(_: { items: [], theme: number, cancelable: boolean, cancelableLabel: string }): Promise<{ canceled: boolean, selectedItem: number }>{
    console.error('NativeActionSheet Plugin does not work in browser.')
    return { canceled: true, selectedItem: -1};
  }
}
