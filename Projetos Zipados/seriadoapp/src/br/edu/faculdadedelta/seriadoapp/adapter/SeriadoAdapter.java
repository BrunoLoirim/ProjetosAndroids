package br.edu.faculdadedelta.seriadoapp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.edu.faculdadedelta.seriadoapp.R;
import br.edu.faculdadedelta.seriadoapp.modelo.Seriado;

public class SeriadoAdapter extends BaseAdapter {
	
	private List<Seriado> listaSeriado;
	private Context context;
	
	public SeriadoAdapter(List<Seriado> listaSeriado, Context context) {
		this.listaSeriado = listaSeriado;
		this.context = context;
	}

	@Override
	public int getCount() {
		return listaSeriado.size();
	}

	@Override
	public Object getItem(int position) {
		return listaSeriado.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, 
			View convertView, ViewGroup parent) {

		View view = convertView.inflate(context, 
				R.layout.item_lista_seriado, null);
		
		Seriado seriado = (Seriado) getItem(position);
		
		TextView tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
		tvTitulo.setText("Titulo: " + seriado.getTitulo());
		
		TextView tvNota = (TextView) view.findViewById(R.id.tvNota);
		tvNota.setText("Nota: " + seriado.getNota());
		
		TextView tvNumeroTemporada 
			= (TextView) view.findViewById(R.id.tvNumeroTemporada);
		tvNumeroTemporada.setText(
				"Numero de temporada: " + seriado.getNumeroTemporada());
		
		return view;
	}
	
	
}
