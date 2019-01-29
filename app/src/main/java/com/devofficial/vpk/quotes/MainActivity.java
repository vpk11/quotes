package com.devofficial.vpk.quotes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String q;
    public static final String MyPref="Val";
    public static final String key="keyVal";
    SharedPreferences sharedPreferences;
    //Button bn;
    //int notificationID=1;


    int i=0, end;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  bn=(Button)findViewById(R.id.button);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });*/

        //Quotes block
        sharedPreferences=getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        final String[] quote = getResources().getStringArray(R.array.quote);
        end = quote.length;
        i=sharedPreferences.getInt(key,0);

        textView = (TextView) findViewById(R.id.txt_view1);
        textView.setVisibility(View.GONE);
        textView.setText(quote[i]);
        textView.setVisibility(View.VISIBLE);
        q = quote[i];
        i++;
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(key,i);
        editor.apply();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ClipboardManager clipboardManager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("Quotes",q);
                clipboardManager.setPrimaryClip(clipData);

                Snackbar.make(view, "Quote Copied", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

    }
    /*public void displayNotification()
    {
        String[] quote = getResources().getStringArray(R.array.quote);
        sharedPreferences=getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        int k=sharedPreferences.getInt(key,0);
        Intent i=new Intent("notify_filter");
        i.putExtra("notificationID",notificationID);
        PendingIntent pi=PendingIntent.getActivity(this,0,i,0);
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification=new Notification(R.mipmap.ic_launcher,quote[k], System.currentTimeMillis());
        nm.notify(0,notification);


    }*/

    public void nextQ(View view) {

        String[] quote = getResources().getStringArray(R.array.quote);
        if (i == quote.length) {
            i = 0;
        }
        textView.setText(quote[i]);
        q = quote[i];
        i++;
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(key,i);
        editor.apply();

    }
    /*public void prevQ(View view) {
        int j=i;
        String[] quote = getResources().getStringArray(R.array.quote);

        if(j==0)
        {
            j=0;
        }
        else
            j--;

        textView.setText(quote[j]);

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /*MenuItem item=menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();*/

        return true;
    }
   /* private void setShareIntent(Intent shareIntent)
    {
        if (mShareActionProvider!=null)
        {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menu_item_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, q);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        }

        return super.onOptionsItemSelected(item);
    }

    private static long back_pressed;
    @Override
    public void onBackPressed()
    {
        if (back_pressed+2000>System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Press again to Exit", Toast.LENGTH_SHORT).show();
        back_pressed=System.currentTimeMillis();
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }


}
