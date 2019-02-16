package todoapp.commons.web.support;

import todoapp.commons.domain.Spreadsheet;
import todoapp.commons.util.StreamUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link Spreadsheet} λ³΄μ‘° ?΄??€
 *
 * @author springrunner.kr@gmail.com
 */
public interface SpreadsheetSupport {

    /**
     * μ£Όμ΄μ§? λͺ¨λΈ(model)?΄?? {@link Spreadsheet}λ₯? μ°Ύμ λ°ν?©??€.
     *
     * @param model ?€?λ§? MVC λͺ¨λΈ
     * @return
     * @throws IllegalArgumentException κ°μ΄ ?κ±°λ, ?κ°? ?΄? λ°κ²¬?λ©? λ°μ
     */
    default Spreadsheet obtainSpreadsheet(Map<String, Object> model) {
        List<Spreadsheet> spreadsheets = model.values()
                                              .stream()
                                              .filter(it -> it instanceof Spreadsheet)
                                              .map(it -> (Spreadsheet) it)
                                              .collect(Collectors.toList());
        if (spreadsheets.isEmpty()) {
            throw new IllegalArgumentException("spreadsheet object inside the model is required");
        }
        if (spreadsheets.size() > 1) {
            throw new IllegalArgumentException("multiple spreadsheet objects were found");
        }
        return spreadsheets.get(0);
    }

    /**
     * μ£Όμ΄μ§? ?? λͺ©λ‘? κ°μ ?°κ²°ν΄? ??? λ¬Έμ?΄λ‘? λ§λ­??€.
     * λ¬Έμ?΄ ?°κ²°μ κ΅¬λΆ λ¬Έμλ₯? ?½??©??€.
     *
     * @param cells     λ°λ³΅?
     * @param delimiter κ΅¬λΆ λ¬Έμ
     * @return
     */
    default String joining(Iterable<Spreadsheet.Cell<?>> cells, CharSequence delimiter) {
        return StreamUtils.createStreamFromIterator(cells.iterator())
                          .map(Spreadsheet.Cell::getValue)
                          .map(String::valueOf)
                          .collect(Collectors.joining(delimiter));
    }

}
