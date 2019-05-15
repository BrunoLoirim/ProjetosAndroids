package br.edu.faculdadedelta.seriadoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import br.edu.faculdadedelta.seriadoapp.adapter.SeriadoAdapter;
import br.edu.faculdadedelta.seriadoapp.dao.SeriadoDAO;
import br.edu.faculdadedelta.seriadoapp.modelo.Seriado;

public class ListaSeriadoActivity extends Activity {
	
	private ListView lvListaSeriado;
	private SeriadoDAO dao = new SeriadoDAO();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_seriado);
		
		lvListaSeriado = (ListView) findViewById(R.id.lvListaSeriado);
		
		lvListaSeriado.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Seriado seriadoSelecionado 
					= (Seriado) parent.getItemAtPosition(position);
				Intent intent 
					= new Intent(getBaseContext(), FormularioActivity.class);
				intent.putExtra("seriadoSelecionado", seriadoSelecionado);
				startActivity(intent);
			}
		});
		
		lvListaSeriado
			.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, 
					View view,
					int position, long id) {
				
				Seriado seriadoSelecionado 
					= (Seriado) parent.getItemAtPosition(position);
				
				dao.excluir(seriadoSelecionado);
				
				carregarLista();
				
				return false;
			}
		});
	}

	private void carregarLista() {
		SeriadoAdapter adapter 
			= new SeriadoAdapter(dao.listar(), getBaseContext());
		lvListaSeriado.setAdapter(adapter);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		carregarLista();
	}
	
	
	
	
	
}
