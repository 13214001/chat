package verkstad.org.in.chat;

import android.content.Context;

import java.util.Date;

/**
 * Created by coder on 10/25/2015.
 */
public class Conversation  {
    String msg;
    String sender;
    Date date;
    public Conversation(String msg,Date date,String sender){
        this.msg=msg;
        this.date=date;
        this.sender=sender;

    }
    public Conversation(){

    }
    public Date getDate(){return date;}
}
