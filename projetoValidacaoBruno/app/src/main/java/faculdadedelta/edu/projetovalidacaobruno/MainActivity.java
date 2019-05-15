package faculdadedelta.edu.projetovalidacaobruno;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEndereco;
    private EditText etDataNascimento;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pegando os valores
        etNome = findViewById(R.id.etNome);
        etEndereco = findViewById(R.id.etEndereco);
        etDataNascimento = findViewById(R.id.etDataNascimento);

        //Criando mascara
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(etDataNascimento, smf);
        etDataNascimento.addTextChangedListener(mtw);
    }

    public void enviar(View view) {
        Intent intent = new Intent(getBaseContext(), ValidacaoAcitivity.class);

        intent.putExtra("nomeParam", etNome.getText().toString());
        intent.putExtra("enderecoParam", etEndereco.getText().toString());
        intent.putExtra("dataParam", etDataNascimento.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    public void limpar(View view) {
        etNome.setText("");
        etEndereco.setText("");
        etDataNascimento.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        limpar(null);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == ValidacaoAcitivity.RESULT_CODE_SUCESS) {
                String mensagemValidacao = data.getStringExtra("mensagemValidacao");
                Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
            }
        }
    }
}
