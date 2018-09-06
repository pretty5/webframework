package com.zhiyou100;

import java.util.List;

public interface PersonDao {
    Person findById(int id);
    int insert(Person person);
      List<Person> listfindId(int id);
    Person findByIdToMap(int id);

    int  update(Person person);

    int delete(int id);

}
