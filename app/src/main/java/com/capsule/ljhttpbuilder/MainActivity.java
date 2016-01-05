package com.capsule.ljhttpbuilder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.capsule.baseframe.http.callback.DataCallback;
import com.capsule.baseframe.http.request.JsonParser;
import com.capsule.baseframe.http.request.JsonRequestBuilder;

public class MainActivity extends AppCompatActivity implements DataCallback<WorksInfoListModel> {

    private WorksParams worksParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        new JsonRequestBuilder<WorksInfoListModel>().setUrl("http://182.92.107.35/api/getWorksList")
                .setMethod(Request.Method.POST)
                .setParams(worksParams)
                .setParser(new JsonParser<WorksInfoListModel>(WorksInfoListModel.class))
                .setDataCallback(this).build().submit();
    }

    @Override
    public void onResponse(WorksInfoListModel worksInfoListModel) {
        Log.i("demo",worksInfoListModel.getData().getWorksList().get(0).getArtist_name());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("demo",error.toString());
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
