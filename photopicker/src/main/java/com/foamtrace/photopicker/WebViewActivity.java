package com.foamtrace.photopicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;


/**
 * Created by Administrator on 2017/5/24.
 */

public class WebViewActivity extends AppCompatActivity {


    private WebView webview;
    private ImageView btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webview = (WebView) findViewById(R.id.webview);
        btndelete = (ImageView) findViewById(R.id.btndelete);
        webview.loadUrl("http://www.izhenku.com/weiweb/Iwanttosell/choosetheskills.html");
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
