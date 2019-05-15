package faculdadedelta.edu.projetoformularioaluno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import faculdadedelta.edu.projetoformularioaluno.adapter.AlunoAdapter;
import faculdadedelta.edu.projetoformularioaluno.dao.AlunoDAO;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaAluno;
    private AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaAluno = findViewById(R.id.lvListaAluno);
    }

    private void carregarLista() {
        AlunoAdapter adapter = new AlunoAdapter(dao.listar(), getBaseContext());
        lvListaAluno.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
