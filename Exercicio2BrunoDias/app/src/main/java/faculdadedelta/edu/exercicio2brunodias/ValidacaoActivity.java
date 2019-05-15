package faculdadedelta.edu.exercicio2brunodias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvValor;
    private TextView tvCor;
    private TextView tvModelo;

    private String nome;
    private String valor;
    private String cor;
    private String modelo;

    public static final int RESULT_CODE_SUCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        tvNome = findViewById(R.id.tvNome);
        tvValor = findViewById(R.id.tvValor);
        tvCor = findViewById(R.id.tvCor);
        tvModelo = findViewById(R.id.tvModelo);

        Intent intent = getIntent();

        if (intent != null) {
            nome = intent.getStringExtra("nomeParam");
            valor = intent.getStringExtra("valorParam");
            cor = intent.getStringExtra("corParam");
            modelo = intent.getStringExtra("modeloParam");

            tvNome.setText("Nome: " + nome);
            tvValor.setText("Valor: " + valor);
            tvCor.setText("Cor: " + cor);
            tvModelo.setText("Modelo: " + modelo);
        }
    }

    public void validar(View view) {
        String mensagemValidacao = validarCampos();
        double valorConvertido = 0;

        try {
            valorConvertido = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }

        if (mensagemValidacao.equals("")) {
            if (nome.length() < 30) {
                mensagemValidacao = "O campo nome deve ser maior que 30 caracters!";
            }
            else if (valorConvertido < 1000.0) {
                mensagemValidacao += "\nO valor deve ser maior que 1000!";
            }
            else if (modelo.length() < 40) {
                mensagemValidacao += "\nO campo modelo deve ser maior que 40 caracters!";
            }
            else {
                mensagemValidacao = "Irrul, Campos Preenchidos Corretamente!";
            }
        }

        Intent intent = new Intent();
        intent.putExtra("mensagemValidacao", mensagemValidacao);

        setResult(RESULT_CODE_SUCESS, intent);

        finish();
    }

    public String validarCampos() {
        String mensagemErro = "";
        if (nome.equals("")) {
            mensagemErro = "O campo nome é obrigatório!";
        }
        if (valor.equals("")) {
            mensagemErro += "\nO campo valor é obrigatório!";
        }
        if (cor.equals("")) {
            mensagemErro += "\nO campo Cor é obrigatório!";
        }
        if (modelo.equals("")) {
            mensagemErro += "\nO campo Modelo é obrigatório!";
        }
        return mensagemErro;
    }
}
