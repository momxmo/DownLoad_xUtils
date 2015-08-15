package com.mo.download_xutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;


/**
 * 本实例:使用xUtils-master框架进行多线程的下载
 */

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    EditText et_url;
    ProgressBar pb_01;

    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_url = (EditText) findViewById(R.id.et_url);
        pb_01 = (ProgressBar) findViewById(R.id.pb_01);


    }

    //下载
    public void download(View view) {
        path = et_url.getText().toString().trim();

        Log.i(TAG,".......。。。。。。。。。");
        Log.i(TAG,"path:"+path);

        HttpUtils utils = new HttpUtils();

/*        utils.download(path, getFileName(path), new RequestCallBack<File>() {
                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        Log.i(TAG,"onSuccess.....................");
                        Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(com.lidroid.xutils.exception.HttpException e, String s) {
                        Log.i(TAG,"onFailure.....................");
                        Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        Log.i(TAG,"正在下载。。。。。。。。。");
                        pb_01.setMax((int) total);
                        pb_01.setProgress((int) current);
                    }
                }
        );*/
        utils.download(path, getFileName(path), new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                pb_01.setMax((int) total);
                pb_01.setProgress((int) current);

            }
        });

    }


    /**
     * 获取文件的名字
     *
     * @param path
     * @return
     */
    public String getFileName(String path) {
        int index = path.lastIndexOf("/") + 1;
        File file  = new File(getFilesDir(),path.substring(index));

        //存到到sd卡中，一般先要判断sd是否挂在，没有挂在，一定要提示用户，不然会保存异常
//        return Environment.getExternalStorageDirectory().getPath() + "/" + path.substring(index);
        Log.i(TAG,file.getAbsolutePath());
        return file.getAbsolutePath();
    }

}
