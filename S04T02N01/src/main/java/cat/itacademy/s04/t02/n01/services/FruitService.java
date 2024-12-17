package cat.itacademy.s04.t02.n01.services;

import cat.itacademy.s04.t02.n01.model.Fruit;

import java.util.List;

public interface FruitService {
    List<Fruit> findAll();
    Fruit getReferenceById(int id);
    Fruit save(Fruit fruit);
    void deleteById(int id);
    Fruit update(int id, Fruit fruit);
}
