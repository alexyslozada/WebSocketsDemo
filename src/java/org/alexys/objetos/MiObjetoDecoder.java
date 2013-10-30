/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.alexys.objetos;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Alexys
 */
public class MiObjetoDecoder implements Decoder.TextStream<MiObjeto> {

    @Override
    public MiObjeto decode(Reader reader) throws DecodeException, IOException {
        MiObjeto miObjeto = new MiObjeto();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject jsonObject = jsonReader.readObject();
            miObjeto.setNombre(jsonObject.getString("nombre"));
            miObjeto.setEdad(jsonObject.getInt("edad"));
        }
        return miObjeto;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
