/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.filtros;

import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan
 */
@Service("FiltroB")
public class FiltroB implements Filtro{

    @Override
    public void filtrar(Blueprint plano) {
        ArrayList<Point> newpuntos= new ArrayList();
        List<Point> puntos=plano.getPoints();
        for (int i=0;i<plano.getPoints().size();i++){
        if(i%2==0){
            newpuntos.add(puntos.get(i));}
        }
        plano.setPoints(newpuntos);
    }
    
    
}
