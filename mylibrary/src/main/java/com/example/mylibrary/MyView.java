package com.example.mylibrary;


import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Nishtha.Udai on 12/30/2015.
 */
public class MyView extends RelativeLayout {
    ImageView dragBtn;
    boolean touchAndSwipe = false;
    float x1;
    float x2;
    Button b;
  //  private Context mContext;

    boolean isCurrentMeeting = true;
    RelativeLayout submenulayout;

    public MyView(final Context context) {
        super(context);
        initialize(context);
        //this.mContext = mContext;

        dragBtn = (ImageView) findViewById(R.id.dragBtn);

        dragBtn.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent me) {
                touchAndSwipe = false;

                switch (me.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                        Display display = wm.getDefaultDisplay();
                       // Display display = getWindowManager().getDefaultDisplay();


                        RelativeLayout rl = (RelativeLayout) findViewById(R.id.parent_relative_layout);
                        int height = rl.getHeight(); //display.getHeight();
                        int width = rl.getWidth(); //display.getHeight();
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(v.getWidth(), v.getHeight());

                        /*if (me.getRawX() < v.getWidth()) {
                            params.leftMargin = 10;
                        }else
                        {
                            params.leftMargin = (int) me.getRawX() - (v.getWidth());
                        }*/

                        if (me.getRawX() < v.getWidth()) {
                            params.leftMargin = 10;
                        } else if (me.getRawX() > (display.getWidth() - (v.getWidth() + 10))) {
                            params.leftMargin = (width - (v.getWidth() + 20));
                        } else {
                            params.leftMargin = (int) me.getRawX() - (v.getWidth()) + 22;
                        }

                        if (me.getRawY() < 200) {
                            params.topMargin = 10;
                        } else if (me.getRawY() > (display.getHeight() - (v.getHeight() + 10))) {
                            params.topMargin = (height - (v.getHeight() + 20));
                        } else {
                            params.topMargin = (int) (me.getRawY() - (v.getHeight() * 2));
                        }

                        v.setLayoutParams(params);

                        break;

                    case MotionEvent.ACTION_DOWN:
                        x1 = me.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = me.getX();
                        float diff = x2 - x1;
                        if (Math.abs(diff) > 15) {
                            // Swipe
                            touchAndSwipe = true;
                        } else {
                            touchAndSwipe = false;
                        }
                        break;
                }
                return touchAndSwipe;
            }
        });
        b =(Button) findViewById(R.id.category1);
        submenulayout=(RelativeLayout) findViewById(R.id.submenu_layout);
        dragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
                final Dialog dialog = new Dialog(context);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.popup_layout);


                Button b1 = (Button) dialog.findViewById(R.id.btn_settings);
                Button b2 = (Button) dialog.findViewById(R.id.btn_logout);
                Button b3 = (Button) dialog.findViewById(R.id.btn_refresh);
                Button b4 = (Button) dialog.findViewById(R.id.btn_archive);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //   Toast.makeText(MyView.this, "Settings", Toast.LENGTH_LONG).show();
                        dialog.hide();
                    }
                });

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  Toast.makeText(MyView.this, "Logout", Toast.LENGTH_LONG).show();
                        dialog.hide();
                    }
                });

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(MyView.this, " Refresh", Toast.LENGTH_LONG).show();
                        dialog.hide();
                    }
                });

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //     Toast.makeText(MyView.this, "Archive", Toast.LENGTH_LONG).show();
                        dialog.hide();
                    }
                });

                dialog.show();


            }
        });


    }


    private void initialize(Context context) {
        inflate(context, R.layout.my_view, this);

    }
    public MyView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        initialize(context);
        //TODO:
    }
}
