package verkstad.org.in.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    EditText user,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Go to registration activity
    public void register(View view){
        Intent i=new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }
    //Verify User
    public void login(View view){
        user= (EditText) findViewById(R.id.editText);
        password= (EditText) findViewById(R.id.editText2);
        String username=user.getText().toString();
        final String pass=password.getText().toString();
        ParseUser.logInInBackground(username, pass, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    //Toast.makeText(getApplicationContext(),"successfull login",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Users_List.class);
                    startActivity(i);

                } else {
                    Toast.makeText(getApplication(), "Please Register to Sign in", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
