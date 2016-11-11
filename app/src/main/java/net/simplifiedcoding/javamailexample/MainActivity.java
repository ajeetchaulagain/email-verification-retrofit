package net.simplifiedcoding.javamailexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;
import java.util.Random;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring EditText
    private EditText editTextEmail;
//    private EditText editTextSubject;
//    private EditText editTextMessage;

    //Send button
    private Button buttonSend;

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

        Random r = new Random();
        int randNum = r.nextInt(5000 - 2000) + 2000;


        String email = editTextEmail.getText().toString().trim();
//        String subject = editTextSubject.getText().toString().trim();
//        String message = editTextMessage.getText().toString().trim();

        String subject="Edu-Kit: Confirmation Link";
        String message="http://localhost/register/register.php?username=ajeet&rand="+randNum;

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
}
