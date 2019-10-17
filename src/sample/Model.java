package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.shapes.Shape;

public class Model {

    private BooleanProperty enabled = new SimpleBooleanProperty(true);

    private ObservableList<Shape> items = FXCollections.observableArrayList();

    public ObservableList<Shape> getItems() {
        return items;
    }
    
    public boolean isEnabled() {
        return enabled.get();
    }

    public BooleanProperty enabledProperty() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled.set(enabled);
    }

}
