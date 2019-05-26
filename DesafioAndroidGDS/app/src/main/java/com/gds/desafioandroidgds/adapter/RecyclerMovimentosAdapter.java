package com.gds.desafioandroidgds.adapter;

import android.content.Context;
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

public class RecyclerMovimentosAdapter extends RecyclerView.Adapter<RecyclerMovimentosAdapter.ViewHolder> {

    private List<Movimento> movimentoList;
    private MyDbHandler dbHandler;

    public RecyclerMovimentosAdapter(List<Movimento> movimentoList, MyDbHandler dbHandler) {
        this.movimentoList = movimentoList;
        this.dbHandler = dbHandler;
    }

    @NonNull
    @Override
    public RecyclerMovimentosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_movimento_item, viewGroup,false);
        dbHandler = new MyDbHandler(view.getContext(), null, null, 1);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMovimentosAdapter.ViewHolder viewHolder, int i) {
        Movimento movimento = movimentoList.get(i);
        viewHolder.bind(movimento);
    }

    @Override
    public int getItemCount() {
        return movimentoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView quantidade;
        private TextView valorTotal;
        private TextView dataOcorrencia;
        private TextView operacaoDC;
        private TextView descricao;
        private TextView cancelado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            quantidade = itemView.findViewById(R.id.mov_quantidade_id);
            valorTotal = itemView.findViewById(R.id.mov_valortotal_id);
            dataOcorrencia = itemView.findViewById(R.id.mov_dataocorrencia_id);
            operacaoDC = itemView.findViewById(R.id.mov_operacaodc_id);
            descricao = itemView.findViewById(R.id.mov_descricao_id);
            cancelado = itemView.findViewById(R.id.mov_cancelado_id);
        }

        private void bind(Movimento movimento){
            String real = "R$ ";
            String dc = movimento.getOperacaoDC().trim().toLowerCase().toUpperCase().toString();
            String dbt = "D".trim().toLowerCase().toUpperCase().toString();
            String cdt = "C".trim().toLowerCase().toUpperCase().toString();
            String debito = "Débito";
            String credito = "Crédito";
            String compraCancelada = movimento.getCancelado().trim().toLowerCase().toUpperCase().toString();
            String n = "N".trim().toLowerCase().toUpperCase().toString();
            String s = "S".trim().toLowerCase().toUpperCase().toString();
            String nao = "Não";
            String sim = "Sim";

            //String a= movimento.getQuantidade().replaceAll(".","\\,\\");

            quantidade.setText(movimento.getQuantidade());
            valorTotal.setText(String.format("%s%s", real, movimento.getVlTotal()));
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

        private void salvarmovimentosNoDB(){
            Movimento movimento = new Movimento();
            movimento.setQuantidade(quantidade.getEditableText().toString());
            movimento.setVlTotal(valorTotal.getEditableText().toString());
            movimento.setDtOcorrencia(dataOcorrencia.getEditableText().toString());
            movimento.setOperacaoDC(operacaoDC.getEditableText().toString());
            movimento.setDescricao(descricao.getEditableText().toString());
            movimento.setCancelado(cancelado.getEditableText().toString());
            //movimento.setFkCodEmpresa();
        }
    }
}
