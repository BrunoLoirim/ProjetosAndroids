package faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import faculdadedelta.edu.trabalho1n2brunodias.R;
import faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.modelo.Diaria;

public class DiariaAdapter extends BaseAdapter {

    private List<Diaria> listaDiaria;
    private Context context;

    public DiariaAdapter(List<Diaria> listaDiaria, Context context) {
        this.listaDiaria = listaDiaria;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaDiaria.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDiaria.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.activity_item_lista_diarias, null);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Diaria diaria = (Diaria) getItem(position);

        TextView tvDestino = view.findViewById(R.id.tvDestino);
        tvDestino.setText("Destino: " + diaria.getDestino());

        TextView tvDataSaida = view.findViewById(R.id.tvDataSaida);
        tvDataSaida.setText("Data de Saida: " + sdf.format(diaria.getDtSaida()));

        TextView tvDataChegada = view.findViewById(R.id.tvDataChegada);
        tvDataChegada.setText("Data de Chegada: " + sdf.format(diaria.getDtChegada()));

        TextView tvItinerario = view.findViewById(R.id.tvItinerario);
        tvItinerario.setText("Itinerario: " + diaria.getItinerario());

        TextView tvFuncionario = view.findViewById(R.id.tvFuncionario);
        tvFuncionario.setText("Funcionário: " + diaria.getFuncionario());

        TextView tvValorDiaria = view.findViewById(R.id.tvValorDiaria);
        tvValorDiaria.setText("Valor Diaria: " + diaria.getValor());

        return view;
    }
}
