package com.alper.pola.andoid.phonedailer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import reciver.MyReceiver;

import static com.alper.pola.andoid.phonedailer.R.id.button15;
import static com.alper.pola.andoid.phonedailer.R.id.editText;

public class MainActivity extends AppCompatActivity {
    private final static int[] STATE_PRESSED = {

            android.R.attr.state_focused
    };
    private static int MY_PERMISSIONS_REQUEST_CALL_PHONE;

    Boolean move = false;
    MyButton zero;
    MyButton one;
    MyButton two;
    MyButton three;
    MyButton four;
    MyButton five;
    MyButton six;
    MyButton seven;
    MyButton eight;
    MyButton nine;
    MyButton astrike;
    MyButton hash;
    MyButton call;
    MyButton delete;
    EditText number;
    String text;
    MyButton playback;
    Handler handler = new Handler();
    MediaPlayer mpastrik;
    MediaPlayer mphash;
    MediaPlayer mpastrik1;
    MediaPlayer mphash1;
    MediaPlayer mpempty;
    MediaPlayer mpnodelete;
    MediaPlayer mpdelete;
    MediaPlayer mpdailing;
    MediaPlayer mp9;
    MediaPlayer mp8;
    MediaPlayer mp7;
    MediaPlayer mp6;
    MediaPlayer mp5;
    MediaPlayer mp4;
    MediaPlayer mp3;
    MediaPlayer mp2;
    MediaPlayer mp1;
    MediaPlayer mp0;

    MediaPlayer mediaPlayer;
    private RelativeLayout touchview;
    private Button mLastButton;
// declaring variables
    static boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        MyReceiver mReceiver = new MyReceiver (this);
        registerReceiver(mReceiver, filter);



        final Vibrator vibe = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE) ;
        mp0 = MediaPlayer.create(MainActivity.this, R.raw.zero);
        mp1 = MediaPlayer.create(MainActivity.this, R.raw.one);
        mp2 = MediaPlayer.create(MainActivity.this, R.raw.two);
        mp3 = MediaPlayer.create(MainActivity.this, R.raw.three);
        mp4 = MediaPlayer.create(MainActivity.this, R.raw.four);
        mp5 = MediaPlayer.create(MainActivity.this, R.raw.five);
        mp6 = MediaPlayer.create(MainActivity.this, R.raw.six);
        mp7 = MediaPlayer.create(MainActivity.this, R.raw.seven);
        mp8 = MediaPlayer.create(MainActivity.this, R.raw.eight);
        mp9 = MediaPlayer.create(MainActivity.this, R.raw.nine);

        mpdailing = MediaPlayer.create(MainActivity.this, R.raw.dialing);
        mphash = MediaPlayer.create(MainActivity.this, R.raw.hash);
        mpastrik = MediaPlayer.create(MainActivity.this, R.raw.astrik);
        mphash1 = MediaPlayer.create(MainActivity.this, R.raw.hash);
        mpastrik1 = MediaPlayer.create(MainActivity.this, R.raw.astrik);
        mpdelete = MediaPlayer.create(MainActivity.this, R.raw.deleted);
        mpnodelete = MediaPlayer.create(MainActivity.this, R.raw.nodigittodelete);


mediaPlayer = new MediaPlayer();

        touchview = (RelativeLayout) findViewById(R.id.activity_main);


        zero = (MyButton) findViewById(R.id.button0);
        one = (MyButton) findViewById(R.id.button1);
        delete = (MyButton) findViewById(button15);
        two = (MyButton) findViewById(R.id.button2);
        three = (MyButton) findViewById(R.id.button3);
        four = (MyButton) findViewById(R.id.button4);
        five = (MyButton) findViewById(R.id.button5);
        six = (MyButton) findViewById(R.id.button6);
        seven = (MyButton) findViewById(R.id.button7);
        eight = (MyButton) findViewById(R.id.button8);
        nine = (MyButton) findViewById(R.id.button9);
        astrike = (MyButton) findViewById(R.id.buttonastrike);
        hash = (MyButton) findViewById(R.id.buttonhash);
        call = (MyButton) findViewById(R.id.button10);
        playback = (MyButton) findViewById(R.id.button11);
        number = (EditText) findViewById(editText);
        text = number.getText().toString();


