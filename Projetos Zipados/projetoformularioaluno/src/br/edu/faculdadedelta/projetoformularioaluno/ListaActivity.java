package br.edu.faculdadedelta.projetoformularioaluno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import br.edu.faculdadedelta.projetoformularioaluno.adapter.AlunoAdapter;
import br.edu.faculdadedelta.projetoformularioaluno.dao.AlunoDAO;
import br.edu.faculdadedelta.projetoformularioaluno.modelo.Aluno;

public class ListaActivity extends Activity {

	private ListView lvListaAluno;
	private AlunoDAO dao = new AlunoDAO();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		
		lvListaAluno = (ListView) findViewById(R.id.lvListaAluno);
		
		lvListaAluno.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Aluno alunoSelecionado 
					= (Aluno) parent.getItemAtPosition(position);
				Intent intent 
					= new Intent(getBaseContext(), FormularioActivity.class);
				intent.putExtra("alunoSelecionado", alunoSelecionado);
				
				startActivity(intent);
			}
		});
		
		lvListaAluno.setOnItemLongClickListener(
				new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, 
					View view,
					int position, long id) {
				
				Aluno alunoSelecionado 
					= (Aluno) parent.getItemAtPosition(position);
				
				dao.excluir(alunoSelecionado);
				
				carregarLista();
				
				return false;
			}
		});
	}

	private void carregarLista() {
		AlunoAdapter adapter = new AlunoAdapter(dao.listar(),
				getBaseContext());
		lvListaAluno.setAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		carregarLista();
	}
}
