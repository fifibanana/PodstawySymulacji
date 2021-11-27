package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie początkowe aktywności gniazda obsługi. Rozpoczyna obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpocznijObsluge2 extends BasicSimEvent<Smo, Klient>
{
    private final RNGenerator generator;

    public RozpocznijObsluge2(Smo parent) throws SimControlException
    {
    	super(parent);
    	generator = new RNGenerator();
    }

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent = getSimObj();

        if (smoParent.liczbaZgl_2() > 0)
        {
        	// Zablokuj gniazdo
        	smoParent.setWolne_2(false);
        	// Pobierz klienta
        	Klient2 zgl = smoParent.usun_2();
        	// Wygeneruj czas obsługi
            double czasObslugi;
            do {
            	czasObslugi = generator.normal(7, 0.5);
            } while (czasObslugi<=0.0);
        	
            // Zapamiętaj dane monitorowane
        	smoParent.MVczasy_obslugi_2.setValue(czasObslugi);
            smoParent.MVczasy_oczekiwania_2.setValue(simTime() - zgl.getCzasPrzybycia());
            System.out.println(simTimeFormatted()+":    SMO2  Początek obsługi zgl. nr: " + zgl.getId());
        	// Zaplanuj koniec obsługi
        	new ZakonczObsluge2(smoParent, czasObslugi, zgl);
        }
		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Klient getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}