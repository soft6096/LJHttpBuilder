package com.capsule.baseframe.http.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kakalee on 15/7/30.
 */
public class Params implements Serializable {

    private static final long serialVersionUID = 1677274487904495553L;

    protected Map<String, String> map;

    public Params() {
        map = new HashMap<String, String>();
    }

    public void set(String name, String value) {
        map.put(name, value);
    }

    public void get(String name) {
        map.get(name);
    }

    public Map<String, String> getMap() {
        return map;
    }
}
