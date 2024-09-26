# @capacitor/native-action-sheet

Capacitor plugin for the native action sheet dialog

## Install

```bash
npm install @capacitor/native-action-sheet
npx cap sync
```

## API

<docgen-index>

* [`open(...)`](#open)
* [Interfaces](#interfaces)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### open(...)

```typescript
open(options: { items: NativeActionSheetItem[]; theme: number; cancelable: boolean; cancelableLabel: string; }) => Promise<{ canceled: boolean; selectedItem: number; }>
```

Open new action sheet

| Param         | Type                                                                                                          | Description                 |
| ------------- | ------------------------------------------------------------------------------------------------------------- | --------------------------- |
| **`options`** | <code>{ items: NativeActionSheetItem[]; theme: number; cancelable: boolean; cancelableLabel: string; }</code> | Options for the actionsheet |

**Returns:** <code>Promise&lt;{ canceled: boolean; selectedItem: number; }&gt;</code>

--------------------


### Interfaces


#### NativeActionSheetItem

| Prop        | Type                                                                      |
| ----------- | ------------------------------------------------------------------------- |
| **`label`** | <code>string</code>                                                       |
| **`style`** | <code><a href="#nativeactionsheetstyle">NativeActionSheetStyle</a></code> |


### Enums


#### NativeActionSheetStyle

| Members           | Value                      |
| ----------------- | -------------------------- |
| **`DEFAULT`**     | <code>"DEFAULT"</code>     |
| **`DESTRUCTIVE`** | <code>"DESTRUCTIVE"</code> |

</docgen-api>
