package verkstad.org.in.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by coder on 10/25/2015.
 */
public class CustomAdapter extends BaseAdapter {
    Context context;
    String message[];
    String sender[];
    LayoutInflater inflater=null;
    TextView tv;
    public CustomAdapter(FindCallback<ParseObject> chat_list,Context context, String[] msg, String[] senderc){
      message=msg;
       sender=senderc;
        this.context=context;

        inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return message.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowview;

        if (sender[position].equals(ParseUser.getCurrentUser().getUsername())){
             rowview=inflater.inflate(R.layout.chat_item_sent,null);
        }
        else {
            rowview=inflater.inflate(R.layout.chat_item_rcv,null);
        }
        tv=(TextView) rowview.findViewById(R.id.lbl2);
        tv.setText(message[position]);
        return rowview;
    }
}
