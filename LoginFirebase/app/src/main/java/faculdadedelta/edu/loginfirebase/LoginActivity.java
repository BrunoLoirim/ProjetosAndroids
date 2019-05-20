package faculdadedelta.edu.loginfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.file.Files;
import java.time.Instant;

public class LoginActivity extends AppCompatActivity {



    private EditText edEmail;
    private EditText edSenha;
    private Button   btnEnter;
    private TextView txtMsgLink;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edEmail);
        edSenha = findViewById(R.id.edSenha);
        btnEnter = findViewById(R.id.btnEnter);
        txtMsgLink = findViewById(R.id.txtMsgLink);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String senha = edSenha.getText().toString();

                Log.i("Email:", email);
                Log.i("Senha:", senha);
            }
        });
        txtMsgLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegistreActivity.class);

            startActivity(intent);
            }
        });

    }
}
