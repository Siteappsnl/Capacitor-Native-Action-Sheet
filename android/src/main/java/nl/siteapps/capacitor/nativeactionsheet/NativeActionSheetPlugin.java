package nl.siteapps.capacitor.nativeactionsheet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "NativeActionSheet")
public class NativeActionSheetPlugin extends Plugin {

    /**
     * Open Native action sheet
     * @param Call Capacitor Call that was made
     */
    @PluginMethod
    public void open(PluginCall Call) {
        try {
            // Extract the items out of the call
            JSArray Items = Call.getArray("items", new JSArray());
            // Check if there are any items
            if (Items.length() == 0) {
                Call.reject("No items where provided");
                return;
            }

            // Create the correct builder
            AlertDialog.Builder Builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                Builder = new AlertDialog.Builder(this.getActivity(), Call.getInt("theme", 1));
            } else {
                Builder = new AlertDialog.Builder(this.getActivity());
            }
            // Set title
            Builder.setTitle(Call.getString("title", ""));
            // Make cancelable
            Builder.setCancelable(Call.getBoolean("cancelable", true));
            // Loop trough the items
            String[] Buttons = new String[Items.length()];
            for (int I = 0; I < Items.length(); I++) {
                Buttons[I] = Items.getJSONObject(I).getString("label");
            }
            // Add the items
            Builder.setItems(Buttons, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Call.resolve(new JSObject().put("cancelled", false).put("selectedItem", which));
                }
            });
            // Create cancel listener
            Builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Call.resolve(new JSObject().put("cancelled", true));
                }
            });

            // Add the cancel button
            if (Call.getBoolean("cancelable", true)) {
                Builder.setNegativeButton(Call.getString("cancelableLabel", "Cancel"),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
            }

            // Show the action list
            Builder.create().show();
        }
        catch(Exception Error){
            Call.reject(Error.getMessage());
        }
    }
}
