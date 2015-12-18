package verkstad.org.in.chat;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by coder on 10/24/2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "SnkGFGT9xUPmVH9DTXJuZuw0CNioWydCaK8va9X5", "nhT6IAQ7tvKX9vw1yxHSOu5UMvXahdVSamAxrsHP");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
