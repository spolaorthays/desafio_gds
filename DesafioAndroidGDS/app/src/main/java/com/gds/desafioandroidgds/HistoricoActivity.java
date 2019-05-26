package com.gds.desafioandroidgds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gds.desafioandroidgds.adapter.RecyclerHistoricoAdapter;
import com.gds.desafioandroidgds.database.MyDbHandler;
import com.gds.desafioandroidgds.interfaces.OnCardClick;
import com.gds.desafioandroidgds.model.Cartao;

public class HistoricoActivity extends AppCompatActivity implements OnCardClick {

    private RecyclerView recyclerView;
    private RecyclerHistoricoAdapter adapter;
    private MyDbHandler dbHandler;

    private Cartao cartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        dbHandler = new MyDbHandler(this, null, null,1);

        setupRecycler();
    }

    private void setupRecycler() {
        recyclerView = findViewById(R.id.recycler_historico_id);

        adapter = new RecyclerHistoricoAdapter(dbHandler.getAllCartoes(), dbHandler, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onCardClicado(Cartao cartao) {
        //Aqui eu coloco um bungle com intent para passar o id para a próxima activity.
    }
}
