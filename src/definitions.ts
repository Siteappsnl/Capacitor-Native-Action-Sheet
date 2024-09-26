export enum NativeActionSheetStyle {
  DEFAULT = "DEFAULT",
  DESTRUCTIVE = "DESTRUCTIVE",
}

export interface NativeActionSheetItem{
  label : string;
  style: NativeActionSheetStyle
}

export interface NativeActionSheetPlugin {
  open(options: { items: NativeActionSheetItem[], theme: number, cancelable: boolean, cancelableLabel: string }): Promise<{ canceled: boolean, selectedItem: number }>;
}