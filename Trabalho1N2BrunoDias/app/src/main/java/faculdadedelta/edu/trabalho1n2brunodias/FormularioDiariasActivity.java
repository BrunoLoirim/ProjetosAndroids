package faculdadedelta.edu.trabalho1n2brunodias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.dao.DiariaDao;
import faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.modelo.Diaria;

public class FormularioDiariasActivity extends AppCompatActivity {

    private EditText etDestino;
    private EditText etDataSaida;
    private EditText etDataChegada;
    private EditText etItinerario;
    private EditText etFuncionario;
    private EditText etValorDiaria;

    private Diaria diaria = new Diaria();
    private DiariaDao dao = new DiariaDao();

    boolean compData;
    Date dataSaidaConvertida = null;
    Date dataChegadaConvertida = null;

    double valorDiaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_diarias);

        etDestino = findViewById(R.id.etDestino);
        etDataSaida = findViewById(R.id.etDataSaida);
        etDataChegada = findViewById(R.id.etDataChegada);
        etItinerario = findViewById(R.id.etItinerario);
        etFuncionario = findViewById(R.id.etFuncionario);
        etValorDiaria = findViewById(R.id.etValorDiaria);

        Intent intent = getIntent();

        if (intent != null) {
            Diaria diariaSelecionada = (Diaria) intent.getSerializableExtra("diariaSelecionada");
            if(diariaSelecionada != null) {
                popularFormulario(diariaSelecionada);
            }
        }

    }

    private void popularFormulario(Diaria diariaSeleciona) {
        etDestino.setText(diariaSeleciona.getDestino());
        etDataSaida.setText(String.valueOf(diariaSeleciona.getDtSaida()));
        etDataChegada.setText(String.valueOf(diariaSeleciona.getDtChegada()));
        etItinerario.setText(diariaSeleciona.getItinerario());
        etFuncionario.setText(diariaSeleciona.getFuncionario());
        etValorDiaria.setText(String.valueOf(diariaSeleciona.getValor()));

        diaria.setId(diariaSeleciona.getId());

    }

    private void popularModelo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            diaria.setDtSaida(sdf.parse(etDataSaida.getText().toString()));
            diaria.setDtChegada(sdf.parse(etDataChegada.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            dataSaidaConvertida = sdf.parse(etDataSaida.getText().toString());
            dataChegadaConvertida = sdf.parse(etDataChegada.getText().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        diaria.setDestino(etDestino.getText().toString());
        diaria.setItinerario(etItinerario.getText().toString());
        diaria.setFuncionario(etFuncionario.getText().toString());
        diaria.setValor(Double.parseDouble(etValorDiaria.getText().toString()));

    }

    public void salvar(View view) {

        String mensagemValidacao = validarCampos();

        if (mensagemValidacao.equals("")) {
            popularModelo();
            if (diaria.getId() == null) {
                diaria.calculaDesconto();
                dao.incluir(diaria);
                Toast.makeText(getBaseContext(), "Inclusão realizada com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                diaria.calculaDesconto();
                dao.alterar(diaria);
                Toast.makeText(getBaseContext(),"Alteração realizada com sucesso!", Toast.LENGTH_LONG).show();
            }
            limparCampos(null);
        } else {
            Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
        }
    }

    private String validarCampos() {
        String mensagem = "";
        if (etDestino.getText().toString().equals("")) {
            mensagem = "O campo Destino é obrigatório!";
        }
        if (etDataSaida.getText().toString().equals("")) {
            mensagem += "\nO campo Data de Saida é oborigatório!";
        }
        if (etDataChegada.getText().toString().equals("")) {
            mensagem += "\nO campo Data de Chegada é obrigatório!";
        }
        if (etItinerario.getText().toString().equals("")) {
            mensagem += "\nO campo Itinerario de temporada é obrigatório!";
        }
        if (etFuncionario.getText().toString().equals("")) {
            mensagem += "\nO campo Funcionario é obrigatório!";
        }
        if (etValorDiaria.getText().toString().equals("")) {
            mensagem += "\nO campo Valor é obrigatório!";
        }
        return mensagem;
    }

    public void limparCampos(View view) {
        etDestino.setText("");
        etDataChegada.setText("");
        etDataSaida.setText("");
        etItinerario.setText("");
        etFuncionario.setText("");
        etValorDiaria.setText("");

        diaria = new Diaria();
    }

    public void listar(View view) {
        Intent intent = new Intent(getBaseContext(), ListaDiarias.class);
        startActivity(intent);
    }
}
