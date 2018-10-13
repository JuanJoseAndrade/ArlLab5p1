/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Service;
/**
 *
 * @author juan
 */
@Service("FiltroA")
public class FiltroA implements Filtro {
    @Override
    public Blueprint filtrar(Blueprint plano){
        for (Point p:plano.getPoints()){
            plano.removeEqualPoints(p);
        }
        return plano;
    }
}
