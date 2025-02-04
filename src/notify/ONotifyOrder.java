package notify;

import java.util.ArrayList;
import java.util.List;

public class ONotifyOrder {

    //-- VARIABLES --\\

    private List<INotify> notifications = new ArrayList<INotify>();

    //-- METHODS --\\

    public void addObserver(INotify o) {
        this.notifications.add(o);
    }

    public void removeObserver(INotify o) {
        this.notifications.remove(o);
    }

    public void notify(String message) {
        for (INotify n : this.notifications) {
            n.update(message);
        }
    }

}
