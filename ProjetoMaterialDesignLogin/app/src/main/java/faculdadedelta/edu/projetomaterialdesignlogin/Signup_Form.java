package faculdadedelta.edu.projetomaterialdesignlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Signup_Form extends AppCompatActivity {

    private EditText etNome;
    private EditText etUsuario;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etSenha2;
    private RadioGroup rgSexo;
    private RadioButton  rbMasculino;
    private RadioButton rbFeminino;
    private final static int RESULT_CODE_SUCESS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        Intent intent = getIntent();

        if (intent != null) {
            etNome = findViewById(R.id.etNome);
            etUsuario = findViewById(R.id.etUsuario);
            etEmail = findViewById(R.id.etEmail);
            etSenha = findViewById(R.id.etSenha);
            etSenha2 = findViewById(R.id.etSenha2);
            rgSexo = findViewById(R.id.rgSexo);
            rbMasculino = findViewById(R.id.rbMasculino);
            rbFeminino = findViewById(R.id.rbFeminino);
        }

    }

    public void registrar(View v) {
        String mensagemValidacao = validarCampos();

        if (mensagemValidacao.equals("")) {
            if (etNome.getText().toString().length() < 5) {
                mensagemValidacao = "O campo 'Nome' deve possuir mais que 5 caracters";
            }
            else if (etUsuario.getText().toString().length() < 5) {
                mensagemValidacao += "\nO campo 'Usuario' deve possuir mais que 5 caracters";
            } else if (etSenha.getText().toString().length() < 6) {
                mensagemValidacao += "\nO campo 'Senha' deve possuir mais que 6 caracters";
            } else if (etSenha2.getText().toString().length() < 6) {
                mensagemValidacao += "\nO campo 'Senha' deve possuir mais que 6 caracters";
            } else if (!etSenha.equals(etSenha2)) {
                mensagemValidacao += "\nSenhas não são iguais!";
            } else{
                Intent intent = new Intent(getBaseContext(), Login_Form.class);
                intent.putExtra("usuarioParam", etUsuario.getText().toString());
                intent.putExtra("senhaParam", etSenha.getText().toString());

                mensagemValidacao = "Cadastro Realizado!";

                intent.putExtra("mensagemValidacao", mensagemValidacao);
                setResult(RESULT_CODE_SUCESS, intent);
                finish();
            }
        } else {
            Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_SHORT).show();
    }

    public String validarCampos() {
        String mensagemErro = "";

        if (etNome.getText().toString().equals("")) {
            mensagemErro = "Campo 'Nome' é obrigatório!";
        }
        if (etUsuario.getText().toString().equals("")) {
            mensagemErro += "\nCampo 'Usuario' é obrigatório!";
        }
        if (etEmail.getText().toString().equals("")) {
            mensagemErro += "\nCampo 'Email' é obrigatório!";
        }
        if (etSenha.getText().toString().equals("")) {
            mensagemErro += "\nCampo 'Senha' é obrigatório!";
        }
        if (etSenha2.getText().toString().equals("")) {
            mensagemErro += "\nCampo 'Confirma Senha' é obrigatório!";
        }
        if (!rbMasculino.isChecked() && !rbFeminino.isChecked()) {
            mensagemErro += "\nCampo 'Sexo' é obrigatório!";
        }

        return mensagemErro;
    }
}
