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
                    new Auto("abc-123", "Opel", "corsa", "feher", "Budapest"),
                    new Auto("bcg-234", "Volvo", "c70", "piros", "Debrecen"),
                    new Auto("plk-789", "Bmw", "Isetta", "kek", "Győr"),
                    new Auto("xyz-987", "Mercedes", "Rotschwein", "zold", "Debrecen")
            ));

    /**
     * Parkolók listája
     */
    private ArrayList<Parkolo> parkoloAutoListB
            = new ArrayList<Parkolo>(Arrays.asList(
                    new Parkolo("Debrecen", 15, new ArrayList<Auto>(Arrays.asList(new Auto("abc-123", "opel", "corsa", "feher")))),
                    new Parkolo("Budapest", 25, new ArrayList<Auto>(Arrays.asList(new Auto("bcg-234", "volvo", "850R", "piros")))),
                    new Parkolo("Győr", 10, new ArrayList<Auto>(Arrays.asList(new Auto("plk-789", "bayerisch motoren werke", "fk-1", "kek"))))
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
