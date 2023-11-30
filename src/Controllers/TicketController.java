package src.Controllers;
 
import java.util.ArrayList;
import src.Domain.*;
import src.Presentation.*;

public class TicketController implements Observer{
    private Gui mainFrame;
    private DBController db = DBController.getOnlyInstance();
    private CancelFlightPanel cancelFlightPanel;
    private Subject subject;

    public TicketController (Gui mainFrame, Subject s) { 
        this.mainFrame = mainFrame; 
        subject = s;
        subject.register(this);
    }

    @Override
    public void update(){ db = DBController.getOnlyInstance(); }

    public void setCancelFlightPanel(CancelFlightPanel cancelFlightPanel){ this.cancelFlightPanel = cancelFlightPanel; }
    public boolean cancelFlight(String ticketNum, String flightID, String seatID) {
        // ArrayList<Ticket> tmp = Ticket.getTickets(); // idk what this is doing so i commented it out
        return false;
    }
}