package cat.itacademy.s04.t02.n01.services;

import cat.itacademy.s04.t02.n01.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingPathVariableException;

import java.util.List;
import java.util.Optional;

@Service
public class FruitServiceImpl implements FruitService{
    @Autowired
    private FruitRepository fruitRepository;

    @Override
    public List<Fruit> findAll() {
        return fruitRepository.findAll();
    }

    @Override
    public Fruit getReferenceById(int id) throws FruitNotFoundException {
          return fruitRepository.findById(id).orElseThrow(() ->
                new FruitNotFoundException("Fruit not found with given ID.")
        );
    }

    @Override
    public Fruit save(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    @Override
    public void deleteById(int id) {
        if (!fruitRepository.existsById(id)){
            throw new EmptyResultDataAccessException("Fruit to be deleted not found with ID " + id + ".", 1);
        }
        fruitRepository.deleteById(id);
    }

    @Override
    public Fruit update(int id, Fruit fruit) {
        Fruit currentFruit = fruitRepository.findById(id)
                            .orElseThrow(()-> new FruitNotFoundException("Fruit with given ID not found"));
        currentFruit.setName(fruit.getName());
        currentFruit.setKgQty(fruit.getKgQty());
        return fruitRepository.save(currentFruit);
    }
}
