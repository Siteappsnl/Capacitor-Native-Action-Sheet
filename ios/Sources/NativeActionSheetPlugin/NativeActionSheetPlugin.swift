import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(NativeActionSheetPlugin)
public class NativeActionSheetPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "NativeActionSheetPlugin"
    public let jsName = "NativeActionSheet"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "open", returnType: CAPPluginReturnPromise)
    ]
    
    /**
     * Open Native action sheet
     * @param call Capacitor Call that was made
     */
    @objc func open(_ call: CAPPluginCall) {
        DispatchQueue.main.async { [weak self] in
            // Extract the items
            let items = call.getArray("items", JSArray())
            // Check if there are any items
            if items.count == 0 {
                call.reject("No items where provided");
                return;
            }
            // Create alert controller
            let alertController = UIAlertController(title: call.getString("title"), message: nil, preferredStyle: UIAlertController.Style.actionSheet)
            // loop though the items
            for i in 0...items.count-1 {
                // fetch the item
                let item: JSObject = items[i] as! JSObject
                let label = (item["label"] ?? "") as! String
                let destructive = (item["style"] ?? "") as! String == "DESTRUCTIVE"
                // Add the action to the controller
                alertController.addAction(UIAlertAction(title: label, style: destructive ? .destructive : .default) { UIAlertAction in
                    call.resolve(["cancelled" : false, "selectedItem": i])
                })
            }
            
            // Add the cancel action
            if call.getBool("cancelable", true){
                alertController.addAction(UIAlertAction(title: call.getString("cancelableLabel", "Cancel"), style: .cancel, handler: { UIAlertAction in
                    call.resolve(["cancelled" : true])
                }))
            }
            
            // Open the alert controller
            self!.bridge!.viewController!.present(alertController, animated: true, completion: nil)
        }
    }
}
