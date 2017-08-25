package navneet.com.hackernews;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sree on 24-08-2017.
 */

public class PreferencesHelper {

    private static PreferencesHelper instance;
    private Context context;
    private SharedPreferences preferences;

    public PreferencesHelper(Context c) {
        this.context = c.getApplicationContext();
        preferences = context.getSharedPreferences(Constants.PREFERENCES_FILE, Context.MODE_PRIVATE);

    }

    public static PreferencesHelper getInstance(Context c) {
        if (instance == null) {
            instance = new PreferencesHelper(c);
        }
        return instance;
    }

    public void storeUnencryptedSetting(String key, String value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public String getUnencryptedSetting(String key) {
        return preferences.getString(key, "");
    }

    public Boolean getBooleanSetting(String key) {
        return preferences.getBoolean(key, false);
    }

}
