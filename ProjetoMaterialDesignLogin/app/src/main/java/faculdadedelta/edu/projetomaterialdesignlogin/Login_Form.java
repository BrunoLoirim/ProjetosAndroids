package faculdadedelta.edu.projetomaterialdesignlogin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Form extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etSenha;

    private String usuario;
    private String senha;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login");
    }

    public void registrar(View view) {

        Intent intent = new Intent(getBaseContext(), Signup_Form.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = getIntent();

        if (intent != null) {
            usuario = intent.getStringExtra("usuarioParam");
            senha = intent.getStringExtra("senhaParam");

            Toast.makeText(getBaseContext(), "Apenas Didático", Toast.LENGTH_SHORT).show();
        }
    }

}
