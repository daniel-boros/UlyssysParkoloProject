package MBeans;

import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.Stateless;
import parkoloPackageDTO.Auto;
import parkoloPackageDTO.Parkolo;
import MBeans.ParkoloMBean;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

/**
 * Stateless EJB, amelye osztály tartalmazza "adatbázis szerűen" az előre felvett adatokat.
 * @author Daniel
 */
@Stateless
public class DataBase {

    /**
     * parkoló autók listája
     */
    private ArrayList<Auto> autoListB
            = new ArrayList<Auto>(Arrays.asList(
                    new Auto("HDF-483", "Opel", "Corsa", "fehér", "Budapest"),
                    new Auto("PNR-782", "Volvo", "c70", "piros", "Debrecen"),
                    new Auto("AIY-912", "Bmw", "e24", "kék", "Győr"),
                    new Auto("IJY-633", "Mercedes", "w124", "zöld", "Debrecen")
            ));

    /**
     * Parkolók listája
     */
    private ArrayList<Parkolo> parkoloAutoListB
            = new ArrayList<Parkolo>(Arrays.asList(
                    new Parkolo("Debrecen", 3),
                    new Parkolo("Budapest", 25),
                    new Parkolo("Győr", 10)
            ));

    
    public ArrayList<Auto> getAutoListB() {
        return autoListB;
    }

    public void setAutoListB(ArrayList<Auto> autoListB) {
        this.autoListB = autoListB;
    }

    public ArrayList<Parkolo> getParkoloAutoListB() {
        return parkoloAutoListB;
    }

    public void setParkoloAutoListB(ArrayList<Parkolo> parkoloAutoListB) {
        this.parkoloAutoListB = parkoloAutoListB;
    }

}
