import { WebPlugin } from '@capacitor/core';

import type { NativeActionSheetPlugin } from './definitions';

export class NativeActionSheetWeb extends WebPlugin implements NativeActionSheetPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
