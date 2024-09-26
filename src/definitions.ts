export enum NativeActionSheetStyle {
  DEFAULT = "DEFAULT",
  DESTRUCTIVE = "DESTRUCTIVE",
}

export interface NativeActionSheetOptions{
  /** Items for the action sheet */
  items: NativeActionSheetItem[]
  /** Theme number (android only) */
  theme: number, 
  /** Is the action sheet cancelable */
  cancelable: boolean,
  /** Label for cancel button */
  cancelableLabel: string
}

export interface NativeActionSheetResponse{
  /** Was the cancel button pressed */
  cancelled: boolean,
  /** Selected item index (starting from null) */
  selectedItem : number
}

export interface NativeActionSheetItem{
  /** Label for item */
  label : string;
  /** Style for the item (iOS only) */
  style: NativeActionSheetStyle
}

export interface NativeActionSheetPlugin {
  /**
   * Open new action sheet
   * @param options Options for the actionsheet
   */
  open(options: NativeActionSheetOptions): Promise<NativeActionSheetResponse>;
}