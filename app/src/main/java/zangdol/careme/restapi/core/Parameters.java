package zangdol.careme.restapi.core;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public abstract class Parameters {
    private List<NameValuePair> params;

    public abstract int getNumParams();

    public abstract void setParams();

    public Parameters() {
        params = new ArrayList<>(getNumParams());
        setParams();
    }

    public void addParam(String key, String value) {
        params.add(new BasicNameValuePair(key, value));
    }

    public List<NameValuePair> getParams() {
        return params;
    }
}