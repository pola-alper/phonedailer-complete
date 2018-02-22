package com.alper.pola.andoid.phonedailer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button0)
    MyButton zero;
    @BindView(R.id.button1)
    MyButton one;
    @BindView(R.id.button2)
    MyButton two;
    @BindView(R.id.button3)
    MyButton three;
    @BindView(R.id.button4)
    MyButton four;
    @BindView(R.id.button5)
    MyButton five;
    @BindView(R.id.button6)
    MyButton six;
    @BindView(R.id.button7)
    MyButton seven;
    @BindView(R.id.button8)
    MyButton eight;
    @BindView(R.id.button9)
    MyButton nine;
    @BindView(R.id.buttonastrike)
    MyButton astrike;
    @BindView(R.id.buttonhash)
    MyButton hash;
    @BindView(R.id.button10)
    MyButton call;
    @BindView(R.id.button15)
    MyButton delete;
    @BindView(R.id.editText)
    EditText number;
    @BindView(R.id.button11)
    MyButton playback;
    @BindView(R.id.activity_main)
    RelativeLayout touchview;
    private Handler handler = new Handler();
    private MediaPlayer mpdailing;
    private String text;
    private MediaPlayer mediaPlayer;
    private Vibrator vibe;
    private int sounds[] = new int[]{R.raw.zero, R.raw.one, R.raw.two, R.raw.three, R.raw.four, R.raw.five
            , R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine, R.raw.astrik, R.raw.hash, R.raw.dialing, R.raw.deleted, R.raw.nodigittodelete};
    ;
    private Button mLastButton;

    // declaring variables


    private int delay;
    private Boolean move = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        vibe = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        mpdailing = MediaPlayer.create(MainActivity.this, R.raw.dialing);
        mediaPlayer = new MediaPlayer();
        text = number.getText().toString();
        touchview.setOnTouchListener(new View.OnTouchListener() {
            private boolean isInside = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();


                for (int i = 0; i < touchview.getChildCount(); i++) {
                    View current = touchview.getChildAt(i);
                    if (current instanceof Button) {
                        //setting a loop to get all views inside the layout
                        Button b = (Button) current;


                        if (event.getAction() == MotionEvent.ACTION_DOWN) {


                            if (!isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                    b.getBottom())) {


                                move = false; // controlling action move
                                b.getBackground().setAlpha(255);

                            }

                            if (isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                    b.getBottom())) {
                                move = false;
                                b.getBackground().setState(STATE_PRESSED);
                                b.getBackground().setAlpha(100);


                            }

                        }

                        if (event.getAction() == MotionEvent.ACTION_MOVE && !move) {
                            if (!isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                    b.getBottom())) {

                                move = false;
                                b.getBackground().setAlpha(255);

                            }

                            if (isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                    b.getBottom())) {
                                move = false;
                                b.getBackground().setState(STATE_PRESSED);
                                b.getBackground().setAlpha(100);
                                mLastButton = b;
                                if (mLastButton == zero) {
                                    setMediaPlayer(sounds[0]);
                                }
                                if (mLastButton == one) {
                                    setMediaPlayer(sounds[1]);
                                }
                                if (mLastButton == two) {
                                    setMediaPlayer(sounds[2]);
                                }
                                if (mLastButton == three) {
                                    setMediaPlayer(sounds[3]);
                                }
                                if (mLastButton == four) {
                                    setMediaPlayer(sounds[4]);
                                }
                                if (mLastButton == five) {
                                    setMediaPlayer(sounds[5]);
                                }
                                if (mLastButton == six) {
                                    setMediaPlayer(sounds[6]);
                                }
                                if (mLastButton == seven) {
                                    setMediaPlayer(sounds[7]);
                                }
                                if (mLastButton == eight) {
                                    setMediaPlayer(sounds[8]);
                                }
                                if (mLastButton == nine) {
                                    setMediaPlayer(sounds[9]);
                                }
                                if (mLastButton == astrike) {
                                    setMediaPlayer(sounds[10]);

                                }
                                if (mLastButton == hash) {
                                    setMediaPlayer(sounds[11]);

                                }
                                if (mLastButton == call) {

                                    text = number.getText().toString();
                                    if (text.matches("")) {


                                    } else {
                                        mediaPlayer = MediaPlayer.create(MainActivity.this, sounds[12]);
                                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                            @Override
                                            public void onPrepared(MediaPlayer mediaPlayer) {
                                                move = true;
                                                mpdailing.start();
                                                mpdailing.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                    @Override
                                                    public void onCompletion(MediaPlayer mediaPlayer) {

                                                        callback();
                                                    }
                                                });
                                            }
                                        });
                                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mediaPlayer) {
                                                move = false;
                                                mpdailing.release();
                                                callback();
                                            }
                                        });
                                    }
                                }
                                if (mLastButton == delete) {
                                    move = true;
                                    text = number.getText().toString();
                                    // setting up delete function

                                    if (text.matches("")) {
                                        setMediaPlayer(sounds[14]);
                                    } else {
                                        char char2 = text.charAt(text.length() - 1);
                                        if (char2 == '0') {

                                            setMediaPlayer(sounds[0]);

                                        }

                                        if (char2 == '#') {
                                            setMediaPlayer(sounds[11]);
                                        }
                                        if (char2 == '*') {
                                            setMediaPlayer(sounds[10]);
                                        }
                                        handler.postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                setMediaPlayer(sounds[13]);

                                            }
                                        }, 500);


                                    }
                                }
                                if (mLastButton == playback) {
                                    text = number.getText().toString();
                                }
                            }


                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (!isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                    b.getBottom())) {
                                move = false;
                                b.getBackground().setAlpha(255);

                            }

                            if (isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                    b.getBottom())) {
                                b.getBackground().setState(STATE_PRESSED);

                                b.getBackground().setAlpha(100);
                                b.performClick();
                                mLastButton = b;
                                if (mLastButton == zero) {
                                    number.append("0");

                                    vibe.vibrate(100);
                                }
                                if (mLastButton == one) {
                                    number.append("1");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == two) {
                                    number.append("2");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == three) {
                                    number.append("3");
                                    vibe.vibrate(100);

                                }
                                if (mLastButton == four) {
                                    number.append("4");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == five) {
                                    number.append("5");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == six) {
                                    number.append("6");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == seven) {
                                    number.append("7");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == eight) {
                                    number.append("8");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == nine) {
                                    number.append("9");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == astrike) {
                                    number.append("*");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == hash) {
                                    number.append("#");
                                    vibe.vibrate(100);
                                }
                                if (mLastButton == call) {
                                    if (text.matches("")) {

                                    } else {

                                        vibe.vibrate(100);
                                        if (isPermissionGranted()) {
                                            call_action();
                                        }
                                    }
                                }
                                if (mLastButton == delete) {
                                    text = number.getText().toString();
                                    delete();

                                }
                                if (mLastButton == playback) {
                                    vibe.vibrate(100);
                                    callback();
                                }

                            }

                        }


                    }
                }
                return true;
            }

        });


    }

    // setting up volume button function
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {

            delete();

        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            text = number.getText().toString();
            if (text.matches("")) {
                setMediaPlayer(sounds[14]);
            } else {

                callback();
            }
        }
        return true;

    }

    //playback function
    public void callback() {
        delay = 0;
        int index = 0;
        while (index < text.length()) {

            final int finalIndex = index;
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (text.charAt(finalIndex) == '*') {
                        setMediaPlayer(sounds[10]);


                    } else if (text.charAt(finalIndex) == '#') {
                        setMediaPlayer(sounds[11]);


                    } else {
                        setMediaPlayer(sounds[Integer.valueOf(String.copyValueOf(new char[]{text.charAt(finalIndex)}))]);

                    }
//                    Log.d("test callback", String.valueOf(text.charAt(finalIndex)));
//                    //      Log.d("test callback", String.valueOf(sounds[text.charAt(i)]));
//                    Log.d("number", String.valueOf(number));


                }
                // close this activity


            }, delay);

            index++;
            delay += 700;
        }


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
    }

    public void delete() {
        if (text.matches("")) {
            setMediaPlayer(sounds[14]);


        } else {
            char char2 = text.charAt(text.length() - 1);

            if (char2 == '*') {
                setMediaPlayer(sounds[10]);

                deletedelay();

            } else if (char2 == '#') {
                setMediaPlayer(sounds[11]);
                deletedelay();
            } else {

                setMediaPlayer(sounds[Integer.valueOf(String.copyValueOf(new char[]{char2}))]);
                deletedelay();


            }
        }


    }

    public void setMediaPlayer(int audio) {
        mediaPlayer = MediaPlayer.create(MainActivity.this, audio);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mediaPlayer.start();
                move = true;


            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                move = false;
            }

        });
    }

    public void deletedelay() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.deleted);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        move = true;
                        mediaPlayer.start();

                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                        move = false;
                        number.setText(text.substring(0, text.length() - 1));


                    }
                });

            }
        }, 500);
    }

    @SuppressLint("MissingPermission")
    public void call_action() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + text));
        startActivity(callIntent);
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    static boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }

    private final static int[] STATE_PRESSED = {

            android.R.attr.state_focused
    };
}







