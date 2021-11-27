package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters;

public class ZakonczObsluge extends BasicSimEvent<Smo, Klient>
{
    public ZakonczObsluge(Smo parent, double delay, Klient zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }
  
	@Override
	protected void onTermination() throws SimControlException {}

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent = getSimObj();

        System.out.println(simTimeFormatted()+": SMO-Koniec obsługi zgl. nr: " + eventParams.getId());
		smoParent.setWolne(true);



		Klient2 zgl = new Klient2(simTime());
		smoParent.dodaj_2(zgl);

		if (smoParent.liczbaZgl_2() == 1 && smoParent.isWolne_2()){
			new RozpocznijObsluge2(smoParent);
		}



		// Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
        if (smoParent.liczbaZgl() > 0)
        {
        	new RozpocznijObsluge(smoParent);
        }		
	}

	@Override
	public Klient getEventParams() {
		return null;
	}
}