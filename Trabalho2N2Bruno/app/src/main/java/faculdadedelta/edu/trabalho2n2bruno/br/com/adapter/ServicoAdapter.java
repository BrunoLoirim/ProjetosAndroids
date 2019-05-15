package faculdadedelta.edu.trabalho2n2bruno.br.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import faculdadedelta.edu.trabalho2n2bruno.R;
import faculdadedelta.edu.trabalho2n2bruno.br.com.modelo.Servico;

public class ServicoAdapter extends BaseAdapter {

    private List<Servico> listaServico;
    private Context context;

    public ServicoAdapter(List<Servico> listar, Context baseContext) {
        this.listaServico = listaServico;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaServico.size();
    }

    @Override
    public Object getItem(int position) {
        return listaServico.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.activity_item_lista_servico, null);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Servico servico = (Servico) getItem(position);

        TextView tvCliente = view.findViewById(R.id.tvCliente);
        tvCliente.setText("Cliente: " + servico.getCliente());

        TextView tvServico = view.findViewById(R.id.tvServico);
        tvServico.setText("Serviço: " + servico.getServico());

        TextView tvQntdServico = view.findViewById(R.id.tvQntdServico);
        tvQntdServico.setText("Quantidade: " + servico.getQtdServico());

        TextView tvVlrServico = view.findViewById(R.id.tvVlrServico);
        tvVlrServico.setText("Valor: " + servico.getVlrServico());

        TextView tvDtExecucao = view.findViewById(R.id.tvDtExecucao);
        tvDtExecucao.setText("Data de Execução: " + sdf.format(servico.getDtExecucao()));

        return view;
    }
}
