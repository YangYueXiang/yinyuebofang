package com.bwie.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.bwie.rikao1017.R;

import java.io.IOException;

/**
 * Created by YangYueXiang
 * on 2018/10/17
 */
public class VoidService extends Service {

    private MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        try {
            mp = MediaPlayer.create(this, R.raw.zhiweimeihao);
            mp.prepare();
        } catch (IllegalStateException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        if (mp != null) {
            mp.start();
            //注册回调函数，音乐播放完毕之后，音乐播放完毕的事件处理
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    try {
                        mp.start();
                    } catch (IllegalStateException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }

                }
            });

            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                public boolean onError(MediaPlayer mp, int what, int extra) {
                    try {
                        mp.release();
                    } catch (Exception e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }
                    return false;
                }
            });
            super.onStart(intent, startId);
        }
    }

    @Override
    public void onDestroy() {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        super.onDestroy();
    }
}
