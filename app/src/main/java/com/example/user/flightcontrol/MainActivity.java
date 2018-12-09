package com.example.user.flightcontrol;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    // UI Element
    Button btnUP;
    Button btnDOWN;
    EditText txtAddress;
    SeekBar Speed;
    SeekBar Channel1;
    SeekBar Channel2;
    Socket myAppSocket = null;
    public static String wifiModuleIp = "";
    public static int wifiModulePort = 0;
    public static String wifiModuleIp2 = "";
    public static int wifiModulePort2 = 0;
    public static String wifiModuleIp3 = "";
    public static int wifiModulePort3 = 0;
    public static String CMD = "0";
    public static int setpoint = 0;
    public static int setpoint2 = 0;
    public static int setpoint3 = 0;
    public static int answer = 0;
    public static int call = 0;
    public static int answer2 = 0;
    public static int call2 = 0;
    public static int answer3= 0;
    public static int call3 = 0;
    public static int answer0 = 0;
    public static int call0 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUP = (Button) findViewById(R.id.btnUp);
        btnDOWN = (Button) findViewById(R.id.btnDown);
        Speed = (SeekBar) findViewById(R.id.Speed);
        Channel1 = (SeekBar) findViewById(R.id.Channel1);
        Channel2 = (SeekBar) findViewById(R.id.Channel2);

        txtAddress = (EditText) findViewById(R.id.ipAddress);


        btnUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if  (call0 == answer0) {
                    getIPandPoert();
                    CMD = "Up";
                    call0 = call0 + 1;
                    if (call0 > 100) {
                        call0 = 0;
                    }
                    Soket_AsyncTask cmd_increase_servo0 = new Soket_AsyncTask();
                    cmd_increase_servo0.execute();
                }
        }
        });
        btnDOWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if  (call0 == answer0) {
                    getIPandPoert();
                    CMD = "Down";
                    call0 = call0 + 1;
                    if (call0 > 100) {
                        call0 = 0;
                    }
                    Soket_AsyncTask cmd_increase_servo0 = new Soket_AsyncTask();
                    cmd_increase_servo0.execute();
                }

            }
        });




        Speed.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener() {
            boolean readyForQuery;
            int progressChangedValue = 0;
            long sendInterval = 50;
            long nextsend = 0;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               if  (call3 == answer3) {//(nextsend < uptimeMillis())  {
                   progressChangedValue = progress;
                   setpoint3 = progress;
                    readyForQuery = false;

                   call3 = call3 +1;
                   if (call3 > 100) {
                       call3 = 0;
                   }

                   String setpointmsg = String.valueOf(setpoint3);
                        // Log.d("MY TEST","setpoint:" +setpointmsg);
                       //getIPandPoert();
                  Soket_AsyncTaskBar3 setpoint_change_servo3 = new Soket_AsyncTaskBar3();
                 setpoint_change_servo3.execute();

                   readyForQuery = true;
                       //nextsend = uptimeMillis() + sendInterval ;

                    ///

                    ///
               }


            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                getIPandPoert();
                readyForQuery = true;

                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                call3 = call3 +1;
                if (call3 > 100) {
                    call3 = 0;
                }

                Soket_AsyncTaskBar3 setpoint_change_servo3 = new Soket_AsyncTaskBar3();
                setpoint_change_servo3.execute();
              //  Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                //        Toast.LENGTH_SHORT).show();


            }
        });


        Channel1.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener() {
            boolean readyForQuery;
            int progressChangedValue = 0;



            @Override
            public void onProgressChanged(SeekBar seekBar, int progress2, boolean fromUser) {

                if  (call == answer) {//(nextsend < uptimeMillis())  {
                    progressChangedValue = progress2;
                    setpoint = progress2;
                    readyForQuery = false;

                    call = call +1;
                    if (call > 100) {
                        call = 0;
                    }

                    String setpointmsg = String.valueOf(setpoint);
                    // Log.d("MY TEST","setpoint:" +setpointmsg);
                    //getIPandPoert();
                    Soket_AsyncTaskBar setpoint_change_servo = new Soket_AsyncTaskBar();
                    setpoint_change_servo.execute();

                    readyForQuery = true;
                    //nextsend = uptimeMillis() + sendInterval ;

                    ///

                    ///
                }


            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                getIPandPoert();
                readyForQuery = true;

                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                call = call +1;
                if (call > 100) {
                    call = 0;
                }

                Soket_AsyncTaskBar setpoint_change_servo = new Soket_AsyncTaskBar();
                setpoint_change_servo.execute();
                //  Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                //        Toast.LENGTH_SHORT).show();


            }
        });

        Channel2.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener() {
            boolean readyForQuery;
            int progressChangedValue = 0;



            @Override
            public void onProgressChanged(SeekBar seekBar, int progress3, boolean fromUser) {

                if  (call2 == answer2) {//(nextsend < uptimeMillis())  {
                    progressChangedValue = progress3;
                    setpoint2 = progress3;
                    readyForQuery = false;

                    call2 = call2 +1;
                    if (call2 > 100) {
                        call2 = 0;
                    }

                    String setpointmsg = String.valueOf(setpoint2);
                    // Log.d("MY TEST","setpoint:" +setpointmsg);
                    //getIPandPoert();
                    Soket_AsyncTaskBar2 setpoint_change_servo2 = new Soket_AsyncTaskBar2();
                    setpoint_change_servo2.execute();

                    readyForQuery = true;
                    //nextsend = uptimeMillis() + sendInterval ;

                    ///

                    ///
                }


            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                getIPandPoert();
                readyForQuery = true;

                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                call2 = call2 +1;
                if (call2 > 100) {
                    call2 = 0;
                }

                Soket_AsyncTaskBar2 setpoint_change_servo2 = new Soket_AsyncTaskBar2();
                setpoint_change_servo2.execute();
                //  Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                //        Toast.LENGTH_SHORT).show();


            }
        });


    }
    public void getIPandPoert() {
        String iPandPort = txtAddress.getText().toString();
       // Log.d("MYTEST", "IP String: " + iPandPort);
        String temp[] = iPandPort.split(",");
        wifiModuleIp = temp[0];
        wifiModulePort = Integer.valueOf(temp[1]);
        //Log.d("MY TEST","IP:" +wifiModuleIp);
        //Log.d("MY TEST","PORT:" +wifiModulePort);

        //String iPandPort2 = txtAddress2.getText().toString();
        // Log.d("MYTEST", "IP String: " + iPandPort);
        //String temp2[] = iPandPort2.split(",");
        wifiModuleIp2 = temp[0];
        wifiModulePort2 = wifiModulePort +1;

        //String iPandPort3 = txtAddress3.getText().toString();
        // Log.d("MYTEST", "IP String: " + iPandPort);
        //String temp3[] = iPandPort3.split(",");
        wifiModuleIp3 = temp[0];
        wifiModulePort3 = wifiModulePort +2;

    }
    public class Soket_AsyncTask extends AsyncTask<Void,Void,Void>
    {
        Socket socket;

        @Override
        protected Void doInBackground(Void... params){

            try {
                InetAddress inetAddress = InetAddress.getByName(MainActivity.wifiModuleIp);
                socket = new java.net.Socket(inetAddress, MainActivity.wifiModulePort);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes(CMD);


                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                final float msgin = dataInputStream.readFloat();
                Log.d("MY TEST","msgin:" +msgin);
                runOnUiThread(new Runnable() {
                    public void run() {

//                        String ssage = new String(msgin);
                        Toast.makeText(MainActivity.this, "Server message is :" + msgin,
                                Toast.LENGTH_SHORT).show();
                    }
                });

                answer0 = answer0 + 1;
                if (answer0 > 100) {
                    answer0 = 0;
                }

                dataInputStream.close();

                dataOutputStream.close();

                socket.close();
            }catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
            return null;
        }


    }

    public class Soket_AsyncTaskBar extends AsyncTask<Void,Void,Void> {


        Socket socket2;
        Timer timer = new Timer();
        int seconds = 0;

        @Override
        protected Void doInBackground(Void... params) {



            try {
                InetAddress inetAddress = InetAddress.getByName(MainActivity.wifiModuleIp);
                socket2 = new java.net.Socket(inetAddress, MainActivity.wifiModulePort);
                DataOutputStream dataOutputStream = new DataOutputStream(socket2.getOutputStream());
                dataOutputStream.writeInt(setpoint);
                //Log.d("MY TEST","setpoint:" +setpoint);


                DataInputStream dataInputStream = new DataInputStream(socket2.getInputStream());
                final float msg2 = dataInputStream.readFloat();
                Log.d("MY TEST", "msg2:" + msg2);

                answer = answer + 1;
                if (answer > 100) {
                    answer = 0;
                }
                //answer = dataInputStream.readInt();
                Log.d("MY TEST", "answer:" + answer);


                Log.d("MY TEST", "call:" + call);


                dataInputStream.close();

                dataOutputStream.close();

                socket2.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
        public class Soket_AsyncTaskBar2 extends AsyncTask<Void,Void,Void> {

            Socket socket3;

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    InetAddress inetAddress = InetAddress.getByName(MainActivity.wifiModuleIp2);
                    socket3 = new java.net.Socket(inetAddress, MainActivity.wifiModulePort2);
                    DataOutputStream dataOutputStream = new DataOutputStream(socket3.getOutputStream());
                    dataOutputStream.writeInt(setpoint2);
                    //Log.d("MY TEST","setpoint:" +setpoint);


                    DataInputStream dataInputStream = new DataInputStream(socket3.getInputStream());
                    final float msg3 = dataInputStream.readFloat();
                    Log.d("MY TEST", "msg3:" + msg3);

                    answer2 = answer2 + 1;
                    if (answer2 > 100) {
                        answer2 = 0;
                    }
                    //answer = dataInputStream.readInt();
                    Log.d("MY TEST", "answer2:" + answer2);


                    Log.d("MY TEST", "call2:" + call2);


                    dataInputStream.close();

                    dataOutputStream.close();

                    socket3.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
            public class Soket_AsyncTaskBar3 extends AsyncTask<Void,Void,Void>
            {

                Socket socket4;

                @Override
                protected Void doInBackground(Void... params){

                    try {
                        InetAddress inetAddress = InetAddress.getByName(MainActivity.wifiModuleIp3);
                        socket4 = new java.net.Socket(inetAddress, MainActivity.wifiModulePort3);
                        DataOutputStream dataOutputStream = new DataOutputStream(socket4.getOutputStream());
                        dataOutputStream.writeInt(setpoint3);
                        //Log.d("MY TEST","setpoint:" +setpoint);


                        DataInputStream dataInputStream = new DataInputStream(socket4.getInputStream());
                        final float msg4 = dataInputStream.readFloat();
                        Log.d("MY TEST","msg2:" +msg4);

                        answer3 = answer3 +1;
                        if (answer3 > 100) {
                            answer3 = 0;
                        }
                        //answer = dataInputStream.readInt();
                        Log.d("MY TEST","answer3:" +answer3);


                        Log.d("MY TEST","call3:" +call3);


                        dataInputStream.close();

                        dataOutputStream.close();

                        socket4.close();
                    }catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
                    return null;
                }

            }

}
