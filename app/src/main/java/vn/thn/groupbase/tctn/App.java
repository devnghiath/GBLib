package vn.thn.groupbase.tctn;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.activeandroid.ActiveAndroid;

/**
 * Created by truonghieunghia on 6/27/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        ActiveAndroid.initialize(this);
    }
}
