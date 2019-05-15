package faculdadedelta.edu.trabalho2n2brunodias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import faculdadedelta.edu.trabalho2n2brunodias.br.com.dao.ServicoDao;
import faculdadedelta.edu.trabalho2n2brunodias.br.com.modelo.Servico;

public class MainActivity extends AppCompatActivity {

    private EditText etCliente;
    private EditText etServico;
    private EditText etQtdServico;
    private EditText etVlrServico;
    private EditText etDtServico;

    private Servico servico = new Servico();
    private ServicoDao dao = new ServicoDao();

    Date dtServicoConvertida = null;
    Date dtAtual = new Date();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCliente = findViewById(R.id.etCliente);
        etServico = findViewById(R.id.etServico);
        etQtdServico = findViewById(R.id.etQtdServico);
        etVlrServico = findViewById(R.id.etVlrServico);
        etDtServico = findViewById(R.id.etDtExecucao);

        // Criando a mascara no campo Data de Nascimento
        //SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        //MaskTextWatcher mtw = new MaskTextWatcher(etDtServico, smf);
        //etDtServico.addTextChangedListener(mtw); //Referenciando o campo dizendo que o mesmo possui mascara

        Intent intent = getIntent();

        if (intent != null) {
            Servico servicoSeleciona = (Servico) intent.getSerializableExtra("servicoSeleciona");
            if(servicoSeleciona !=null){
                popularFormulario(servicoSeleciona);
            }
        }
    }

    private void popularFormulario(Servico servicoSeleciona) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        etCliente.setText(servicoSeleciona.getCliente());
        etServico.setText(servicoSeleciona.getServico());
        etQtdServico.setText(String.valueOf(servicoSeleciona.getQtdServico()));
        etVlrServico.setText(String.valueOf(servicoSeleciona.getVlrServico()));
        etDtServico.setText(String.valueOf(sdf.format(servicoSeleciona.getDtServico())));

        servico.setId(servicoSeleciona.getId());

    }

    private void popularModelo() {
        try {
            servico.setDtServico(sdf.parse(etDtServico.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dtServicoConvertida = sdf.parse(etDtServico.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        servico.setCliente(etCliente.getText().toString());
        servico.setServico(etServico.getText().toString());
        servico.setQtdServico(Integer.parseInt(etQtdServico.getText().toString()));
        servico.setVlrServico(Double.parseDouble(etVlrServico.getText().toString()));

    }

    public void salvar(View view) {

        String mensagemValidacao = validarCampos();

        if (mensagemValidacao.equals("")) {

            if(Integer.parseInt(etQtdServico.getText().toString()) < 1) {
                Toast.makeText(getBaseContext(), "O campo QUANTIDADE deve ser maior que 0", Toast.LENGTH_LONG).show();
                etQtdServico.setText("");
            }

            try {
                if(sdf.parse(etDtServico.getText().toString()).getTime() > dtAtual.getTime()) {
                    Toast.makeText(getBaseContext(), "O campo Data Execução deve ser menor que a data atual", Toast.LENGTH_LONG).show();
                    etDtServico.setText("");
                } else {
                    popularModelo();
                    if (servico.getId() == null) {
                        servico.calculaDesconto();
                        dao.incluir(servico);
                        Toast.makeText(getBaseContext(), "Inclusão realizada com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        servico.calculaDesconto();
                        dao.alterar(servico);
                        Toast.makeText(getBaseContext(),"Alteração realizada com sucesso!", Toast.LENGTH_LONG).show();
                    }
                    limparCampos(null);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
        }
    }

    private String validarCampos() {
        String mensagem = "";
        if (etCliente.getText().toString().equals("")) {
            mensagem = "O campo Cliente é obrigatório!";
        }
        if (etServico.getText().toString().equals("")) {
            mensagem += "\nO campo Serviço é oborigatório!";
        }
        if (etQtdServico.getText().toString().equals("")) {
            mensagem += "\nO campo Quantidade é obrigatório!";
        }
        if (etVlrServico.getText().toString().equals("")) {
            mensagem += "\nO campo Valor é obrigatório!";
        }
        if (etDtServico.getText().toString().equals("")) {
            mensagem += "\nO campo Data de Execução é obrigatório!";
        }

        return mensagem;
    }

    public void limparCampos(View view) {
        etCliente.setText("");
        etServico.setText("");
        etQtdServico.setText("");
        etVlrServico.setText("");
        etDtServico.setText("");

        servico = new Servico();
    }

    public void listar(View view) {
        Intent intent = new Intent(getBaseContext(), ListaServico.class);
        startActivity(intent);
    }
}
