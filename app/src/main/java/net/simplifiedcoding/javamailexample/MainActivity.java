package net.simplifiedcoding.javamailexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Random;


import okhttp3.ResponseBody;
import retrofit2.Call;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring EditText
    private EditText editTextEmail;
//    private EditText editTextSubject;
//    private EditText editTextMessage;

    //Send button
    private Button buttonSend;

    Random r = new Random();
    int randNum = r.nextInt(50000 - 20000) + 20000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
//        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
//        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        buttonSend = (Button) findViewById(R.id.buttonSend);

        //Adding click listener
        buttonSend.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email


        String email = editTextEmail.getText().toString().trim();
//        String subject = editTextSubject.getText().toString().trim();
//        String message = editTextMessage.getText().toString().trim();

        String subject="Edu-Kit: Confirmation Link";
        String message="http://localhost/register/reg.php?username=ajeet&rand="+randNum;

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        insertRandomNumber();
        sendEmail();
    }


    private void insertRandomNumber() {
        RandomNumAPI service = ServiceGenerator.createService(RandomNumAPI.class);
        Call<ResponseBody> signU =  service.insertRandomNumber(
                "ajeet",
                randNum

        );


        signU.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                //On success we will read the server's output using bufferedreader
                //Creating a bufferedreader object
                BufferedReader reader = null;

                //An string to store output from the server
                String output = "";

                try {
                    //Initializing buffered reader
                    reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    //Reading the output in the string
                    output = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Displaying the output as a toast
//                        Toast.makeText(SignUpActivity.this, output, Toast.LENGTH_LONG).show();
                Log.d("Check", output);

                if (output.equals("random number inserted")) {

                    Toast.makeText(getApplicationContext(),output,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),output,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //If any error occured displaying the error as toast
                Toast.makeText(getApplicationContext(), t.toString(), LENGTH_LONG).show();
            }
        });
    }
}
