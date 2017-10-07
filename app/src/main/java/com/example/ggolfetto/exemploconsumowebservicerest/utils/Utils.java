package com.example.ggolfetto.exemploconsumowebservicerest.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.ggolfetto.exemploconsumowebservicerest.model.PessoaObj;
import com.example.ggolfetto.exemploconsumowebservicerest.network.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by ggolfetto on 16/05/2017.
 */

public class Utils {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public PessoaObj parseJson(String json){
        try{
            PessoaObj pessoa = new PessoaObj();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("results");

            JSONObject objArray = array.getJSONObject(0);

            //Nome da pessoa é um objeto, instancia um novo JSONObject
            JSONObject nome = objArray.getJSONObject("name");
            pessoa.setNome(nome.getString("first"));
            pessoa.setSobrenome(nome.getString("last"));

            //Endereco tambem é um Objeto
            JSONObject endereco = objArray.getJSONObject("location");
            pessoa.setEndereco(endereco.getString("street"));
            pessoa.setCidade(endereco.getString("city"));
            pessoa.setEstado(endereco.getString("state"));

            pessoa.setEmail(objArray.getString("email"));
            pessoa.setTelefone(objArray.getString("phone"));

            JSONObject obj = objArray.getJSONObject("login");
            //Atribui os objetos que estão nas camadas mais altas
            pessoa.setUsername(obj.getString("username"));
            pessoa.setSenha(obj.getString("password"));


            //Imagem eh um objeto
            JSONObject foto = objArray.getJSONObject("picture");
            pessoa.setFoto(baixarImagem(foto.getString("large")));

            return pessoa;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Bitmap baixarImagem(String url){
        Bitmap imagem = null;
        try{
            URL endereco;
            InputStream inputStream;
            endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagem;
    }
}


















