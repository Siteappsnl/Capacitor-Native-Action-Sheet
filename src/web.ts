import { WebPlugin } from '@capacitor/core';

import type { NativeActionSheetPlugin, NativeActionSheetOptions, NativeActionSheetResponse } from './definitions';

export class NativeActionSheetWeb extends WebPlugin implements NativeActionSheetPlugin {
  async open(_: NativeActionSheetOptions): Promise<NativeActionSheetResponse>{
    console.error('NativeActionSheet Plugin does not work in browser.')
    return { cancelled : false, selectedItem: -1}
  }
}
