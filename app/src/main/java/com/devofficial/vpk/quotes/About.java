package com.devofficial.vpk.quotes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void goFb(View view)
    {
        goToUrl("http://facebook.com/Vishnuemerges");
    }
    public void  goTwitter(View view)
    {
        goToUrl("http://twitter.com/vpk11");
    }

    public void goGplus(View view)
    {
        Toast.makeText(getApplicationContext(),"G+ Comming Soon",Toast.LENGTH_SHORT).show();

    }

    private void goToUrl(String url)
    {
        Uri uriUrl=Uri.parse(url);
        Intent launchBrowser=new Intent(Intent.ACTION_VIEW,uriUrl);
        startActivity(launchBrowser);
    }
}
