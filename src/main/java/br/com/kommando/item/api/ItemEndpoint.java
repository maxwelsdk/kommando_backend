package br.com.kommando.item.api;

import br.com.kommando.item.data.models.Item;
import br.com.kommando.item.data.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("itens")
public class ItemEndpoint {

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getAll() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("itens", service.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/{pedidoId}")
    public ResponseEntity<HashMap<String, Object>> newItem(@PathVariable String pedidoId, @RequestBody Item item) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "item criado com sucesso!");
        response.put("item", service.save(pedidoId, item));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(path = "/{pedidoId}/batch")
    public ResponseEntity<HashMap<String, Object>> newItens(@PathVariable String pedidoId, @RequestBody List<Item> itens) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "itens criado com sucesso!");
        response.put("itens", service.saveAll(pedidoId, itens));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> getItem(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("item", service.findById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteItem(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        service.removeById(id);
        response.put("msg", "Item deletado com sucesso!");
        response.put("id", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
