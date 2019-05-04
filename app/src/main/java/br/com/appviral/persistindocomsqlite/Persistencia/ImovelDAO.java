package br.com.appviral.persistindocomsqlite.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.appviral.persistindocomsqlite.Entidade.Imovel;

/**
 * Jefferson Borges - 2019
 */

public class ImovelDAO {

    Context context;
    DBSQLite dbsqLite;
    public static ArrayList<Imovel> listaImovels;

    public ImovelDAO(Context context) {
        this.context = context;
        dbsqLite = new DBSQLite(context);
        listar();
    }

    public boolean inserir(Imovel imovel) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Imovel.CAMPO_ENDERECO, imovel.endereco);
        values.put(Imovel.CAMPO_NUMERO, imovel.numero);
        values.put(Imovel.CAMPO_BAIRRO, imovel.bairro);
        values.put(Imovel.CAMPO_CIDADE, imovel.cidade);
        values.put(Imovel.CAMPO_ESTADO, imovel.estado);
        values.put(Imovel.CAMPO_IMOBILIARIA, imovel.imobiliaria);
        values.put(Imovel.CAMPO_TELEFONE, imovel.telefone);
        values.put(Imovel.CAMPO_GEOLOCALIZACAO, imovel.geolocalizacao);

        Long id = db.insert(Imovel.TABELA, null, values);

        db.close();
        if (id > 0) {
            imovel.id = id;
            listaImovels.add(imovel);
        }
        return id > 0;
    }

    public boolean alterar(Imovel imovel) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Imovel.CAMPO_ENDERECO, imovel.endereco);
        values.put(Imovel.CAMPO_NUMERO, imovel.numero);
        values.put(Imovel.CAMPO_BAIRRO, imovel.bairro);
        values.put(Imovel.CAMPO_CIDADE, imovel.cidade);
        values.put(Imovel.CAMPO_ESTADO, imovel.estado);
        values.put(Imovel.CAMPO_IMOBILIARIA, imovel.imobiliaria);
        values.put(Imovel.CAMPO_TELEFONE, imovel.telefone);
        values.put(Imovel.CAMPO_GEOLOCALIZACAO, imovel.geolocalizacao);
        String whare = Imovel.CAMPO_ID + " = ?";

        int id = db.update(Imovel.TABELA, values, whare, new String[]{String.valueOf(imovel.id)});
        db.close();
        if (id > 0)
            listaImovels.set(listaImovels.indexOf(localizaImovelPorId(imovel.id)), imovel);
        return id > 0;
    }

    public boolean excluir(Imovel imovel) {
        return excluir(imovel.id);
    }

    public boolean excluir(Long id) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();
        String whare = Imovel.CAMPO_ID + " = ?";
        int ret = db.delete(Imovel.TABELA, whare, new String[]{String.valueOf(id)});
        db.close();

        if (ret > 0)
            listaImovels.remove(localizaImovelPorId(id));

        return ret > 0;
    }

    public void listar() {
        SQLiteDatabase db = dbsqLite.getReadableDatabase();
        listaImovels = new ArrayList<>();

        String selectQuery = "SELECT  " +
                Imovel.CAMPO_ID + ","
                + Imovel.CAMPO_ENDERECO + " TEXT, "
                + Imovel.CAMPO_NUMERO + " TEXT, "
                + Imovel.CAMPO_BAIRRO + " TEXT, "
                + Imovel.CAMPO_CIDADE + " TEXT, "
                + Imovel.CAMPO_ESTADO + " TEXT, "
                + Imovel.CAMPO_IMOBILIARIA + " TEXT, "
                + Imovel.CAMPO_TELEFONE + " TEXT, "
                + Imovel.CAMPO_GEOLOCALIZACAO + " TEXT " +
                " FROM " + Imovel.TABELA;

        Cursor cursor = db.rawQuery(selectQuery, null);
        Imovel umImovel;

        if (cursor.moveToFirst()) {
            do {
                umImovel = new Imovel();
                umImovel.id = cursor.getLong(0);
                umImovel.endereco = cursor.getString(1);
                umImovel.numero = cursor.getString(2);
                umImovel.bairro = cursor.getString(3);
                umImovel.cidade = cursor.getString(4);
                umImovel.estado = cursor.getString(5);
                umImovel.imobiliaria = cursor.getString(6);
                umImovel.telefone = cursor.getString(7);
                umImovel.geolocalizacao = cursor.getString(8);

                listaImovels.add(umImovel);

            } while (cursor.moveToNext());
            db.close();
        }
    }

    public Imovel localizaImovelPorId(long id) {
        for (Imovel umaImovel : listaImovels)
            if (umaImovel.id == id)
                return umaImovel;
        return null;
    }
}
