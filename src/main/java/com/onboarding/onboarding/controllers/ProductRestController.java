package com.onboarding.onboarding.controllers;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onboarding.onboarding.services.IProductServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.onboarding.onboarding.models.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired 
    private IProductServices productServices;

    @GetMapping("/products")
    public List<Product> listAll() {
        return productServices.findAll();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        Product objProduct = null;
        HashMap<String, Object> respuestas = new HashMap();
        ResponseEntity<?> objRespuesta;
        try {
            objProduct = productServices.findById(id);

            if (objProduct == null) {
                respuestas.put("mensaje", "El producto con ID: " + id + " no existe en la base de datos");
                objRespuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
            } else {
                objRespuesta = new ResponseEntity<Product>(objProduct, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            respuestas.put("mensaje", "Error al realizar la consulta en la base de datos");
            respuestas.put("descripción del error", e.getMessage());
            objRespuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return objRespuesta;
    }
    @PostMapping("/products")
    public ResponseEntity<?> save(@RequestBody Product product) {
        Product objProduct = null;
        HashMap<String, Object> respuestas = new HashMap();
        ResponseEntity<?> objRespuesta;
        try {
            objProduct = productServices.save(product);
            objRespuesta = new ResponseEntity<Product>(objProduct, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            respuestas.put("mensaje", "Error al realizar la inserción en la base de datos");
            respuestas.put("descripción del error", e.getMessage());
            objRespuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.BAD_REQUEST);

        }

        return objRespuesta;
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
        Product objProduct = null;
        HashMap<String, Object> respuestas = new HashMap();
        ResponseEntity<?> objRespuesta;
        try {
            objProduct = productServices.findById(id);
            if(objProduct == null) {
                respuestas.put("mensaje", "El producto con ID: " + id + " no existe en la base de datos");
                objRespuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);

            }else{
                objProduct = productServices.update(id,product);
                objRespuesta = new ResponseEntity<Product>(objProduct, HttpStatus.CREATED);
            }
            
        } catch (DataAccessException e) {
            respuestas.put("mensaje", "Error al realizar la actualzación en la base de datos");
            respuestas.put("descripción del error", e.getMessage());
            objRespuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.BAD_REQUEST);
        }

        return objRespuesta;
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Product objProduct = null;
        HashMap<String, Object> respuestas = new HashMap();
        ResponseEntity<?> objRespuesta;
        try {
            objProduct = productServices.findById(id);

            if (objProduct == null) {
                respuestas.put("mensaje","El producto con ID: " + id + " que se desea eliminar no existe en la base de datos");
                objRespuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.NOT_FOUND);
            } else {
                productServices.delete(id);
                objRespuesta = new ResponseEntity<Product>(objProduct, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            respuestas.put("mensaje", "Error al realizar la eliminación del cliente en la base de datos");
            respuestas.put("descripción del error", e.getMessage());
            objRespuesta = new ResponseEntity<HashMap<String, Object>>(respuestas, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return objRespuesta;
    }
    
}


