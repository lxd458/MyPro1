package com.example.recorder;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    TextureView tv;
    Button btn;
    MediaRecorder recorder;
    Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         tv = findViewById(R.id.tv);
         btn = findViewById(R.id.btn);
         btn.setOnClickListener(new View.OnClickListener() {

             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onClick(View view) {
                 CharSequence text = btn.getText();
                 if(TextUtils.equals(text,"开始")){//判断是否
                    btn.setText("结束");
                    camera = Camera.open();
                      camera.setDisplayOrientation(90);
                        camera.unlock();
                     recorder = new MediaRecorder();
                     recorder.setCamera(camera );
                     recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//音频 麦克风
                     recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);//视频：
                     recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//常用输出格式
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
                            recorder.setOrientationHint(90);
                     recorder.setOutputFile(new File(getExternalFilesDir(""),"a.mp4").getAbsolutePath());
                    recorder.setVideoSize(640,480);
                    recorder.setPreviewDisplay(new Surface(tv.getSurfaceTexture()));

                     try {
                         recorder.prepare();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }


                     recorder.start();

                 }else {
                     btn.setText("开始");
                     try { //调用MediaRecorder的start()与stop()间隔不能小于1秒(有时候大于1秒也崩)，否则必崩。
                         //下面三个参数必须加，不加的话会奔溃，在mediarecorder.stop();
                         //报错为：RuntimeException:stop failed
                         recorder.setOnErrorListener(null);
                         recorder.setOnInfoListener(null);
                         recorder.setPreviewDisplay(null);
                         recorder.stop();
                     } catch (IllegalStateException e) {
                         // TODO: handle exception
                         Log.i("Exception", Log.getStackTraceString(e));
                     }catch (RuntimeException e) {
                         // TODO: handle exception
                         Log.i("Exception", Log.getStackTraceString(e));
                     }catch (Exception e) {
                         // TODO: handle exception
                         Log.i("Exception", Log.getStackTraceString(e));
                     }
                     //java.lang.RuntimeException: stop failed.

                     recorder.release();

                     camera.stopPreview();
                     camera.release();
                 }
             }
         });
    }
}
