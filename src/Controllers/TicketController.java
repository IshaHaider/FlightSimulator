package src.Controllers;

import src.Domain.*;
import src.Presentation.*;
import java.util.ArrayList;

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
}