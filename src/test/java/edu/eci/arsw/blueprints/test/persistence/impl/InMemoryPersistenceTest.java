/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.persistence.BlueprintNotFoundException;
import edu.eci.arsw.persistence.BlueprintPersistenceException;
import edu.eci.arsw.persistenceimpl.InMemoryBlueprintPersistence;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }
    @Test
    public void getBlueprintTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts0=new Point[]{new Point(30, 40),new Point(30, 40),new Point(30, 40),new Point(15, 15),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp0=new Blueprint("mack", "plano1",pts0);
        ibpp.addNewBlueprint(bp0);
        Point[] pts3=new Point[]{new Point(60, 40),new Point(60, 40),new Point(60, 40),new Point(15, 15),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp3=new Blueprint("mack", "plano2",pts3);
        ibpp.addNewBlueprint(bp3);
        Point[] pts1=new Point[]{new Point(67, 89),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp1=new Blueprint("jhon", "plano1",pts1);
        ibpp.addNewBlueprint(bp1);
        Point[] pts2=new Point[]{new Point(87, 109),new Point(87, 109),new Point(87, 109),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp2=new Blueprint("jhon", "plano2",pts2);
        ibpp.addNewBlueprint(bp2);
        try{
            assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp0.getAuthor(), bp0.getName()), bp0);
            assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp3.getAuthor(), bp3.getName()), bp3);
            assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp1.getAuthor(), bp1.getName()), bp1);
            assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp2.getAuthor(), bp2.getName()), bp2);
            
        }
        catch(BlueprintNotFoundException ex){
            fail("Blueprint persistence failed on getting an stored Blueprint");
        }
                
        
    }
    @Test
    public void getBlueprintByAuthorTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts0=new Point[]{new Point(30, 40),new Point(30, 40),new Point(30, 40),new Point(15, 15),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp0=new Blueprint("mack", "plano1",pts0);
        ibpp.addNewBlueprint(bp0);
        Point[] pts3=new Point[]{new Point(60, 40),new Point(60, 40),new Point(60, 40),new Point(15, 15),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp3=new Blueprint("mack", "plano2",pts3);
        ibpp.addNewBlueprint(bp3);
        Point[] pts1=new Point[]{new Point(67, 89),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp1=new Blueprint("jhon", "plano1",pts1);
        ibpp.addNewBlueprint(bp1);
        Point[] pts2=new Point[]{new Point(87, 109),new Point(87, 109),new Point(87, 109),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp2=new Blueprint("jhon", "plano2",pts2);
        ibpp.addNewBlueprint(bp2);
        
        List<Blueprint> planosAutor=ibpp.getBlueprintsByAuthor(bp0.getAuthor());
        List<Blueprint> planosAutor2=ibpp.getBlueprintsByAuthor(bp1.getAuthor());
        int cont=0;
        for (int i=0; i<planosAutor.size();i++){
            if(planosAutor.get(i)==bp0){
                cont+=1;
            }
            if(planosAutor.get(i)==bp3){
                cont+=1;
            }  
        }
        int cont2=0;
        for (int i=0; i<planosAutor2.size();i++){
            if(planosAutor2.get(i)==bp1){
                cont2+=1;
            }
            if(planosAutor2.get(i)==bp2){
                cont2+=1;
            }  
        }
        
        assertEquals("The output from getBlueprintsByAuthor isn't giving a correct result",cont, planosAutor.size());
        assertEquals("The output from getBlueprintsByAuthor isn't giving a correct result",cont2, planosAutor2.size());

                 
        
    }
    @Test
    public void getAllBlueprintsTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts0=new Point[]{new Point(30, 40),new Point(30, 40),new Point(30, 40),new Point(15, 15),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp0=new Blueprint("mack", "plano1",pts0);
        ibpp.addNewBlueprint(bp0);
        Point[] pts3=new Point[]{new Point(60, 40),new Point(60, 40),new Point(60, 40),new Point(15, 15),new Point(15, 15),new Point(16, 17),new Point(22, 21),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp3=new Blueprint("mack", "plano2",pts3);
        ibpp.addNewBlueprint(bp3);
        Point[] pts1=new Point[]{new Point(67, 89),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp1=new Blueprint("jhon", "plano1",pts1);
        ibpp.addNewBlueprint(bp1);
        Point[] pts2=new Point[]{new Point(87, 109),new Point(87, 109),new Point(87, 109),new Point(47, 15),new Point(23, 12),new Point(85, 23),new Point(95, 40)};
        Blueprint bp2=new Blueprint("jhon", "plano2",pts2);
        ibpp.addNewBlueprint(bp2);
        
        try {
            List<Blueprint> alltheblueprints=ibpp.getAllBluePrints();
            int cont=0;
            for (int i=0; i<alltheblueprints.size();i++){
                if(alltheblueprints.get(i)==bp0){
                    cont+=1;
                }
                if(alltheblueprints.get(i)==bp3){
                    cont+=1;
                }
                if(alltheblueprints.get(i)==bp1){
                    cont+=1;
                }
                if(alltheblueprints.get(i)==bp2){
                    cont+=1;
                }
            }
            assertEquals("The output from getAllBlueprints isn't giving a correct result",cont, alltheblueprints.size());
        } catch (BlueprintNotFoundException ex) {
            fail("Blueprint persistence failed on getting all Blueprints");
        }

    }


    
}
