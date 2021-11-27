package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczObsluge2 extends BasicSimEvent<Smo, Klient2>
{
    public ZakonczObsluge2(Smo parent, double delay, Klient2 zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }
  
	@Override
	protected void onTermination() throws SimControlException {}

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent = getSimObj();

        System.out.println(simTimeFormatted()+":      2 SMO-Koniec obsługi zgl. nr: " + eventParams.getId());
		smoParent.setWolne_2(true);



		// Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
        if (smoParent.liczbaZgl_2() > 0)
        {
        	new RozpocznijObsluge2(smoParent);
        }		
	}

	@Override
	public Klient2 getEventParams() {
		return null;
	}
}