package be.tahayasin.menubook.Handlers;

import android.content.Context;

import be.tahayasin.menubook.SharedPrefencesManager;

public class ResCodeHandler {
    public static boolean HandleCode(Context context, int code){ // returns true if the code can run normaly //TODO make this methode usefull :D
        switch (code){
            case -1: {
                return true;
            }
            case 31: {
                SharedPrefencesManager.deleteAll(context);
            }
        }

        return false;
    }
    public static boolean NeedsToResetUidAndPin(int code){
        switch (code){
            case 31: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    public static void DeleteUidAndPinIfNeeded(Context context, int code){ // Deletes the uid and pin if the code is set to do that
        switch (code){
            case 19: // Error while making the new uid
            case 20: // Hash needed to create new id
            case 21: // Salt needed to create new id
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30: //Invalid uid
            case 31: //Invalid code
            case 69: {
                SharedPrefencesManager.deleteAll(context);
                break;
            }
        }
    }
}