package com.regionalTourism.commons;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GeneralUtils {

    static public ResponseEntity createOkResponse(Object bodyObj) {
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(bodyObj);
    }
    static public ResponseEntity createErrorResponse(String msg) {
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(msg);
    }

    static public ResponseEntity createForbiddenResponse(String msg) {
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(msg);
    }




    static public JsonObject getErrorResponseObject(String errorMsg) {
        JsonObject result = new JsonObject();
        result.addProperty("msg", errorMsg);
        result.addProperty("status", "ERROR");
        return result;
    }

    static public boolean isJsonNull (JsonElement el){
        return el == null || el.isJsonNull();
    }

    static public int customGSONIndexOf (JsonArray list, String key, String value){
        int result = -1;
        for (int i = 0; i < list.size(); i++) {
            JsonObject elem = list.get(i).getAsJsonObject();
            if (elem.has(key) && elem.get(key).getAsString().equals(value)) {
                return i;
            }
        }
        return result;
    }

    static public int customGSONIndexOf (JsonArray list, String key, int value){
        int result = -1;
        for (int i = 0; i < list.size(); i++) {
            JsonObject elem = list.get(i).getAsJsonObject();
            if (elem.has(key) && elem.get(key).getAsInt() == value) {
                return i;
            }
        }
        return result;
    }

    static public int customGSONIndexOf (JsonArray list, String key, long value){
        int result = -1;
        for (int i = 0; i < list.size(); i++) {
            JsonObject elem = list.get(i).getAsJsonObject();
            if (elem.has(key) && elem.get(key).getAsLong() == value) {
                return i;
            }
        }
        return result;
    }

    static public boolean insertRecursiveHierarchyJsonArray (JsonArray list, String subListKey, String parentKey, String childKey, JsonObject insertValue) {
        if (insertValue == null || list == null || subListKey == null || parentKey == null || childKey == null) {
            return false;
        }
        if (insertValue.get(childKey) == null || insertValue.get(childKey).getAsLong() == 0) {
            list.add(insertValue);
            return true;
        } else {
            for (int i = 0; i < list.size(); i++) {
                JsonObject elem = list.get(i).getAsJsonObject();
                if (elem.get(parentKey).getAsLong() == insertValue.get(childKey).getAsLong()) {
                    elem.get(subListKey).getAsJsonArray().add(insertValue);
                    return true;
                } else {
                    if (insertRecursiveHierarchyJsonArray( elem.get(subListKey).getAsJsonArray(), subListKey, parentKey, childKey, insertValue)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //Email Callbacks

}
