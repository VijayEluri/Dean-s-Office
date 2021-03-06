package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.orders.OrderDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.orders.OrderQueueWindow;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.OrderController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.OrderModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/9/11
 * Time: 1:07 PM
 */
public class OrderView extends View {

    private OrderQueueWindow orderQueueWindow;
    private OrderDialog orderDialog;

    public OrderView(OrderController controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(StudentEvents.OrderQueueCall)) {
            onOrderQueueCall();
        } else if (eventType.equals(StudentEvents.AddNewOrderCall)) {
            onAddNewOrderCall();
        } else if (eventType.equals(StudentEvents.EditOrderCall)) {
            onEditOrderCall(event);
        }
    }

    private void onOrderQueueCall() {
        if (orderQueueWindow == null) {
            orderQueueWindow = new OrderQueueWindow();
        }
        orderQueueWindow.show();
    }

    private void onAddNewOrderCall() {
        if (orderDialog == null) {
            orderDialog = new OrderDialog();
        }
        orderDialog.showNewOrderDialog();
    }

    private void onEditOrderCall(AppEvent event) {
        if (orderDialog == null) {
            orderDialog = new OrderDialog();
        }
        orderDialog.showEditOrderDialog( event.<OrderModel>getData() );
    }
}
