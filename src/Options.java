import java.util.ArrayList;
import java.util.List;

public class Options {
    private final List<CurrencyCode[]> options = new ArrayList<>();

    public Options() {
        CurrencyCode[] currencyCodes = CurrencyCode.values();
        for (int i = 0; i < currencyCodes.length; i++) {
            for (int j = 0; j < currencyCodes.length; j++) {
                if (i != j) {
                    CurrencyCode[] option = new CurrencyCode[]{currencyCodes[i], currencyCodes[j]};
                    options.add(option);
                }
            }
        }
    }

    public int size() {
        return options.size();
    }

    public CurrencyCode[] get(int numberChoise) {
        return options.get(numberChoise);
    }
}
