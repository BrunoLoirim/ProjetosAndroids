package faculdadedelta.edu.provan1brunodias;

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
    private EditText etCpf;
    private EditText etPlano;
    private EditText etDataNascimento;
    private EditText etDataInicio;
    private EditText etDataFim;
    private EditText etValor;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        etPlano = findViewById(R.id.etPlano);
        etDataNascimento = findViewById(R.id.etDataNascimento);
        etDataInicio = findViewById(R.id.etDataInicio);
        etDataFim = findViewById(R.id.etDataFim);
        etValor = findViewById(R.id.etValor);

        // Criando a mascara no campo Data de Nascimento
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(etDataNascimento, smf);
        etDataNascimento.addTextChangedListener(mtw); //Referenciando o campo dizendo que o mesmo possui mascara

        SimpleMaskFormatter smf2 = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw2 = new MaskTextWatcher(etDataInicio, smf2);
        etDataInicio.addTextChangedListener(mtw2);

        SimpleMaskFormatter smf3 = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw3 = new MaskTextWatcher(etDataFim, smf3);
        etDataFim.addTextChangedListener(mtw3);

        SimpleMaskFormatter smf4 = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw4 = new MaskTextWatcher(etCpf, smf4);
        etCpf.addTextChangedListener(mtw4);

    }



    public void salvar(View view) {
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("nomeParam", etNome.getText().toString());
        intent.putExtra("cpfParam", etCpf.getText().toString());
        intent.putExtra("planoParam", etPlano.getText().toString());
        intent.putExtra("dataNascimentoParam", etDataNascimento.getText().toString());
        intent.putExtra("dataInicioParam", etDataInicio.getText().toString());
        intent.putExtra("dataFimParam", etDataFim.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    public void limpar(View view) {
        etNome.setText("");
        etCpf.setText("");
        etPlano.setText("");
        etDataNascimento.setText("");
        etDataInicio.setText("");
        etDataFim.setText("");
        etValor.setText("");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE) {
            if(requestCode == ValidacaoActivity.RESULT_CODE_SUCESS) {
                String mensagemValidacao =  data.getStringExtra("mensagemValidacao");
                Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
                limpar(null);
            }
        }
    }
}
