package com.start.boot.service;


import com.start.boot.domain.Person;
import com.start.boot.query.PersonQuery;

import java.util.List;

/**
 * 收藏service
 * Created by caomin on 2017/11/10.
 */
public interface PersonService {


    /**
     * 保存
     * @param Person
     * @return
     */
    Person save(Person Person);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    boolean delPerson(String id);

    /**
     * 更新
     * @param Person
     * @return
     */
    int updatePerson(Person Person);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Person  getPersonById(String id);

    /**
     * 获取某个用户某个类型的收藏
     * @param
     * @param type
     * @return
     */
    List<Person> getPersonListByUserIdAndType(String dwbm, String gh, Integer type);

    /**
     * 获取某个用户的所有收藏
     * @param
     * @return
     */
    List<Person>getPersonListByUserId(PersonQuery query);

    /**
     * 获取当前收藏案件
     * @param person
     * @return
     * @throws Exception
     */
    Person getPerson(Person person) throws Exception;

}
