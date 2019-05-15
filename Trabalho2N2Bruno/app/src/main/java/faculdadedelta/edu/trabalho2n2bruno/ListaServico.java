package faculdadedelta.edu.trabalho2n2bruno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import faculdadedelta.edu.trabalho2n2bruno.br.com.adapter.ServicoAdapter;
import faculdadedelta.edu.trabalho2n2bruno.br.com.dao.ServicoDao;
import faculdadedelta.edu.trabalho2n2bruno.br.com.modelo.Servico;

public class ListaServico extends AppCompatActivity {

    private ListView lvListaServico;
    private ServicoDao dao = new ServicoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_servico);

        lvListaServico = findViewById(R.id.lvListaServico);

        lvListaServico.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Servico servicoSelecionado = (Servico) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("servicoSelecionado", servicoSelecionado);
                startActivity(intent);
            }
        });

        lvListaServico.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Servico servicoSelecionado = (Servico) parent.getItemAtPosition(position);

                dao.excluir(servicoSelecionado);

                carregarLista();

                return false;
            }
        });
    }

    private void carregarLista() {
        ServicoAdapter adapter = new ServicoAdapter(dao.listar(), getBaseContext());
        lvListaServico.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
