package br.edu.faculdadedelta.exercicio3brunodias;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class PrincipalActivity extends AppCompatActivity {

    private EditText etProduto;
    private EditText etValor;
    private EditText etCliente;
    private EditText etDataVenda;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        etProduto = findViewById(R.id.etProduto);
        etValor = findViewById(R.id.etValor);
        etCliente = findViewById(R.id.etCliente);
        etDataVenda = findViewById(R.id.etDataVenda);

        //Mascara dataVenda
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(etDataVenda, smf);
        etDataVenda.addTextChangedListener(mtw);
    }

    public void vender(View view) {
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("produtoParam", etProduto.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("clienteParam", etCliente.getText().toString());
        intent.putExtra("dataVendaParam", etDataVenda.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    public void limpar(View view) {
        etProduto.setText("");
        etValor.setText("");
        etCliente.setText("");
        etDataVenda.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if(requestCode == ValidacaoActivity.RESULT_CODE_SUCESS) {
                String mensagemValidacao =  data.getStringExtra("mensagemValidacao");
                Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
                limpar(null);
            }
        }
    }
}
