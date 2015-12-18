package verkstad.org.in.chat;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Chat_List extends CustomActivity {
    private static Handler handler;
    private boolean isRunning;
    EditText msg;
    ArrayList<Conversation> convlist;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__list);
        Bundle bundle=getIntent().getExtras();
        String user = bundle.getString("usern");
        getSupportActionBar().setTitle(user);

        handler = new Handler();

    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning=true;
        loadconversation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning=false;
    }
public  void loadconversation(){
    Bundle bundle=getIntent().getExtras();
    String user = bundle.getString("usern");
    Conversation c = new Conversation();
    String[] array = {user,ParseUser.getCurrentUser().getUsername()};
    ParseQuery<ParseObject> query=ParseQuery.getQuery("chat");
    query.whereContainedIn("sender", Arrays.asList(array));
    query.whereContainedIn("receiver", Arrays.asList(array));
    query.orderByAscending("createdAt");
    query.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> list, ParseException e) {
            String[] msg = new String[list.size()];
            String[] sender = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                msg[i] = list.get(i).getString("message").toString();
                sender[i] = list.get(i).getString("sender").toString();

            }
            Context context = getApplicationContext();
            ListView listView = (ListView) findViewById(R.id.listView2);
            // ArrayAdapter adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,msg);
            listView.setAdapter(new CustomAdapter(this, context, msg, sender));
            /**handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (isRunning)
                        loadconversation();
                }
            }, 1000);**/

        }
    });








}
    public void SendMessage(View view){
        Bundle bundle=getIntent().getExtras();
        String user = bundle.getString("usern");
        msg= (EditText) findViewById(R.id.txt);
        String Msg = msg.getText().toString();
        if (Msg.length() == 0)
            return;
        msg.setText(null);
        /**Conversation c = new Conversation(Msg,new Date(),ParseUser.getCurrentUser().getUsername());
        convlist = new ArrayList<Conversation>();
        convlist.add(c);**/
        ParseObject parseObject=new ParseObject("chat");
        parseObject.put("message",Msg);
        parseObject.put("receiver",user);
        parseObject.put("sender", ParseUser.getCurrentUser().getUsername());
        parseObject.saveInBackground();

    }

}
