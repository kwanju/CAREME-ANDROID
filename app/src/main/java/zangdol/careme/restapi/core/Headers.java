package zangdol.careme.restapi.core;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

import java.util.ArrayList;
import java.util.List;

public abstract class Headers {

    private List<Header> headers;

    public abstract void setHeaders();

    public Headers() {
        headers = new ArrayList<>();
        setHeaders();
    }

    public void addHeader(final String key, final String value) {
        headers.add(new Header() {
            @Override
            public String getName() {
                return key;
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public HeaderElement[] getElements() throws ParseException {
                return new HeaderElement[0];
            }
        });

    }
    public Header[] getHeaders(){
        int size = headers.size();
        Header[] result = new Header[size];

        for(int i=0;i<size;i++)
            result[i] = headers.get(i);

        return result;
    }
}
