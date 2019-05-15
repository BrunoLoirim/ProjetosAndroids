package faculdadedelta.edu.projetoformularioaluno.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import faculdadedelta.edu.projetoformularioaluno.R;
import faculdadedelta.edu.projetoformularioaluno.modelo.Aluno;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView.inflate(context, R.layout.item_lista_aluno, null);

        Aluno aluno = (Aluno) getItem(position);

        TextView tvId = convertView.findViewById(R.id.tvId);
        tvId.setText("ID: " + aluno.getId());

        TextView tvNome = convertView.findViewById(R.id.tvNome);
        tvNome.setText("Nome: " + aluno.getNome());

        TextView tvMatricula = convertView.findViewById(R.id.tvMatricula);
        tvNome.setText("Matricula: " + aluno.getMatricula());

        TextView tvCpf = convertView.findViewById(R.id.tvCpf);
        tvNome.setText("CPF: " + aluno.getCpf());



        return v;
    }
}
