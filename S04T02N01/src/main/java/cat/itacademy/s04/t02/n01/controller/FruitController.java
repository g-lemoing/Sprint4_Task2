package cat.itacademy.s04.t02.n01.controller;

import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.services.FruitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruita")
public class FruitController {
    @Autowired
    FruitServiceImpl fruitService;

    @GetMapping("/getAll")
    public List<Fruit> getAll(){
        return fruitService.findAll();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getFruit(@PathVariable int id){
        return new ResponseEntity<>(fruitService.getReferenceById(id), HttpStatus.OK);
    }

    @GetMapping("/getOne")
    public ResponseEntity<Object> getFruitMissingParameter(){
        return new ResponseEntity<>("ID parameter is mandatory", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit fruit){
        return new ResponseEntity<>(fruitService.save(fruit), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable int id, @RequestBody Fruit fruit){
        Fruit updatedFruit = fruitService.update(id, fruit);
        return new ResponseEntity<>(updatedFruit, HttpStatus.OK);
    }

    @PutMapping("/update/")
    public ResponseEntity<Object> updateFruitMissingParameter(@RequestBody Fruit fruit){
        return new ResponseEntity<>("ID parameter is mandatory", HttpStatus.BAD_REQUEST);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Fruit> deleteFruit(@PathVariable int id){
        fruitService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
