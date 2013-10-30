package org.alexys.objetos;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Alexys
 */
public class MiObjetoEncoder implements Encoder.TextStream<MiObjeto>{

    @Override
    public void encode(MiObjeto miObjeto, Writer writer) throws EncodeException, IOException {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("tipo", "nuevo")
                .add("nombre", miObjeto.getNombre())
                .add("edad", miObjeto.getEdad())
                .build();
        try(JsonWriter jsonWriter = Json.createWriter(writer)){
            jsonWriter.writeObject(jsonObject);
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
