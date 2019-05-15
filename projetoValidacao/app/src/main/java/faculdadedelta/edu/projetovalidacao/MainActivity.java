package faculdadedelta.edu.projetovalidacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEndereco;
    private EditText etDataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pegando os valores
        etNome = findViewById(R.id.etNome);
        etEndereco = findViewById(R.id.etEndereco);

        //Criando mascara
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(etDataNascimento, smf);
        etDataNascimento.addTextChangedListener(mtw);
    }

    public void enviar(View view) {

    }
}
