package ui.demo.com.quizui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Design-4 on 3/2/2018.
 */

public class SplashScreen  extends AppCompatActivity {
    Animation uptodown,downtoup,alpha;
    LinearLayout layout_one;
    FrameLayout layout_two;
    ImageView imageView7,imageView8,imageView9,imageView10;
    RelativeLayout relative_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_splash);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        layout_one=(LinearLayout) findViewById(R.id.layout_one);
        layout_two=(FrameLayout) findViewById(R.id.layout_two);
        relative_layout=(RelativeLayout) findViewById(R.id.relative_layout);

        imageView7=(ImageView) findViewById(R.id.imageView7);
        imageView8=(ImageView) findViewById(R.id.imageView8);
        imageView9=(ImageView) findViewById(R.id.imageView9);
        imageView10=(ImageView) findViewById(R.id.imageView10);


        uptodown = AnimationUtils.loadAnimation(this,R.anim.updown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        alpha = AnimationUtils.loadAnimation(this,R.anim.alpha);

        imageView7.startAnimation(uptodown);
        imageView8.startAnimation(uptodown);
        imageView9.startAnimation(uptodown);
        imageView10.startAnimation(uptodown);
        //layout_one.startAnimation(uptodown);
        layout_two.startAnimation(downtoup);
        //relative_layout.startAnimation(alpha);

//        Thread mythred=new Thread(){
//            @Override
//            public void run(){
//                try {
//                    sleep(3000);
//                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                    finish();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
//            }
//        };
//        mythred.start();

        new Handler().postDelayed(new Runnable() {
            public void run() {

                     /* Create an intent that will start the main activity. */
                Intent mainIntent = new Intent(SplashScreen.this,
                        CategoryActivity.class);

                //SplashScreen.this.startActivity(mainIntent);
                startActivity(mainIntent);
                     /* Finish splash activity so user cant go back to it. */
                SplashScreen.this.finish();
                overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

                     /* Apply our splash exit (fade out) and main
                        entry (fade in) animation transitions. */
            }
        }, 3000);
    }
}
