package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Description: Klasa gniazda obsługi obiektów - zgłoszeń 
 */

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;



public class Smo extends BasicSimObj
{
    private final LinkedList <Klient> kolejka;
    private final LinkedList <Klient2> kolejka_2;


	private boolean wolne = true;
    private boolean wolne_2 = true;

    public MonitoredVar MVczasy_obslugi;
    public MonitoredVar MVczasy_oczekiwania;
    public MonitoredVar MVdlKolejki;
    public MonitoredVar MVutraconeZgl;

    public MonitoredVar MVczasy_obslugi_2;
    public MonitoredVar MVczasy_oczekiwania_2;
    public MonitoredVar MVdlKolejki_2;
    public MonitoredVar MVutraconeZgl_2;

	
    public Smo() throws SimControlException
    {
        //Utworzenie kolejek dla gniazda 1 i 2
        kolejka = new LinkedList<>();
        kolejka_2 = new LinkedList<>();
        // Deklaracja zmiennych
        MVczasy_obslugi = new MonitoredVar();
        MVczasy_oczekiwania = new MonitoredVar();
        MVdlKolejki = new MonitoredVar();
        MVutraconeZgl = new MonitoredVar();
        // Deklaracaja zmiennych dla kolejki nr 2
        MVczasy_obslugi_2 = new MonitoredVar();
        MVczasy_oczekiwania_2 = new MonitoredVar();
        MVdlKolejki_2 = new MonitoredVar();
        MVutraconeZgl_2 = new MonitoredVar();

    }

    // Wstawienie zgłoszenia do kolejki
    public int dodaj(Klient zgl)
    {
        kolejka.add(zgl);
        MVdlKolejki.setValue(kolejka.size());
        return kolejka.size();
    }

    public int dodaj_2(Klient2 zgl)
    {
        kolejka_2.add(zgl);
        MVdlKolejki_2.setValue(kolejka_2.size());
        return kolejka_2.size();
    }


    // Pobranie zgłoszenia z kolejki
    public Klient usun()
    {
    	Klient zgl = (Klient) kolejka.removeFirst();
        MVdlKolejki.setValue(kolejka.size());
        return zgl;
    }

    public Klient2 usun_2()
    {
        Klient2 zgl = (Klient2) kolejka_2.removeFirst();
        MVdlKolejki_2.setValue(kolejka_2.size());
        return zgl;
    }


    // Pobranie zgłoszenia z kolejki
    public boolean usunWskazany(Klient zgl)
    {
    	Boolean b= kolejka.remove(zgl);
        MVdlKolejki.setValue(kolejka.size());
        return b;
    }
    
    public int liczbaZgl()
    {
        return kolejka.size();
    }

    public int liczbaZgl_2()
    {
        return kolejka_2.size();
    }


	public boolean isWolne() {
		return wolne;
	}

    public boolean isWolne_2() {
        return wolne_2;
    }


	public void setWolne(boolean wolne) {
		this.wolne = wolne;
	}

    public void setWolne_2(boolean wolne) {
        this.wolne_2 = wolne;
    }

	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}