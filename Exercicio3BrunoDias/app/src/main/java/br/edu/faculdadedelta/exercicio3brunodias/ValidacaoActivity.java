package br.edu.faculdadedelta.exercicio3brunodias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvProduto;
    private TextView tvValor;
    private TextView tvCliente;
    private TextView tvDataVenda;

    private String produto;
    private String valor;
    private String cliente;
    private String data;

    public static final int RESULT_CODE_SUCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        tvProduto = findViewById(R.id.tvProduto);
        tvValor = findViewById(R.id.tvValor);
        tvCliente = findViewById(R.id.tvCliente);
        tvDataVenda = findViewById(R.id.tvDataVenda);

        Intent intent = getIntent();

        if (intent != null) {
            produto = intent.getStringExtra("produtoParam");
            valor = intent.getStringExtra("valorParam");
            cliente = intent.getStringExtra("clienteParam");
            data = intent.getStringExtra("dataVendaParam");

            tvProduto.setText("Produto: " + produto);
            tvValor.setText("Valor: " + valor);
            tvCliente.setText("Cliente: " + cliente);
            tvDataVenda.setText("Data da Venda: " + data);

        }
    }

    public void validar(View view) {
        String mensagemValidacao = validarCampos();
        double valorConvertido = 0;
        String dataLimiteVenda = "01/01/2014";
        Date dataConvertida = null;
        Date dataLimiteConvertida = null;
        Date novadata = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        //Conversão de String para Double
        try {
            valorConvertido = Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }


        //Conversão de data Para comparação
        try {
            dataConvertida = sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dataLimiteConvertida = sdf.parse(dataLimiteVenda);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (mensagemValidacao.equals("")) {

            //A tela Validacao deverá validar se o valor é maior que 658.
            if (valorConvertido < 658) {
                mensagemValidacao = "Campo valor deve ser maior que 658";
            }

            //A tela Validacao deverá validar se o produto contém o número de caracteres maior que 10
            else if (produto.length() < 10) {
                mensagemValidacao += "\nCampo produto deve ter mais de 10 caracters";
            }

            //A tela Validacao deverá validar se o cliente contém o número de caracteres maior que 40
            else if (cliente.length() < 40) {
                mensagemValidacao += "\nCampo produto deve ter mais de 40 caracters";
            }

            //A tela Validacao deverá validar se a data da venda é maior que a 01/01/2014
            else if (dataConvertida.before(dataLimiteConvertida) || dataConvertida.after(novadata)) {
                mensagemValidacao += "\nA data de venda não pode ser maior que '01/01/2014' ou menor que a data atual";
            } else {
                mensagemValidacao = "Campos Válidos!";
            }
        }

        Intent intent = new Intent();
        intent.putExtra("mensagemValidacao", mensagemValidacao);

        setResult(RESULT_CODE_SUCESS, intent);

        finish();

    }

    public String validarCampos() {
        String mensagemErro = "";

        if (produto.equals("")) {
            mensagemErro = "O campo 'Produto' é obrigatório!";
        }
        if (valor.equals("")) {
            mensagemErro += "\nO campo 'Valor' é obrigatório!";
        }
        if (cliente.equals("")) {
            mensagemErro += "\nO campo 'Cliente' é obrigatório!";
        }
        if (data.equals("")) {
            mensagemErro += "\nO campo 'Data da Venda' é obrigatório!";
        }

        return mensagemErro;
    }

    public void voltar(View view) {
        finish();
    }
}
