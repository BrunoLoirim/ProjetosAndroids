package br.edu.faculdadedelta.projetoformularioaluno.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.edu.faculdadedelta.projetoformularioaluno.R;
import br.edu.faculdadedelta.projetoformularioaluno.modelo.Aluno;

public class AlunoAdapter extends BaseAdapter {

	private List<Aluno> lista;
	private Context context;
	
	public AlunoAdapter(List<Aluno> lista, Context context) {
		this.lista = lista;
		this.context = context;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, 
			ViewGroup parent) {
		
		View view = convertView.inflate(context, 
					R.layout.item_lista_aluno, null);
		
		Aluno aluno = (Aluno) getItem(position);
		
		TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
		tvNome.setText("Nome: " + aluno.getNome());
		
		TextView tvId = (TextView) view.findViewById(R.id.tvId);
		tvId.setText("Id: " + aluno.getId());
		
		TextView tvMatricula 
			= (TextView) view.findViewById(R.id.tvMatricula);
		
		tvMatricula.setText("Matricula: " + aluno.getMatricula());
		
		TextView tvCpf = (TextView) view.findViewById(R.id.tvCpf);
		tvCpf.setText("Cpf: " + aluno.getCpf());
		
		return view;
	}
	
}
