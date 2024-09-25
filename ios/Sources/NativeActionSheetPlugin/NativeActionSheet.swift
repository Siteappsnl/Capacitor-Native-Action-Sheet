import Foundation

@objc public class NativeActionSheet: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
