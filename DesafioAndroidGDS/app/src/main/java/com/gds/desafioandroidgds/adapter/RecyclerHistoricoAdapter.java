package com.gds.desafioandroidgds.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.gds.desafioandroidgds.R;
import com.gds.desafioandroidgds.database.MyDbHandler;
import com.gds.desafioandroidgds.interfaces.OnCardClick;
import com.gds.desafioandroidgds.model.Cartao;

import java.util.List;

public class RecyclerHistoricoAdapter extends RecyclerView.Adapter<RecyclerHistoricoAdapter.ViewHolder> {

    private List<Cartao> cartaoList;
    private MyDbHandler dbHandler;
    private OnCardClick onCardClick;

    public RecyclerHistoricoAdapter(List<Cartao> cartaoList, MyDbHandler dbHandler, OnCardClick onCardClick) {
        this.cartaoList = cartaoList;
        this.dbHandler = dbHandler;
        this.onCardClick = onCardClick;
    }

    @NonNull
    @Override
    public RecyclerHistoricoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_historico_item, viewGroup,false);
        dbHandler = new MyDbHandler(view.getContext(), null, null, 1);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHistoricoAdapter.ViewHolder viewHolder, int i) {
        Cartao cartao = cartaoList.get(i);
        viewHolder.bind(cartao);
    }

    @Override
    public int getItemCount() {
        return cartaoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nomeCliente;
        private TextView saldo;
        private TextView dataConsulta;
        private Button btDetalhes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCliente = itemView.findViewById(R.id.his_nome_id);
            saldo = itemView.findViewById(R.id.his_saldo_id);
            dataConsulta = itemView.findViewById(R.id.his_data_id);
            btDetalhes = itemView.findViewById(R.id.his_detalhes_bt);
        }

        public void bind(final Cartao cartao){
            String real = "R$ ";

            nomeCliente.setText(cartao.getNome());
            saldo.setText(String.format("%s%s", real, cartao.getSaldo()));
            dataConsulta.setText(cartao.getDtUltimoUpdate());
            btDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCardClick.onCardClicado(cartao);
                }
            });
        }
    }
}
