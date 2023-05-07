package Events;

import java.io.Serial;
import java.util.EventObject;

public class DataSheetChangeEvent extends EventObject {

    @Serial
    private static final long serialVersionUID = 1L;

    public DataSheetChangeEvent(Object source) {
        super(source);
    }
}
