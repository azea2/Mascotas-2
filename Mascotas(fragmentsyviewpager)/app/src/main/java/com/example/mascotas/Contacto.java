package com.example.mascotas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {

    EditText etNombreCompleto,etCorreo,etComentarios;
    Button btEnviarComentarios;
    String sEmail,sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        etNombreCompleto    = findViewById(R.id.etNombreCompleto);
        etCorreo            = findViewById(R.id.etCorreo);
        etComentarios       = findViewById(R.id.etComentarios);
        btEnviarComentarios = findViewById(R.id.btEnviarComentarios);



        //PARA QUE FUNCIONE EL ENVIO DEL MENSAJE DEBES DE INGRESAR TU CORREO Y CONTRASEÑA AQUI
        //Y ASI EL COIGO PUEDA ENVIAR EL CORREO DESDE OTRO CUENTA EXISTENTE

        sEmail ="Ingresa tu correo";
        sPassword = "Ingresa tu contraseña";



        btEnviarComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //INCIALIZADOR DE ACTIVIDADES
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");

                //INICIALIZADOR DE SESION
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail,sPassword);
                    }
                });


                try {
                    //INICIALIZADOR EMAIL content

                    Message message = new MimeMessage(session);

                    //SENDER EMAIL
                    message.setFrom(new InternetAddress(sEmail));

                    //RECIPIENTE EMAIL
                    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(etCorreo.getText().toString().trim()));

                    //ASUNTO DEL EMAIL
                    message.setSubject(etNombreCompleto.getText().toString().trim());

                    //MENSAJE AL EMAIL
                    message.setText(etComentarios.getText().toString().trim());

                    //Enviar EMAIL
                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    private class SendMail extends AsyncTask<Message,String,String> {
        //INICIALIZA
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Create and show progress dialog

            progressDialog =ProgressDialog.show(Contacto.this,"Please wait","Sending Mail...",true,false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                //CUANDO FUNCIONA
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                //CUANDO NO FUNCIONE
                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //IGNORA EN EL DIALOGO DE PROGESO

            progressDialog.dismiss();
            if (s.equals("Success")){
                //CUANDO FUNCIONA
                //INICIALIZA DIALOGO DE ALERTA ALERTA
                AlertDialog.Builder builder = new AlertDialog.Builder(Contacto.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<front color='#509324'>Success</font>"));
                builder.setMessage("Mail send successfully.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        //CLEAR ALL EDIT
                        etCorreo.setText("");
                        etNombreCompleto.setText("");
                        etComentarios.setText("");
                    }
                });
                //show alert dialog
                builder.show();
            }else {
                //When error
                Toast.makeText(getApplicationContext(),"Something went wrong ?",Toast.LENGTH_SHORT).show();
            }
        }
    }
}