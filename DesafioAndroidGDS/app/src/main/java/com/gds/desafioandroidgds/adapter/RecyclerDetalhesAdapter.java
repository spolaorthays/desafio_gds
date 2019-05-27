package com.gds.desafioandroidgds.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gds.desafioandroidgds.R;
import com.gds.desafioandroidgds.database.MyDbHandler;
import com.gds.desafioandroidgds.model.Movimento;

import java.util.List;

public class RecyclerDetalhesAdapter extends RecyclerView.Adapter<RecyclerDetalhesAdapter.ViewHolder> {

    private List<Movimento> movimentoList;
    private MyDbHandler dbHandler;

    public RecyclerDetalhesAdapter(List<Movimento> movimentoList, MyDbHandler dbHandler) {
        this.movimentoList = movimentoList;
        this.dbHandler = dbHandler;
    }

    @NonNull
    @Override
    public RecyclerDetalhesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_detalhes_item, viewGroup,false);
        dbHandler = new MyDbHandler(view.getContext(), null, null, 1);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDetalhesAdapter.ViewHolder viewHolder, int i) {
        Movimento movimento = movimentoList.get(i);
        viewHolder.bind(movimento);
    }

    @Override
    public int getItemCount() {
        return movimentoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView numId;
        private TextView codProduto;
        private TextView quantidade;
        private TextView valorTotal;
        private TextView dataOcorrencia;
        private TextView operacaoDC;
        private TextView descricao;
        private TextView cancelado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numId = itemView.findViewById(R.id.det_num_id);
            codProduto = itemView.findViewById(R.id.det_produto_id);
            quantidade = itemView.findViewById(R.id.det_quantidade_id);
            valorTotal = itemView.findViewById(R.id.det_valortotal_id);
            dataOcorrencia = itemView.findViewById(R.id.det_dataocorrencia_id);
            operacaoDC = itemView.findViewById(R.id.det_operacaodc_id);
            descricao = itemView.findViewById(R.id.det_descricao_id);
            cancelado = itemView.findViewById(R.id.det_cancelado_id);
        }

        public void bind(Movimento movimento){
            //Formatando quantidade
            float parseQtdade = Float.parseFloat(movimento.getQuantidade());
            int castQtdade = (int) parseQtdade;
            String qtdadeCorreta = String.format("%d", castQtdade);

            //Formatando parte monetária
            String real = "R$ ";
            float valor = Float.parseFloat(movimento.getVlTotal());
            String valorCorreto = String.format("%s%.2f", real, valor);

            //Formatando parte de operações
            String dc = movimento.getOperacaoDC().trim().toLowerCase().toUpperCase().toString();
            String dbt = "D".trim().toLowerCase().toUpperCase().toString();
            String cdt = "C".trim().toLowerCase().toUpperCase().toString();
            String debito = "Débito";
            String credito = "Crédito";

            //Formatando parte de cancelamento
            String compraCancelada = movimento.getCancelado().trim().toLowerCase().toUpperCase().toString();
            String n = "N".trim().toLowerCase().toUpperCase().toString();
            String s = "S".trim().toLowerCase().toUpperCase().toString();
            String nao = "Não";
            String sim = "Sim";

            numId.setText(movimento.getNumId());
            codProduto.setText(movimento.getCodProduto());
            quantidade.setText(qtdadeCorreta);
            valorTotal.setText(valorCorreto);
            dataOcorrencia.setText(movimento.getDtOcorrencia());
            if (dc.equals(dbt)){
                operacaoDC.setText(debito);
            }else if (dc.equals(cdt)){
                operacaoDC.setText(credito);
            }

            descricao.setText(movimento.getDescricao());

            if (compraCancelada.equals(n)){
                cancelado.setText(nao);
            }else if (compraCancelada.equals(s)){
                cancelado.setText(sim);
            }
        }
    }
}
