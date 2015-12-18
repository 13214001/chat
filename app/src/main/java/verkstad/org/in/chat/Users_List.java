package verkstad.org.in.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.List;

public class Users_List extends AppCompatActivity {
    //String[] array = {"anu","abhishek"};
    public static ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users__list);
        ParseUser.getQuery().whereNotEqualTo("username", user.getCurrentUser().getUsername()).findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {
                    final String[] name = new String[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        name[i] = list.get(i).getString("username").toString();
                    }

                    ListView listView = (ListView) findViewById(R.id.listView);
                    ArrayAdapter ad = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item_text,R.id.ListText, name);
                    listView.setAdapter(ad);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            int pos=position;
                            Intent i=new Intent(getApplicationContext(),Chat_List.class);
                            Bundle bundle = new Bundle();
                            String user=name[position];
                            bundle.putString("usern",user);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    });
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_users__list, menu);
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