// setontouchlistener to the layout
        touchview.setOnTouchListener(new View.OnTouchListener() {
            private boolean isInside = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
// getting coordinates of the button
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
move =false;
                                b.getBackground().setState(STATE_PRESSED);
                                b.getBackground().setAlpha(100);
                                mLastButton = b;
                                if (mLastButton == zero) {
                                    // getting buttons and setting up the media player
                                    // on preparedlistener to start the media player when its ready and prevent it from crashing
                                    // oncompletionlistener to realse the media player when the audio finish playing

                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
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

                                if (mLastButton == one) {

                                   MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
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
                                if (mLastButton == two) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
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
                                if (mLastButton == three) {
                                    MediaPlayer mediaPlayer ;

                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
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
                                if (mLastButton == four) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
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
                                        }
                                    });
                                }
                                if (mLastButton == five) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
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
                                if (mLastButton == six) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
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
                                if (mLastButton == seven) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
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
                                if (mLastButton == eight) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
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
                                if (mLastButton == nine) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
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
                                if (mLastButton == astrike) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.astrik);
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
                                if (mLastButton == hash) {
                                    MediaPlayer mediaPlayer ;
                                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
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
                                if (mLastButton == call) {

                                    text = number.getText().toString();
                                    if (text.matches("")) {


                                    } else {
                                        MediaPlayer mediaPlayer;
                                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.dialing);
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
                                        MediaPlayer mediaPlayer;
                                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nodigittodelete);
                                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                            @Override
                                            public void onPrepared(MediaPlayer mediaPlayer) {
                                                mpnodelete.start();

                                            }
                                        });
                                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mediaPlayer) {
                                                mpnodelete.release();

                                            }
                                        });
                                    } else {
                                        char char2 = text.charAt(text.length() - 1);
                                        if (char2 == '0') {

                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                   mediaPlayer.start();

                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mediaPlayer.release();



                                                }
                                            });

                                        }
                                        if (char2 == '1') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                    mp1.start();

                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp1.release();

                                                }
                                            });
                                        }
                                        if (char2 == '2') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                    mp2.start();
                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp2.release();
                                                }
                                            });
                                        }
                                        if (char2 == '3') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                    mp3.start();
                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp3.release();
                                                }
                                            });
                                        }
                                        if (char2 == '4') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                    mp4.start();
                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp4.release();
                                                }
                                            });
                                        }
                                        if (char2 == '5') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                   mp5.start();

                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp5.release();

                                                }
                                            });
                                        }
                                        if (char2 == '6') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                    mp6.start();

                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp6.release();

                                                }
                                            });
                                        }
                                        if (char2 == '7') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                    mp7.start();

                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp7.release();

                                                }
                                            });
                                        }
                                        if (char2 == '8') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                    mp8.start();
                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp8.release();
                                                }
                                            });
                                        }
                                        if (char2 == '9') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                   mp9.start();

                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mp9.release();

                                                }
                                            });
                                        }
                                        if (char2 == '#') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                   mphash.start();
                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mphash.release();
                                                }
                                            });
                                        }
                                        if (char2 == '*') {
                                            MediaPlayer mediaPlayer;
                                            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.astrik);
                                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                @Override
                                                public void onPrepared(MediaPlayer mediaPlayer) {
                                                    mpastrik.start();

                                                }
                                            });
                                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mediaPlayer) {
                                                    mpastrik.release();
                                                }
                                            });
                                        }
                                        handler.postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                MediaPlayer mediaPlayer;
                                                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.deleted);
                                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                    @Override
                                                    public void onPrepared(MediaPlayer mediaPlayer) {

                                                        mediaPlayer.start();

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
                                        }, 500);


                                    }
                                }
                                if (mLastButton == playback) {
// setting up playback function
                                    text = number.getText().toString();
                                    if (text.matches("")) {

                                    } else {
                                        move = true;
                                        //calling playback method
                                        callback();
                                    }
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
                                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                                        callIntent.setData(Uri.parse("tel:" + text));


                                        if (ContextCompat.checkSelfPermission(MainActivity.this,
                                                Manifest.permission.CALL_PHONE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                                    Manifest.permission.CALL_PHONE)) {
                                            } else {
                                                ActivityCompat.requestPermissions(MainActivity.this,
                                                        new String[]{Manifest.permission.CALL_PHONE},
                                                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
                                            }

                                        }

                                        startActivity(callIntent);


                                    }
                                }
                                if (mLastButton == delete) {
                                    text = number.getText().toString();

                                    if (text.matches("")) {


                                    } else {
                                        char char1 = text.charAt(text.length() - 1);
                                        char charhash = '#';
                                        char charastrik = '*';
                                        if (char1 == charhash) {
                                            vibe.vibrate(100);
                                            number.setText(text.substring(0, text.length() - 1));
                                        }
                                        if (char1 == charastrik) {
                                            vibe.vibrate(100);
                                            number.setText(text.substring(0, text.length() - 1));
                                        } else {

                                            vibe.vibrate(100);
                                            number.setText(text.substring(0, text.length() - 1));

                                        }


                                    }
                                }
                                if (mLastButton == playback) {
                                    vibe.vibrate(100);
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
            move = true;
            text = number.getText().toString();
            // setting up delete function

            if (text.matches("")) {
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nodigittodelete);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mpnodelete.start();

                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mpnodelete.release();

                    }
                });
            } else {
                char char2 = text.charAt(text.length() - 1);
                if (char2 == '0') {

                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.release();



                        }
                    });

                }
                if (char2 == '1') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp1.start();

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp1.release();

                        }
                    });
                }
                if (char2 == '2') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp2.start();
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp2.release();
                        }
                    });
                }
                if (char2 == '3') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp3.start();
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp3.release();
                        }
                    });
                }
                if (char2 == '4') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp4.start();
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp4.release();
                        }
                    });
                }
                if (char2 == '5') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp5.start();

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp5.release();

                        }
                    });
                }
                if (char2 == '6') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp6.start();

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp6.release();

                        }
                    });
                }
                if (char2 == '7') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp7.start();

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp7.release();

                        }
                    });
                }
                if (char2 == '8') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp8.start();
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp8.release();
                        }
                    });
                }
                if (char2 == '9') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp9.start();

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mp9.release();

                        }
                    });
                }
                if (char2 == '#') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mphash.start();
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mphash.release();
                        }
                    });
                }
                if (char2 == '*') {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.astrik);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mpastrik.start();

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mpastrik.release();
                        }
                    });
                }
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.deleted);
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
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            text = number.getText().toString();
            if (text.matches("")) {
               mpnodelete.start();
            } else {

                callback();
            }
        }
        return true;

    }

