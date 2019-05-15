package br.edu.faculdadedelta.exercicio1brunodias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacaoActivity extends AppCompatActivity {

    private String nome;
    private String data;
    private String peso;
    private String altura;
    private double pesoConvertido;

    public static final int RESULT_CODE_SUCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();

        if (intent != null) {
            nome = intent.getStringExtra("nomeParam");
            data = intent.getStringExtra("dataNascimentoParam");
            peso = intent.getStringExtra("pesoParam");
            altura = intent.getStringExtra("alturaParam");

            TextView tvNome = (TextView) findViewById(R.id.tvNome);
            tvNome.setText("Nome: " + nome);

            TextView tvDataNascimento = (TextView) findViewById(R.id.tvDataNascimento);
            tvDataNascimento.setText("Data de Nascimento: " + data);

            TextView tvPeso = (TextView) findViewById(R.id.tvPeso);
            tvPeso.setText("Peso: " + peso);

            TextView tvAltura  = (TextView) findViewById(R.id.tvAltura);
            tvAltura.setText("Altura: " + altura);
        }
    }

    public String validarCampos() {
        String mensagemErro = "";
        if (nome.equals("")) {
            mensagemErro = "O campo nome é obrigatório!";
        }
        if (data.equals("")) {
            mensagemErro += "\nO campo data de nascimento é obrigatório!";
        }
        if (peso.equals("")) {
            mensagemErro += "\nO campo peso é obrigatório!";
        }
        if(altura.equals("")) {
            mensagemErro += "\nO campo altura é obrigatório!";
        }
        return mensagemErro;
    }

    public void validar(View view) {
        String mensagemValidacao = validarCampos(); //Validar os campos
        double pesoConvertido = 0;

        try {
            pesoConvertido = Double.parseDouble(peso);
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataConvertida = null;
        try {
            dataConvertida = sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date novaData = new Date();

        if (mensagemValidacao.equals("")) {

            if (nome.length() <= 30) {
                mensagemValidacao = "O campo nome deve ter mais de 30 caracters";
            }
            else if (pesoConvertido < 60.4) {
                mensagemValidacao += "\nPeso deve ser maior que 60,4";
            }
            else if(dataConvertida.after(novaData)) {
                mensagemValidacao += "\nData de Nascimento é maior que a data atual";
            } else {
                mensagemValidacao = "Dados Válidos!";
            }
        }

        Intent intent = new Intent();
        intent.putExtra("mensagemValidacao", mensagemValidacao);

        setResult(RESULT_CODE_SUCESS, intent);

        finish();
    }
}
