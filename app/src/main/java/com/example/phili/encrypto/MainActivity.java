package com.example.phili.encrypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void hash(View view) {
        EditText input = (EditText)findViewById(R.id.input);
        TextView output = (TextView)findViewById(R.id.outputText);

        String text = input.getText().toString();

        try {
            String hash = SHA1Encryptor.SHA1(text);
            output.setText(hash);
        } catch (Exception e) {
            output.setText("An error occurred!");
        }
    }

    public void crypto(View view) {
        EditText encryptionTextField = (EditText)findViewById(R.id.encryptionText);
        EditText passwordField = (EditText)findViewById(R.id.keyText);
        TextView encryptionOutput = (EditText)findViewById(R.id.encryptionOutput);

        AesCbcWithIntegrity.SecretKeys keys = null;
        try {
            keys = AesCbcWithIntegrity.generateKeyFromPassword(passwordField.getText().toString(), "abc");
            String encrypted = AesCbcWithIntegrity.encrypt(encryptionTextField.getText().toString(), keys).toString();

            encryptionOutput.setText(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void decrypt(View view) {
        EditText encryptionTextField = (EditText)findViewById(R.id.encryptionText);
        EditText passwordField = (EditText)findViewById(R.id.keyText);
        TextView encryptionOutput = (EditText)findViewById(R.id.encryptionOutput);

        AesCbcWithIntegrity.SecretKeys keys = null;
        try {
            keys = AesCbcWithIntegrity.generateKeyFromPassword(passwordField.getText().toString(), "abc");
            AesCbcWithIntegrity.CipherTextIvMac cipher = new AesCbcWithIntegrity.CipherTextIvMac(encryptionTextField.getText().toString());

            String decrypted = AesCbcWithIntegrity.decryptString(cipher, keys).toString();
            encryptionOutput.setText(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
