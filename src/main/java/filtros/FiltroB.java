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
@Service("FiltroB")
public class FiltroB implements Filtro{

    @Override
    public Blueprint filtrar(Blueprint plano) {
        int cont=-1;
        for (Point p:plano.getPoints()){
        cont+=1;
            if(cont%2==1){
            plano.removePoint(p);}
        }
        return plano;
    }
    
}
