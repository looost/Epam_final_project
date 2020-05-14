package by.training.tag;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VendorMap {
    private HashMap<String, Double> map = new HashMap<>();

    {
        this.map.put("IBM", 26.2E9);
        this.map.put("Oracle", 35.8E9);
        this.map.put("SAP", 10.01E9);
        this.map.put("Google", 42.9E9);
    }

    private Iterator<Map.Entry<String, Double>> it = map.entrySet().iterator();

    public int getSize() {
        return map.size();
    }

    public String getRevenue() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        if (it.hasNext()) {
            Map.Entry<String, Double> m = it.next();
            return nf.format(m.getValue()) + " " + m.getKey();
        } else {
            return null;
        }
    }
}