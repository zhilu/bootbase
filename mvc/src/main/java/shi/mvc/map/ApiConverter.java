package shi.mvc.map;

import org.apache.commons.lang3.StringUtils;

public class ApiConverter {
    public static ApiItem convert(String api) {
        ApiItem apiItem = new ApiItem();
        if (StringUtils.isBlank(api)) {
            return apiItem;
        }

        String[] cells = StringUtils.split(api, ".");
        apiItem.setHigh(Integer.parseInt(cells[0]));
        if (cells.length > 1) {
            apiItem.setMid(Integer.parseInt(cells[1]));
        }

        if (cells.length > 2) {
            apiItem.setLow(Integer.parseInt(cells[2]));
        }
        return apiItem;
    }
}


