import { NativeActionSheet } from '@capacitor&#x2F;native-action-sheet';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    NativeActionSheet.echo({ value: inputValue })
}
