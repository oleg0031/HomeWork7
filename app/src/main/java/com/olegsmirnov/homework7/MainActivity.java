package com.olegsmirnov.homework7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    public static final String LOG_TAG = "my_tag";
    public static final int LOADER_RANDOM_ID = 1;
    private Loader<String> mLoader;
    private TextView mResultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultTxt = (TextView) findViewById(R.id.resultTxt);
        Button mButton = (Button) findViewById(R.id.toAsTaskButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AsyncTaskActivity.class);
                startActivity(intent);
            }
        });
        Bundle mBundle = new Bundle();
        mBundle.putString(RandomLoader.ARG_WORD, "test");
        mLoader = getSupportLoaderManager().initLoader(LOADER_RANDOM_ID, mBundle, this);
    }

    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> mLoader = null;
        if (id == LOADER_RANDOM_ID) {
            mLoader = new RandomLoader(this, args);
            Log.d(LOG_TAG, "onCreateLoader");
        }
        return mLoader;
    }

    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d(LOG_TAG, "onLoadFinished");
        mResultTxt.setText(data);
    }

    public void onLoaderReset(Loader<String> loader) {
        Log.d(LOG_TAG, "onLoaderReset");
        Bundle mBundle = new Bundle();
        mBundle.putString(RandomLoader.ARG_WORD, "test");
        mLoader = getSupportLoaderManager().initLoader(LOADER_RANDOM_ID, mBundle, this);
    }

    public void startLoad(View v) {
        Log.d(LOG_TAG, "startLoad");
        mLoader.onContentChanged();
    }
}