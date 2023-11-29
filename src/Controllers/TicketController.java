package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

public class TicketController {
    Gui mainFrame;
    DBController db;
    CancelFlightPanel cancelFlightPanel;

    public TicketController (Gui mainFrame, DBController db) {
        this.mainFrame = mainFrame;
        this.db = db;
    }

    public void setCancelFlightPanel(CancelFlightPanel cancelFlightPanel){
        this.cancelFlightPanel = cancelFlightPanel;
    }

    public boolean cancelFlight(String ticketNum, String flightID, String seatID) {
        ArrayList<Ticket> tmp = Ticket.getTickets();
        
        return false;
    }
}