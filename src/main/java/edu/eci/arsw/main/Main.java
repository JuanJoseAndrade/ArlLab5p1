/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.main;

import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.persistence.BlueprintNotFoundException;
import edu.eci.arsw.persistence.BlueprintPersistenceException;
import edu.eci.arsw.persistenceimpl.BlueprintsPersistence;
import edu.eci.arsw.persistenceimpl.InMemoryBlueprintPersistence;
import edu.eci.arsw.services.BlueprintsServices;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan
 */
@Service
public class Main {
   
static BlueprintsServices servicio;   
    public static void main(String args[]) throws BlueprintPersistenceException, BlueprintNotFoundException{
         ApplicationContext blueprintbeans = new ClassPathXmlApplicationContext("applicationContext.xml");
         servicio=blueprintbeans.getBean(BlueprintsServices.class);
         registrarPlanos();
         consultarPlanos();
         consultarPlanosDeAutor("mack");
         consultarPlanosDeAutor("jhon");
         consultarPuntosDePlano(servicio.getBlueprint("mack", "plano1"));
         consultarPuntosDePlano(servicio.getBlueprint("mack", "plano2"));
         consultarPuntosDePlano(servicio.getBlueprint("jhon", "plano1"));
         consultarPuntosDePlano(servicio.getBlueprint("jhon", "plano2"));
    }
    
   
    
    public static void registrarPlanos() throws BlueprintPersistenceException{
        Point[] pts0=new Point[]{new Point(30, 40),new Point(30, 40),new Point(30, 40),new Point(15, 15),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp0=new Blueprint("mack", "plano1",pts0);
        servicio.useFilter(bp0);
        servicio.addNewBlueprint(bp0);
        Point[] pts3=new Point[]{new Point(60, 40),new Point(60, 40),new Point(60, 40),new Point(15, 15),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp3=new Blueprint("mack", "plano2",pts3);
        servicio.useFilter(bp3);
        servicio.addNewBlueprint(bp3);
        Point[] pts1=new Point[]{new Point(67, 89),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp1=new Blueprint("jhon", "plano1",pts1);
        servicio.useFilter(bp1);
        servicio.addNewBlueprint(bp1);
        Point[] pts2=new Point[]{new Point(87, 109),new Point(87, 109),new Point(87, 109),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp2=new Blueprint("jhon", "plano2",pts2);
        servicio.useFilter(bp2);
        servicio.addNewBlueprint(bp2);
    }
    public static void consultarPlanos() throws BlueprintNotFoundException{
        System.out.println(" Todos los Planos de todos los autores: \n");
        List<Blueprint> planos= servicio.getAllBlueprints();
        for(int i=0;i<planos.size();i++){
            System.out.println("Blueprint of: "+planos.get(i).getAuthor()+", called: "+planos.get(i).getName());
        }
        
    }
    public static void consultarPlano(String author,String name) throws BlueprintNotFoundException{
        Blueprint bp0=servicio.getBlueprint(author, name);
        System.out.println("Blueprint of: "+bp0.getAuthor()+", called: "+bp0.getName());
    }
    public static void consultarPlanosDeAutor(String autor) throws BlueprintPersistenceException, BlueprintNotFoundException{

        System.out.println("Planos por autor:"+ autor +"\n");
        List<Blueprint> planosAutor=servicio.getBlueprintsByAuthor(autor);
        for(Blueprint bp:planosAutor){
            System.out.println("Blueprint of: "+bp.getAuthor()+", called: "+bp.getName());
        }
    }
    public static void consultarPuntosDePlano(Blueprint bp) throws BlueprintNotFoundException{
        System.out.println("Puntos en el plano de: \n"+bp.getAuthor()+" en el plano "+bp.getName());
        List<Point> points=bp.getPoints();
        System.out.println("Points:");
        for (Point p:points){
            System.out.println("X: "+p.getX()+" Y: "+p.getY());
        }
    }    
}
