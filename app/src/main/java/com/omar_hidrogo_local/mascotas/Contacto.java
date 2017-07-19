package com.omar_hidrogo_local.mascotas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {

    private String correo = "pruebacoursera2017@gmail.com";
    private String password = "O123456789";

    private EditText etNombre;
    private EditText etEmail;
    private EditText etComentarios;
    private Session session;

    private Button btnSend;
    private ProgressDialog dialog=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.actionBarcontacto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Toast.makeText(Contacto.this, "Para validar el funcionamiento del correo  teclea tu cuenta para que te envies un EMAIL", Toast.LENGTH_LONG).show();

        etNombre = (EditText) findViewById(R.id.etNombre);
        btnSend = (Button) findViewById(R.id.btnSend);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etComentarios = (EditText) findViewById(R.id.etComentarios);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "465");

                try{

                    session=Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,password);
                        }
                    });

                    if(session!=null){
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("Envio desde aplicacion Android");
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(etEmail.getText().toString()));
                        message.setContent("<table><tr><td><strong>Nombre:</strong></td><td>" + etNombre.getText() +
                                "</td></tr><tr><td><strong>Email:</strong></td><td>" + etEmail.getText() +
                                "</td></tr><tr><td><strong>Comentarios:</strong></td><td>" + etComentarios.getText().toString() +
                                "</td></tr></table>", "text/html; charset=utf-8");
                        Transport.send(message);
                        Toast.makeText(Contacto.this, "Mensaje enviado con éxito.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(Contacto.this, "El mensaje no pudo ser enviado, verifica bien el campo de Email o Conexion a Internet", Toast.LENGTH_LONG).show();
                }

            }
        });




    }
}
