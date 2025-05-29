package nl.siteapps.capacitor.nativeactionsheet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

            // Create lists for labels and states
            final List<String> Labels = new ArrayList<>();
            final List<Boolean> EnabledStates = new ArrayList<>();
            for (int I = 0; I < Items.length(); I++) {
                JSONObject item = Items.getJSONObject(I);
                Labels.add(item.getString("label"));
                EnabledStates.add(!item.optBoolean("disabled", false));
            }

            // Get helper variables for the color
            TypedValue TypedValue = new TypedValue();
            Context Context = getContext(); // or this.getActivity()

            // Get default text color (for enabled)
            Context.getTheme().resolveAttribute(android.R.attr.textColorPrimary, TypedValue, true);
            int DefaultTextColor = ContextCompat.getColor(Context, TypedValue.resourceId);

            // Get disabled text color (use textColorTertiary or similar)
            Context.getTheme().resolveAttribute(android.R.attr.textColorTertiary, TypedValue, true);
            int DisabledTextColor = ContextCompat.getColor(Context, TypedValue.resourceId);

            // Create a custom adapter
            ArrayAdapter<String> ItemAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.select_dialog_item, Labels) {
                @Override
                public boolean isEnabled(int position) {
                    return EnabledStates.get(position);
                }

                @Override
                public View getView(int Position, View ConvertView, ViewGroup Parent) {
                    View view = super.getView(Position, ConvertView, Parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    // Handle colors
                    if (!EnabledStates.get(Position)) {
                        textView.setTextColor(DisabledTextColor);
                    } else {
                        textView.setTextColor(DefaultTextColor);
                    }

                    return view;
                }
            };

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

            Builder.setAdapter(ItemAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (EnabledStates.get(which)) {
                        Call.resolve(new JSObject().put("cancelled", false).put("selectedItem", which));
                    }
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
