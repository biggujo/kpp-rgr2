package DataSheetGraphBean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

public class DataSheetGraphTableBeanInfo {
    private final PropertyDescriptor[] propertyDescriptors;

    public DataSheetGraphTableBeanInfo() {
        try {
            propertyDescriptors = new PropertyDescriptor[]
                    {new PropertyDescriptor("color", DataSheetGraphPanel.class),
                            new PropertyDescriptor("filled", DataSheetGraphPanel.class),
                            new PropertyDescriptor("deltaX", DataSheetGraphPanel.class),
                            new PropertyDescriptor("deltaY", DataSheetGraphPanel.class)};
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        return propertyDescriptors;
    }
}
