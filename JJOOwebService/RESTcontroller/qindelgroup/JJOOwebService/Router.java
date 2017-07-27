package qindelgroup.JJOOwebService;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import qindelgroup.JJOOwebService.MasterDAO;

@RestController
public class Router {

    @RequestMapping(value="/olimpiadas", method=RequestMethod.GET)
    public ArrayList<HashMap<String, String>> getOlimpiadas(){
    	MasterDAO masterDAO = new MasterDAO();
    	return masterDAO.getCiudadesCompleto();
    }
    
    @RequestMapping(value="/sedes", method=RequestMethod.GET)
    public ArrayList<HashMap<String, String>> getSedes(){
    	SedeDAO sedesDAO = new SedeDAO();
    	return sedesDAO.getSedes();
    }
    
    @RequestMapping(value="tipoJJOO/descripciones", method=RequestMethod.GET)
    public String[] getDescripcionesTipoJJOO() {
    	TipoJJOODAO tipoJJOODAO = new TipoJJOODAO();
    	return tipoJJOODAO.getDescripciones();
    }
    @RequestMapping(value="ciudades/nombres", method=RequestMethod.GET)
    public String[] getNombresCiudad() {
    	CiudadDAO ciudadDAO = new CiudadDAO();
    	return ciudadDAO.getNombres();
    }
    @RequestMapping(value="sedes/{anyo}/{id_tipo}", method=RequestMethod.GET)
    public HashMap<String, Integer> getSedeByAnyoTipoJJOO(@PathVariable("anyo") int anyo, @PathVariable("id_tipo") int id_tipo) {
    	SedeDAO sedeDAO = new SedeDAO();
    	return sedeDAO.getByAnyoTipo(anyo, id_tipo);
    }
    
    @RequestMapping(value="/sedes/{anyo}/{id_tipo}", method=RequestMethod.DELETE)
    public void deleteSedes(@PathVariable("anyo") int anyo, @PathVariable("id_tipo") int id_tipo) {
    	SedeDAO sedesDAO = new SedeDAO();
    	sedesDAO.deleteSede(anyo, id_tipo);
    }
    
    @RequestMapping(value="/sedes/{anyo}/{id_tipo}", method=RequestMethod.PUT)
    public void updateSedes(@PathVariable("anyo") int anyo, @PathVariable("id_tipo") int id_tipo, @RequestBody int id_ciudad) {
    	SedeDAO sedesDAO = new SedeDAO();
    	sedesDAO.updateSede(anyo, id_tipo, id_ciudad);
    }
    
    @RequestMapping(value="/sedes", method=RequestMethod.POST)
    public void createSedes(@RequestBody int[] data) {
    	SedeDAO sedesDAO = new SedeDAO();
    	sedesDAO.insertNuevaSede(data[0], data[1], data[2]);
    }

    
    
}
