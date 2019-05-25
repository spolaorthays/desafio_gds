package com.gds.desafioandroidgds.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gds.desafioandroidgds.model.Cartao;
import com.gds.desafioandroidgds.model.Movimento;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "desafio_gds.db";

    //Tabela Clientes
    public static final String TABLE_CARTAO = "cartao";
    public static final String COLUMN_COD_CARTAO = "codCartao";
    public static final String COLUMN_NOME_EMPRESA = "nomeEmpresa";
    public static final String COLUMN_SALDO = "saldo";
    public static final String COLUMN_DT_ULTIMO_UPDATE = "dtUltimoUpdate";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_COD_EMPRESA = "codEmpresa";

    //Tabela Movimentos
    public static final String TABLE_MOVIMENTO = "cliente";
    public static final String COLUMN_NUM_ID = "numId";
    public static final String COLUMN_COD_PRODUTO = "codProduto";
    public static final String COLUMN_QUANTIDADE = "quantidade";
    public static final String COLUMN_VL_TOTAL = "vlTotal";
    public static final String COLUMN_DT_OCORRENCIA = "dtOcorrencia";
    public static final String COLUMN_OPERACAO_DC = "operacaoDC";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_CANCELADO = "cancelado";
    public static final String COLUMN_FK_COD_EMPRESA = "codEmpresa";


    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CARTAO + "(" +
                COLUMN_COD_CARTAO + " INTEGER PRIMARY KEY, " +
                COLUMN_NOME_EMPRESA + " TEXT , " +
                COLUMN_SALDO + " TEXT , " +
                COLUMN_DT_ULTIMO_UPDATE + " TEXT , " +
                COLUMN_NOME + " TEXT , " +
                COLUMN_COD_EMPRESA + " TEXT " + ");";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_MOVIMENTO + "(" +
                COLUMN_NUM_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_COD_PRODUTO + " TEXT , " +
                COLUMN_QUANTIDADE + " TEXT , " +
                COLUMN_VL_TOTAL + " TEXT , " +
                COLUMN_DT_OCORRENCIA + " TEXT , " +
                COLUMN_OPERACAO_DC + " TEXT , " +
                COLUMN_DESCRICAO + " TEXT , " +
                COLUMN_CANCELADO + " TEXT , " +
                COLUMN_FK_COD_EMPRESA + " INTEGER, "
                + " FOREIGN KEY ("+COLUMN_FK_COD_EMPRESA+") REFERENCES "+TABLE_CARTAO+"("+COLUMN_COD_EMPRESA+"));";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Comandos caso necessitar alterar a tabela ou dropar
        //db.execSQL("ALTER TABLE " + TABLE_CARTAO + " ADD " + COLUMN_X + " TEXT ;");
        //db.execSQL(" DROP TABLE IF EXISTS " + TABLE_CARTAO);
        //db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MOVIMENTO);
        //onCreate(db);
    }

    //Métodos de adicionar
    public long adicionarCartao(Cartao cartao){
        ContentValues values = new ContentValues();
        if (cartao.getCodCartao() != -1)
            values.put(COLUMN_COD_CARTAO, cartao.getCodCartao());
        if (cartao.getNomeEmpresa() != null)
            values.put(COLUMN_NOME_EMPRESA, cartao.getNomeEmpresa());
        if (cartao.getSaldo() != null)
            values.put(COLUMN_SALDO, cartao.getSaldo());
        if (cartao.getDtUltimoUpdate() != null)
            values.put(COLUMN_DT_ULTIMO_UPDATE, cartao.getDtUltimoUpdate());
        if (cartao.getNome() != null)
            values.put(COLUMN_NOME, cartao.getNome());
        if (cartao.getCodEmpresa() != -1)
            values.put(COLUMN_COD_EMPRESA, cartao.getCodEmpresa());
//        if (cartao.getMovimentos() != null)
//            values.put(COLUMN_MOVIMENTOS, cartao.getMovimentos());

        SQLiteDatabase db = getWritableDatabase();
        long insertedId = db.insert(TABLE_CARTAO, null, values);
        db.close();
        return insertedId;
    }

    public long adicionarMovimentos(Movimento movimento){
        ContentValues values = new ContentValues();
        if (movimento.getNumId() != null)
            values.put(COLUMN_NUM_ID, movimento.getNumId());
        if (movimento.getCodProduto() != null)
            values.put(COLUMN_COD_PRODUTO, movimento.getCodProduto());
        if (movimento.getQuantidade() != null)
            values.put(COLUMN_QUANTIDADE, movimento.getQuantidade());
        if (movimento.getVlTotal() != null)
            values.put(COLUMN_VL_TOTAL, movimento.getVlTotal());
        if (movimento.getDtOcorrencia() != null)
            values.put(COLUMN_DT_OCORRENCIA, movimento.getDtOcorrencia());
        if (movimento.getOperacaoDC() != null)
            values.put(COLUMN_OPERACAO_DC, movimento.getOperacaoDC());
        if (movimento.getDescricao() != null)
            values.put(COLUMN_DESCRICAO, movimento.getDescricao());
        if (movimento.getCancelado() != null)
            values.put(COLUMN_CANCELADO, movimento.getCancelado());
        if (movimento.getFkCodEmpresa() != -1)
            values.put(COLUMN_FK_COD_EMPRESA, movimento.getFkCodEmpresa());

        SQLiteDatabase db = getWritableDatabase();
        long insertedId = db.insert(TABLE_MOVIMENTO, null, values);
        db.close();
        return insertedId;
    }

    //Métodos de select
    public Cartao getCartao (int codCartao){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CARTAO + " WHERE " + COLUMN_COD_CARTAO + "=" + codCartao;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Cartao cartao = new Cartao();
        while (!cursor.isAfterLast()){
            if (cursor.getString(cursor.getColumnIndex(COLUMN_COD_CARTAO)) != null){
                cartao.setNomeEmpresa(cursor.getString(cursor.getColumnIndex(COLUMN_NOME_EMPRESA)));
                cartao.setSaldo(cursor.getString(cursor.getColumnIndex(COLUMN_SALDO)));
                cartao.setDtUltimoUpdate(cursor.getString(cursor.getColumnIndex(COLUMN_DT_ULTIMO_UPDATE)));
                cartao.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_NOME)));
                cartao.setCodEmpresa(cursor.getInt(cursor.getColumnIndex(COLUMN_COD_EMPRESA)));
            }
            cursor.moveToNext();
        }
        db.close();
        return cartao;
    }

    public List<Cartao> getAllCartoes(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CARTAO + " WHERE 1 ORDER BY "+ COLUMN_NOME_EMPRESA + " COLLATE NOCASE ASC ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        List<Cartao> cartaoList = new ArrayList<>();

        while (!cursor.isAfterLast()){
            Cartao cartao = new Cartao();
            if (cursor.getString(cursor.getColumnIndex(COLUMN_COD_CARTAO)) != null){
                cartao.setNomeEmpresa(cursor.getString(cursor.getColumnIndex(COLUMN_NOME_EMPRESA)));
                cartao.setSaldo(cursor.getString(cursor.getColumnIndex(COLUMN_SALDO)));
                cartao.setDtUltimoUpdate(cursor.getString(cursor.getColumnIndex(COLUMN_DT_ULTIMO_UPDATE)));
                cartao.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_NOME)));
                cartao.setCodEmpresa(cursor.getInt(cursor.getColumnIndex(COLUMN_COD_EMPRESA)));
            }
            cartaoList.add(cartao);
            cursor.moveToNext();
        }
        db.close();
        return cartaoList;
    }

    public Movimento getMovimento (int numId){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIMENTO + " WHERE " + COLUMN_NUM_ID + "=" + numId;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Movimento movimento = new Movimento();
        while (!cursor.isAfterLast()){
            if (cursor.getString(cursor.getColumnIndex(COLUMN_NUM_ID)) != null){
                movimento.setCodProduto(cursor.getString(cursor.getColumnIndex(COLUMN_COD_PRODUTO)));
                movimento.setQuantidade(cursor.getString(cursor.getColumnIndex(COLUMN_QUANTIDADE)));
                movimento.setVlTotal(cursor.getString(cursor.getColumnIndex(COLUMN_VL_TOTAL)));
                movimento.setDtOcorrencia(cursor.getString(cursor.getColumnIndex(COLUMN_DT_OCORRENCIA)));
                movimento.setOperacaoDC(cursor.getString(cursor.getColumnIndex(COLUMN_OPERACAO_DC)));
                movimento.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO)));
                movimento.setCancelado(cursor.getString(cursor.getColumnIndex(COLUMN_CANCELADO)));
            }
            cursor.moveToNext();
        }
        db.close();
        return movimento;
    }

    public List<Movimento> getAllMovimentos(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIMENTO + " WHERE 1 ORDER BY "+ COLUMN_FK_COD_EMPRESA + " COLLATE NOCASE ASC ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        List<Movimento> movimentoList = new ArrayList<>();

        while (!cursor.isAfterLast()){
            Movimento movimento = new Movimento();
            if (cursor.getString(cursor.getColumnIndex(COLUMN_NUM_ID)) != null){
                movimento.setCodProduto(cursor.getString(cursor.getColumnIndex(COLUMN_COD_PRODUTO)));
                movimento.setQuantidade(cursor.getString(cursor.getColumnIndex(COLUMN_QUANTIDADE)));
                movimento.setVlTotal(cursor.getString(cursor.getColumnIndex(COLUMN_VL_TOTAL)));
                movimento.setDtOcorrencia(cursor.getString(cursor.getColumnIndex(COLUMN_DT_OCORRENCIA)));
                movimento.setOperacaoDC(cursor.getString(cursor.getColumnIndex(COLUMN_OPERACAO_DC)));
                movimento.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO)));
                movimento.setCancelado(cursor.getString(cursor.getColumnIndex(COLUMN_CANCELADO)));
            }
            movimentoList.add(movimento);
            cursor.moveToNext();
        }
        db.close();
        return movimentoList;
    }

    //Métodos de delete
    public void deleteMovimento(int numId){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MOVIMENTO + " WHERE " + COLUMN_NUM_ID + " =\"" + numId + "\";");
        db.close();
    }

    public void deleteCartao(int codCartao){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CARTAO + " WHERE " + COLUMN_COD_CARTAO + " =\"" + codCartao + "\";");
        db.execSQL("DELETE FROM " + TABLE_MOVIMENTO + " WHERE " + COLUMN_FK_COD_EMPRESA + "=\"" + codCartao + "\";");
        db.close();
    }
}
