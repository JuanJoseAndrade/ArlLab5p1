/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.filtros.FiltroA;
import edu.eci.arsw.filtros.FiltroB;
import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.persistence.BlueprintNotFoundException;
import edu.eci.arsw.persistenceimpl.InMemoryBlueprintPersistence;
import edu.eci.arsw.services.BlueprintsServices;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *
 * @author juan
 */
public class FilterTest {
    @Test
    public void FiltrosTest() {
        FiltroA filtroa=new FiltroA();
        FiltroB filtrob=new FiltroB();
        Point[] pts0=new Point[]{new Point(30, 40),new Point(30, 40),new Point(30, 40),new Point(15, 15),new Point(15, 15),new Point(25, 25)};
        Blueprint bp0=new Blueprint("mack", "plano1",pts0);
        filtroa.filtrar(bp0);
        assertEquals("FiltroA isn't working",bp0.getPoints().size(), 3);
        filtrob.filtrar(bp0);
        assertEquals("FiltroA isn't working",bp0.getPoints().size(), 2);

    }
}
