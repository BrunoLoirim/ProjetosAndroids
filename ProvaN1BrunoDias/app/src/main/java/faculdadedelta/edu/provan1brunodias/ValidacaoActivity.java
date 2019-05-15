package faculdadedelta.edu.provan1brunodias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvCpf;
    private TextView tvPlano;
    private TextView tvDataNascimento;
    private TextView tvDataInicio;
    private TextView tvDataFim;
    private TextView tvValor;

    private String nome;
    private String cpf;
    private String plano;
    private String dataNascimento;
    private String dataInicio;
    private String dataFim;
    private String valor;

    private Date dataAtual = new Date();
    private Date dataConvertidaNasc = null;
    private Date dataConvertidaIni = null;
    private Date dataConvertidaFim = null;
    private Date dataConvertidaLimNasc = null;
    Double valorConvertido = 0.0;
    private String dataLimiteNascimento = "01/01/1900";

    public static final int RESULT_CODE_SUCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        tvNome = findViewById(R.id.tvNome);
        tvCpf = findViewById(R.id.tvCpf);
        tvPlano = findViewById(R.id.tvPlano);
        tvDataNascimento = findViewById(R.id.tvDataNascimento);
        tvDataInicio = findViewById(R.id.tvDataInicio);
        tvDataFim = findViewById(R.id.tvDataFim);
        tvValor = findViewById(R.id.tvValor);

        Intent intent = getIntent();

        if (intent != null) {
            nome = intent.getStringExtra("nomeParam");
            cpf = intent.getStringExtra("cpfParam");
            plano = intent.getStringExtra("planoParam");
            dataNascimento = intent.getStringExtra("dataNascimentoParam");
            dataInicio = intent.getStringExtra("dataInicioParam");
            dataFim = intent.getStringExtra("dataFimParam");
            valor = intent.getStringExtra("valorParam");

            tvNome.setText("Nome: " + nome);
            tvCpf.setText("CPF: " + cpf);
            tvPlano.setText("Plano: " + plano);
            tvDataNascimento.setText("Data de Nascimento: " + dataNascimento);
            tvDataInicio.setText("Data Ínicio Procedimento: " + dataInicio);
            tvDataFim.setText("Data Fim Procedimento: " + dataFim);
            tvValor.setText("Valor: " + valor);
        }
    }

    public String validarCampos() {
        String mensagemErro = "";

        if (nome.equals("")) {
            mensagemErro = "Campo 'Nome' é obrigatório!";
        }
        if (cpf.equals("")) {
            mensagemErro += "\nCampo 'CPF' é obrigatório!";
        }
        if (plano.equals("")) {
            mensagemErro += "\nCampo 'Plano' é obrigatório!";
        }
        if (dataNascimento.equals("")) {
            mensagemErro += "\nCampo 'Data de Nascimento' é obrigatório!";
        }
        if (dataInicio.equals("")) {
            mensagemErro += "\nCampo 'Data Ínicio' é obrigatório!";
        }
        if (dataFim.equals("")) {
            mensagemErro += "\nCampo 'Data Fim' é obrigatório!";
        }
        if (valor.equals("")) {
            mensagemErro += "\nCampo 'Valor' é obrigatório!";
        }


        return mensagemErro;
    }

    public void validar(View view) {
        String mensagemValidacao = validarCampos();

        //Conversão de String para Double
        try {
            valorConvertido = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }

        //Conversão de data Para comparação
        try {
            dataConvertidaNasc = sdf.parse(dataNascimento);
            dataConvertidaIni = sdf.parse(dataInicio);
            dataConvertidaFim = sdf.parse(dataFim);
            dataConvertidaLimNasc = sdf.parse(dataLimiteNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(mensagemValidacao.equals("")) {
            if(nome.length() < 30) {
                mensagemValidacao += "\nNome deve ter mais que 30 caracters";
            } if(valorConvertido < 500.00) {
                mensagemValidacao += "\nO Valor da consulta deve ser maior que R$ 500,00";
            } if(cpf.length() < 14) {
                mensagemValidacao += "\nCpf incorreto, por favor preencher corretamente!";
            } if(dataConvertidaNasc.before(dataConvertidaLimNasc) || dataConvertidaNasc.after(dataAtual)) {
                mensagemValidacao += "\nData incorreta, por favor preencher corretamente!";
            } if(dataConvertidaIni.after(dataConvertidaFim)) {
                mensagemValidacao += "\nData Ínicio do procedimento deve ser menor que a data de fim do procedimento!";
            } if(dataConvertidaIni.before(dataConvertidaNasc)) {
                mensagemValidacao += "\nData de inicio do procedimento deve ser maior que a data de nascimento!";
            }
            else {
                mensagemValidacao += "\nExcelente! Todos os campos estão válidos!";
            }
        }

        Intent intent = new Intent();
        intent.putExtra("mensagemValidacao", mensagemValidacao);
        setResult(RESULT_CODE_SUCESS, intent);
        finish();

    }
}
