export interface NativeActionSheetPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
