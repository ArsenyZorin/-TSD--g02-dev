package lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.coordinator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

public class SwitchButtonAlerts extends Label
{
    private SimpleIntegerProperty switchPos = new SimpleIntegerProperty(1); 

    public SwitchButtonAlerts()
    {
        Button switchBtn = new Button();
        switchBtn.setPrefWidth(50);
        setStyle("-fx-background-color: grey;-fx-text-fill:black;");
        switchBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
            	switch(switchPos.get()){
            	case 1:
            		switchPos.set(2);
            		break;
            	case 2:
            		switchPos.set(3);
            		break;
            	case 3:
            		switchPos.set(1);
            		break;
            	}
            }
        });

        setGraphic(switchBtn);

        switchPos.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1)
            {
                if (t1.intValue() == 1)
                {
                    setContentDisplay(ContentDisplay.LEFT);
                }
                else if (t1.intValue() == 2)
                {
                    setContentDisplay(ContentDisplay.CENTER);
                } 
                else if (t1.intValue() == 3)
                {
                	setContentDisplay(ContentDisplay.RIGHT);
                  
                }
            }
        });

        switchPos.set(1);
    }

    public SimpleIntegerProperty switchPosProperty() { return switchPos; }
}
