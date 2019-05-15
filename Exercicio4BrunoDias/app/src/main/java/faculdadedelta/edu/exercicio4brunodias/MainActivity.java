package faculdadedelta.edu.exercicio4brunodias;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etProduto;
    private EditText etValor;
    private EditText etFornecedor;
    private EditText etQuantidade;

    private String mensagemValidacao;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProduto = findViewById(R.id.etProduto);
        etValor = findViewById(R.id.etValor);
        etFornecedor = findViewById(R.id.etFornecedor);
        etQuantidade = findViewById(R.id.etQuantidade);
    }

    public void enviar(View view) {
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("produtoParam", etProduto.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("fornecedorParam", etFornecedor.getText().toString());
        intent.putExtra("quantidadeParam", etQuantidade.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    public void limpar(View view) {
        etProduto.setText("");
        etValor.setText("");
        etFornecedor.setText("");
        etQuantidade.setText("");

        if (etProduto.equals("") && etValor.equals("") && etFornecedor.equals("") && etQuantidade.equals("")) {
            mensagemValidacao = "Sem dados preenchidos!";
            Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE) {
            if (requestCode == ValidacaoActivity.RESULT_CODE_SUCESS) {
                mensagemValidacao =  data.getStringExtra("mensagemValidacao");
                Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
                limpar(null);
            }
        }
    }
}
