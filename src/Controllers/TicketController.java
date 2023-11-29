package src.Controllers;
 
import java.util.ArrayList;
import src.Domain.*;
import src.Presentation.*;

public class TicketController {
    private Gui mainFrame;
    private DBController db = DBController.getOnlyInstance();
    private CancelFlightPanel cancelFlightPanel;

    public TicketController (Gui mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setCancelFlightPanel(CancelFlightPanel cancelFlightPanel){
        this.cancelFlightPanel = cancelFlightPanel;
    }

    public boolean cancelFlight(String ticketNum, String flightID, String seatID) {
        ArrayList<Ticket> tmp = Ticket.getTickets(); //
        return false;
    }
}