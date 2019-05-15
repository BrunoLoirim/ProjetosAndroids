package faculdadedelta.edu.exercicio4brunodias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvProduto;
    private TextView tvValor;
    private TextView tvFornecedor;
    private TextView tvQuantidade;

    private String produto;
    private String valor;
    private String fornecedor;
    private String quantidade;

    public static final int RESULT_CODE_SUCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        tvProduto = findViewById(R.id.tvProduto);
        tvValor = findViewById(R.id.tvValor);
        tvFornecedor = findViewById(R.id.tvFornecedor);
        tvQuantidade = findViewById(R.id.tvQuantidade);

        Intent intent = getIntent();

        if (intent != null) {
            produto = intent.getStringExtra("produtoParam");
            valor = intent.getStringExtra("valorParam");
            fornecedor = intent.getStringExtra("fornecedorParam");
            quantidade = intent.getStringExtra("quantidadeParam");

            tvProduto.setText("Produto: " + produto);
            tvValor.setText("Valor: " + valor);
            tvFornecedor.setText("Fornecedor: " + fornecedor);
            tvQuantidade.setText("Quantidade: " + quantidade);
        }
    }

    public String validarCampos() {
        String mensagemErro = "";

        if (produto.equals("")) {
            mensagemErro = "Campo 'Produto' é obrigatório!";
        }
        if (valor.equals("")) {
            mensagemErro += "\nCampo 'Valor' é obrigatório!";
        }
        if (fornecedor.equals("")) {
            mensagemErro += "\nCampo 'Fornecedor' é obrigatório!";
        }
        if (quantidade.equals("")) {
            mensagemErro += "\nCampo 'Produto' é obrigatório!";
        }

        return mensagemErro;
    }

    public void validar(View view) {
        String mensagemValidacao = validarCampos();
        double valorConvertido = 0;
        long quantidadeConvertida = 0;

        try {
            valorConvertido = Double.parseDouble(valor);
            quantidadeConvertida = Long.parseLong(quantidade);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        if (mensagemValidacao.equals("")) {

            if (valorConvertido < 500) {
                mensagemValidacao = "O campo 'Valor' deve ser maior que 500";
            }
            else if (produto.length() <= 15) {
                mensagemValidacao += "\nO campo 'Produto' deve ter mais de 15 caracters";
            }
            else if (fornecedor.length() <= 11) {
                mensagemValidacao += "\nO campo 'Fornecedor' deve ter mais de 11 caracters";
            }
            else if (quantidadeConvertida <= 0) {
                mensagemValidacao += "\nO campo 'Quantidade' deve ser maior que 0 (ZERO)";
            } else {
                mensagemValidacao = "Dados inseridos corretamentes!";
            }
        }

        Intent intent = new Intent();
        intent.putExtra("mensagemValidacao", mensagemValidacao);
        setResult(RESULT_CODE_SUCESS, intent);
        finish();
    }
}
