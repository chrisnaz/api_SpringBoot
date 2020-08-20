package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonbyId(UUID uuid) {
        return DB.stream()
                .filter(person -> person.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public int deletePersonbyId(UUID uuid) {
        Optional<Person> personMaybe = selectPersonbyId(uuid);
        if (personMaybe.equals("")) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonbyId(UUID uuid, Person update) {
        return selectPersonbyId(uuid)
                .map(person1 -> {
                    int indexOfPersonUpdate = DB.indexOf(person1);
                    if (indexOfPersonUpdate >= 0) {
                        DB.set(indexOfPersonUpdate, new Person(uuid,update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
