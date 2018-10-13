/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("InMemoryBlue")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
 
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }
    @Override
    public List<Blueprint> getBlueprintsByAuthor(String autor){
        List<Blueprint> authorblueprint=new ArrayList();
        for (Map.Entry<Tuple<String, String>,Blueprint> tupla:blueprints.entrySet()) {            
            if(tupla.getKey().getElem1()== autor){
                authorblueprint.add(tupla.getValue());
            }
        }
        
        return authorblueprint;
    }

    @Override
    public void addNewBlueprint(Blueprint bp) {
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
    }

    @Override
    public List<Blueprint> getAllBluePrints() throws BlueprintNotFoundException {
        List<Blueprint> allblueprints=new ArrayList();
        for (Map.Entry<Tuple<String, String>,Blueprint> tupla:blueprints.entrySet()) {            
                allblueprints.add(tupla.getValue());
        }
        return allblueprints;
    }


    
    
}
