package com.example.shardulnegi12.linearlayout;

        import android.media.MediaPlayer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    MediaPlayer mp1;
    MediaPlayer mp2;
    MediaPlayer mp3;
    MediaPlayer mp4;
    MediaPlayer mp5;
    MediaPlayer mp6;
    MediaPlayer mp7;
    MediaPlayer mp8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = (Button) this.findViewById(R.id.button1);
        mp = MediaPlayer.create(MainActivity.this, R.raw.aud);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.stop();
                    try {
                        mp.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp.seekTo(0);
                } else {
                    mp.start();

                }
            }
        });


        one = (Button) this.findViewById(R.id.button2);
        mp1 = MediaPlayer.create(this, R.raw.aud1);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp1.isPlaying()) {
                    mp1.stop();
                    try {
                        mp1.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp1.seekTo(0);
                } else {
                    mp1.start();

                }
            }
        });

        one = (Button) this.findViewById(R.id.button3);
        mp2 = MediaPlayer.create(this, R.raw.aud2);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp2.isPlaying()) {
                    mp2.stop();
                    try {
                        mp2.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp2.seekTo(0);
                } else {
                    mp2.start();

                }
            }
        });
        one = (Button) this.findViewById(R.id.button4);
        mp3 = MediaPlayer.create(this, R.raw.aud3);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp3.isPlaying()) {
                    mp3.stop();
                    try {
                        mp3.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp3.seekTo(0);
                } else {
                    mp3.start();

                }
            }
        });

        one = (Button) this.findViewById(R.id.button5);
        mp4 = MediaPlayer.create(this, R.raw.aud4);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp4.isPlaying()) {
                    mp4.stop();
                    try {
                        mp4.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp4.seekTo(0);
                } else {
                    mp4.start();

                }
            }
        });

        one = (Button) this.findViewById(R.id.button6);
        mp5 = MediaPlayer.create(this, R.raw.aud5);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp5.isPlaying()) {
                    mp5.stop();
                    try {
                        mp5.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp5.seekTo(0);
                } else {
                    mp5.start();

                }
            }
        });
        one = (Button) this.findViewById(R.id.button7);
        mp6 = MediaPlayer.create(this, R.raw.aud6);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp6.isPlaying()) {
                    mp6.stop();
                    try {
                        mp6.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp6.seekTo(0);
                } else {
                    mp6.start();

                }
            }
        });
        one = (Button) this.findViewById(R.id.button8);
        mp7 = MediaPlayer.create(this, R.raw.aud7);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp7.isPlaying()) {
                    mp7.stop();
                    try {
                        mp7.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp7.seekTo(0);
                } else {
                    mp7.start();

                }
            }
        });
        one = (Button) this.findViewById(R.id.button9);
        mp8 = MediaPlayer.create(this, R.raw.aud8);
        one.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (mp8.isPlaying()) {
                    mp8.stop();
                    try {
                        mp8.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mp8.seekTo(0);
                } else {
                    mp8.start();

                }
            }
        });

    }
}


