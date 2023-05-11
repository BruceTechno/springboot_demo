package com.example.OrderSystem.repository;

import com.example.OrderSystem.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String> {
    public List<PersonInfo> findByAgeGreaterThan(int age);

    public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);

    public List<PersonInfo> findByAgeBetween(int age, int age2);

    public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int age, int age2);

    public List<PersonInfo> findByCityContaining(String str);

    public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAge(int age, String cityStr);

    public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(int age, int age2);

    @Modifying
    @Transactional
    @Query("update PersonInfo p set p.id = :newId , p.name = :newName," +
            " p.age = :newAge, p.city = :newCity where p.id = :newId")//id name age city
    //update (Entity) set
    public int updateNameById(
            @Param("newId") String inputId,
            @Param("newName") String inputName,
            @Param("newAge") int inputAge,
            @Param("newCity") String inputCity);
    @Transactional
    @Modifying
    @Query("select new PersonInfo(p.id,p.name,p.age,p.city)" +
            " from PersonInfo p" +
            " where p.name like concat('%',:newName,'%') or" +
            " p.city like concat('%',:newCity,'%')")
    public List<PersonInfo> searchByNameOrCityNone(@Param("newName")String name ,@Param("newCity")String city);

    @Transactional
    @Modifying
    @Query("select new PersonInfo(p.id,p.name,p.age,p.city)" +
            " from PersonInfo p" +
            " where p.name like concat('%',:newName,'%') or" +
            " p.city like concat('%',:newCity,'%')")
    public List<PersonInfo> searchByNameOrCityAll (@Param("newName")String name ,@Param("newCity")String city);


    public List<PersonInfo> findByNameContainingOrCityContaining(String name , String city);

//    public List<PersonInfo> doQueryByAge(int age);
//
//    public List<PersonInfo> doQueryByAge(int age, int limitSize);
//
//    public List<PersonInfo> doQueryByAge(int age, int limitSize, int startPosition);
//
//    //    public List<PersonInfo> findBy
////    @Transactional
////    public int doUpdateAgeByName(int age , String name);

}
