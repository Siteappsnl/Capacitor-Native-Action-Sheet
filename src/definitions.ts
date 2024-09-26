export interface NativeActionSheetPlugin {
  open(options: { items: [], theme: number, cancelable: boolean, cancelableLabel: string }): Promise<{ canceled: boolean, selectedItem: number }>;
}