//playback function
    public void callback() {


        char char2 = text.charAt(0);
        if (char2 == '0') {

            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });

        }
        if (char2 == '1') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '2') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '3') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '4') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '5') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '6') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '7') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '8') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '9') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '#') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        if (char2 == '*') {
            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }



        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if (text.length() >= 2) {


                    char char2 = text.charAt(1);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }

                } else {
                    handler.removeCallbacks(this);
                }
                // close this activity


            }
        }, 700);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                if (text.length() >= 3) {
                    char char2 = text.charAt(2);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }


            }
        }, 1200);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                if (text.length() >= 4) {
                    char char2 = text.charAt(3);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }


            }
        },1700);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                if (text.length() >=5) {
                    char char2 = text.charAt(4);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
        } else {
            handler.removeCallbacks(this);
        }


    }

        }, 2200);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                if (text.length() >= 6) {
                    char char2 = text.charAt(5);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }


            }
        }, 2700);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                if (text.length() >= 7) {
                    char char2 = text.charAt(6);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }


            }
        }, 3200);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 8) {
                    char char2 = text.charAt(7);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 3700);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 9) {
                    char char2 = text.charAt(8);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 4200);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 10) {
                    char char2 = text.charAt(9);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 4700);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 11) {
                    char char2 = text.charAt(10);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        },5200);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 12) {
                    char char2 = text.charAt(11);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 5700);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 13) {
                    char char2 = text.charAt(12);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 6200);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 14) {
                    char char2 = text.charAt(13);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 6700);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 15) {
                    char char2 = text.charAt(14);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 7200);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (text.length() >= 16) {
                    char char2 = text.charAt(15);
                    if (char2 == '0') {

                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.zero);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });

                    }
                    if (char2 == '1') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.one);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '2') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.two);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '3') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.three);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '4') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.four);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '5') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.five);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '6') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.six);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '7') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.seven);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '8') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.eight);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '9') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.nine);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '#') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hash);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                    if (char2 == '*') {
                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.astrik);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.release();
                            }
                        });
                    }
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 7700);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
    }


}






