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
open(options: NativeActionSheetOptions) => Promise<NativeActionSheetResponse>
```

Open new action sheet

| Param         | Type                                                                          | Description                 |
| ------------- | ----------------------------------------------------------------------------- | --------------------------- |
| **`options`** | <code><a href="#nativeactionsheetoptions">NativeActionSheetOptions</a></code> | Options for the actionsheet |

**Returns:** <code>Promise&lt;<a href="#nativeactionsheetresponse">NativeActionSheetResponse</a>&gt;</code>

--------------------


### Interfaces


#### NativeActionSheetResponse

| Prop               | Type                 | Description                              |
| ------------------ | -------------------- | ---------------------------------------- |
| **`cancelled`**    | <code>boolean</code> | Was the cancel button pressed            |
| **`selectedItem`** | <code>number</code>  | Selected item index (starting from null) |


#### NativeActionSheetOptions

| Prop                  | Type                                 | Description                    |
| --------------------- | ------------------------------------ | ------------------------------ |
| **`items`**           | <code>NativeActionSheetItem[]</code> | Items for the action sheet     |
| **`theme`**           | <code>number</code>                  | Theme number (android only)    |
| **`cancelable`**      | <code>boolean</code>                 | Is the action sheet cancelable |
| **`cancelableLabel`** | <code>string</code>                  | Label for cancel button        |


#### NativeActionSheetItem

| Prop        | Type                                                                      | Description                   |
| ----------- | ------------------------------------------------------------------------- | ----------------------------- |
| **`label`** | <code>string</code>                                                       | Label for item                |
| **`style`** | <code><a href="#nativeactionsheetstyle">NativeActionSheetStyle</a></code> | Style for the item (iOS only) |


### Enums


#### NativeActionSheetStyle

| Members           | Value                      |
| ----------------- | -------------------------- |
| **`DEFAULT`**     | <code>"DEFAULT"</code>     |
| **`DESTRUCTIVE`** | <code>"DESTRUCTIVE"</code> |

</docgen-api>
