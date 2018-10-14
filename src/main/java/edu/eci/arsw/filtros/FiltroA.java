/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.filtros;
import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.persistenceimpl.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
/**
 *
 * @author juan
 */
@Service("FiltroA")
public class FiltroA implements Filtro {
    @Override
    public void filtrar(Blueprint plano){
        Map<Tuple<String,String>,Point> newpoints=new HashMap<>();
        //ArrayList<Point> newpoints= new ArrayList();
        for (Point p:plano.getPoints()){
            newpoints.put(new Tuple<>(String.valueOf(p.getX()),String.valueOf(p.getY())), p);
        }
        List<Point> allpoints=new ArrayList();
        for (Map.Entry<Tuple<String, String>,Point> tupla:newpoints.entrySet()) {            
                allpoints.add(tupla.getValue());
        }
        plano.setPoints(allpoints);
        
    }
}
