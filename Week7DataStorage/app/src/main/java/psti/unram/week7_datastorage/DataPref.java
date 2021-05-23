package psti.unram.week7_datastorage;

import android.content.Context;
import android.content.SharedPreferences;

public class DataPref {
    private final String PREF_NAME = "data_pref";
    private final String DATA_NIM = "nim";
    private final String DATA_NAMA = "nama";

    private final SharedPreferences preferences;

    DataPref(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void savePref(DataModel data) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(DATA_NIM, data.getNim());
        editor.putString(DATA_NAMA, data.getNama());

        editor.apply();
    }

    public DataModel getPref() {
        DataModel data = new DataModel();
        data.setNim(preferences.getString(DATA_NIM, ""));
        data.setNama(preferences.getString(DATA_NAMA, ""));
        return data;
    }

}
