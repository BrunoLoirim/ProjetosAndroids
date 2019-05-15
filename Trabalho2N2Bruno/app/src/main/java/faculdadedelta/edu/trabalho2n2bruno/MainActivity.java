package faculdadedelta.edu.trabalho2n2bruno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import faculdadedelta.edu.trabalho2n2bruno.br.com.dao.ServicoDao;
import faculdadedelta.edu.trabalho2n2bruno.br.com.modelo.Servico;

public class MainActivity extends AppCompatActivity {

    private EditText etCliente;
    private EditText etServico;
    private EditText etQntdServico;
    private EditText etVlrServico;
    private EditText etDtExecucao;

    private Servico servico = new Servico();
    private ServicoDao dao = new ServicoDao();

    private Date dataConvertida = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCliente.findViewById(R.id.etCliente);
        etServico.findViewById(R.id.etServico);
        etQntdServico.findViewById(R.id.etQntdServico);
        etVlrServico.findViewById(R.id.etVlrServico);
        etDtExecucao.findViewById(R.id.etDtExecucao);

        Intent intent = getIntent();

        if (intent != null) {
            Servico servicoSelecionado = (Servico) intent.getSerializableExtra("servicoSelecionado");
            if (servicoSelecionado != null) {
                popularFormulario(servicoSelecionado);
            }
        }
    }

        private void popularFormulario(Servico servicoSeleciona) {
            etCliente.setText(servicoSeleciona.getCliente());
            etServico.setText(servicoSeleciona.getServico());
            etQntdServico.setText(servicoSeleciona.getQtdServico());
            etVlrServico.setText(String.valueOf(servicoSeleciona.getVlrServico()));
            etDtExecucao.setText(String.valueOf(servicoSeleciona.getDtExecucao()));

            servico.setId(servicoSeleciona.getId());

        }

        private void popularModelo() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            try {
                servico.setDtExecucao(sdf.parse(etDtExecucao.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                dataConvertida = sdf.parse(etDtExecucao.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            servico.setCliente(etCliente.getText().toString());
            servico.setServico(etServico.getText().toString());
            servico.setQtdServico(Integer.parseInt(etQntdServico.getText().toString()));
            servico.setVlrServico(Double.parseDouble(etVlrServico.getText().toString()));

        }

        public void salvar(View view) {

            String mensagemValidacao = validarCampos();

            if (mensagemValidacao.equals("")) {
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
            } else {
                Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
            }
        }

        public void listar(View view){
            Intent intent = new Intent(getBaseContext(), ListaServico.class);
            startActivity(intent);
        }

    public String validarCampos() {
        String mensagem = "";
        if (etCliente.getText().toString().equals("")) {
            mensagem = "O campo Cliente é obrigatório!";
        }
        if (etServico.getText().toString().equals("")) {
            mensagem += "\nO campo Serviço é oborigatório!";
        }
        if (etQntdServico.getText().toString().equals("")) {
            mensagem += "\nO campo Quantidade é obrigatório!";
        }
        if (etVlrServico.getText().toString().equals("")) {
            mensagem += "\nO campo Valor de temporada é obrigatório!";
        }
        if (etDtExecucao.getText().toString().equals("")) {
            mensagem += "\nO campo Data Execução é obrigatório!";
        }

        return mensagem;
    }



    public void limparCampos(View view) {
        etCliente.setText("");
        etServico.setText("");
        etQntdServico.setText("");
        etVlrServico.setText("");
        etDtExecucao.setText("");

        servico = new Servico();

    }
}