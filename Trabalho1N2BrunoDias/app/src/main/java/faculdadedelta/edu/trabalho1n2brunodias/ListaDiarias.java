package faculdadedelta.edu.trabalho1n2brunodias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.adapter.DiariaAdapter;
import faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.dao.DiariaDao;
import faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.modelo.Diaria;

public class ListaDiarias extends AppCompatActivity {

    private ListView lvListaDiaria;
    private DiariaDao dao = new DiariaDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_diarias);

        lvListaDiaria = findViewById(R.id.lvListaDiaria);

        lvListaDiaria.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diaria diariaSelecionada = (Diaria) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), FormularioDiariasActivity.class);
                intent.putExtra("diariaSelecionada", diariaSelecionada);
                startActivity(intent);
            }
        });

        lvListaDiaria.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Diaria diariaSelecionada = (Diaria) parent.getItemAtPosition(position);

                dao.excluir(diariaSelecionada);

                carregarLista();

                return false;
            }
        });
    }

    private void carregarLista() {
        DiariaAdapter adapter = new DiariaAdapter(dao.listar(), getBaseContext());
        lvListaDiaria.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
