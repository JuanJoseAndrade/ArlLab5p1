/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services;

import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.persistence.BlueprintNotFoundException;
import edu.eci.arsw.persistenceimpl.BlueprintsPersistence;
import edu.eci.arsw.filtros.Filtro;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("BluePrintServices")
public class BlueprintsServices {
    @Autowired
    @Qualifier("InMemoryBlue")
    BlueprintsPersistence bpp;
   
    @Autowired
    @Qualifier("FiltroA")
    Filtro filtro;
    public BlueprintsServices(){}
    public void addNewBlueprint(Blueprint bp){
        bpp.addNewBlueprint(bp);
    }
    
    public List<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        return bpp.getAllBluePrints();
    }
    public void useFilter(Blueprint bp){
         filtro.filtrar(bp);
        
    }
    
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        return bpp.getBlueprint(author, name);
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public List<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        List<Blueprint> planos=bpp.getBlueprintsByAuthor(author);
            return planos;
    }
    
}
