package com.hoomi.books.lib.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by hoomanostovari on 17/02/2016.
 */
public final class GsonFactory {
    public static Gson getGson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Volume.class, new ModelDeserializer())
                .registerTypeAdapter(VolumeDetails.class, new ModelDeserializer())
                .create();
        return gson;
    }

    private static class ModelDeserializer implements JsonDeserializer<CommonModelInterface> {

        @Override
        public CommonModelInterface deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (typeOfT == Volume.class) {
                return context.deserialize(json, VolumeImp.class);
            } else if (typeOfT == VolumeDetails.class) {
                return context.deserialize(json, VolumeDetailsImp.class);
            }
            return context.deserialize(json, typeOfT);
        }
    }
}
