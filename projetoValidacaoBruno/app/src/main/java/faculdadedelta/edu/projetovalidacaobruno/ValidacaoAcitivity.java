package faculdadedelta.edu.projetovalidacaobruno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacaoAcitivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvEndereco;
    private TextView tvDataNascimento;

    private String nome;
    private String endereco;
    private String dataNascimento;

    public static final int RESULT_CODE_SUCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validacao_acitivity);

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvEndereco = (TextView) findViewById(R.id.tvEndereco);
        tvDataNascimento = (TextView) findViewById(R.id.tvDataNascimento);

        Intent intent = getIntent();

        if (intent != null) {
            nome = intent.getStringExtra("nomeParam");
            endereco = intent.getStringExtra("enderecoParam");
            dataNascimento = intent.getStringExtra("dataParam");

            tvNome.setText("Nome: " + nome);
            tvEndereco.setText("Endereço: " + endereco);
            //tvDataNascimento.setText("Data de nascimento: ".concat(data));
            tvDataNascimento.setText("Data de nascimento: " + dataNascimento);

        }
    }

    public void validar(View view) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String mensagemValidacao = "";

        if (mensagemValidacao.equals("")) {

            try {
                Date dataConvertida = df.parse(dataNascimento);
                Date dataAtual = new Date();

                long idade = (dataAtual.getTime() - dataConvertida.getTime()) / 1000 / 60 / 60 / 24 / 360;

                if (idade > 18) {
                    mensagemValidacao = "Válido!";
                } else {
                    mensagemValidacao = "Inválido!";
                }
            } catch (ParseException e) {
                e.printStackTrace();
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
        if (endereco.equals("")) {
            mensagemErro += "\nO campo endereço é obrigatório!";
        }
        if (dataNascimento.equals("")) {
            mensagemErro += "\nO campo data é obrigatório!";
        }
        return mensagemErro;
    }

}