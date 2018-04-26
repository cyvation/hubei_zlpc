package com.start.boot.service.impl;


import com.start.boot.dao.ajpc.PersonMapper;
import com.start.boot.domain.Person;
import com.start.boot.domain.PersonExample;
import com.start.boot.query.PersonQuery;
import com.start.boot.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Creat
 * ed by caomin on 2017/11/10.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;



    @Override
    public Person save(Person person) {
//        PersonExample example = new PersonExample();
//        example.createCriteria().andGhEqualTo(person.getGh()).andDwbmEqualTo(person.getDwbm()).andGxbmEqualTo(person.getGxbm());
//        List<Person> people = personMapper.selectByExample(example);
//        if(people.size()>0){
//            person.setId(people.get(0).getId());
//            personMapper.updateByPrimaryKeySelective(person);
//        }else {  }
            person.setId(UUID.randomUUID().toString());
            DateTime dateTime=new DateTime(new Date());
            Date date = dateTime.toLocalDate().toDate();
            person.setCjsj(date);
            person.setZhxgsj(date);
            personMapper.insert(person);
        return person;
    }

    @Override
    public boolean delPerson(String id) {
        if (!StringUtils.isEmpty(id)){
          return   personMapper.deleteByPrimaryKey(id)== 0 ?false:true;
        }
        return false;
    }

    @Override
    public int updatePerson(Person Person) {
        return personMapper.updateByPrimaryKeySelective(Person);
    }

    @Override
    public Person getPersonById(String id) {
        if (!StringUtils.isEmpty(id)){
            return   personMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<Person> getPersonListByUserIdAndType(String dwbm, String gh, Integer zllx) {
        if (!StringUtils.isEmpty(dwbm)&&!StringUtils.isEmpty(gh)&& zllx !=null) {
            PersonExample example = new PersonExample();
            example.createCriteria().andDwbmEqualTo(dwbm).andGhEqualTo(gh).andZllxEqualTo(zllx);
            return personMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<Person> getPersonListByUserId(PersonQuery query) {
        String dwbm = query.getDwbm();
        String gh = query.getGh();
        Integer zllx = query.getZllx();
        String zlmc = query.getZlmc();
        PersonExample example = new PersonExample();
        PersonExample.Criteria criteria = example.createCriteria();
        criteria.andSfscEqualTo("N");
        if (!StringUtils.isEmpty(dwbm)) {
            criteria.andDwbmEqualTo(dwbm);

        }
        if (!StringUtils.isEmpty(gh)){
            criteria.andGhEqualTo(gh);
        }
        if (zllx!=null){
            criteria.andZllxEqualTo(zllx);
        }
        if (!StringUtils.isEmpty(zlmc)){
            criteria.andZlmcLike("%"+zlmc+"%");
        }
        example.setOrderByClause("cjsj DESC");
        return personMapper.selectByExample(example);
    }

    @Override
       public Person getPerson(Person person) throws Exception {
        PersonExample example = new PersonExample();
        example.createCriteria().andGhEqualTo(person.getGh()).andDwbmEqualTo(person.getDwbm()).andGxbmEqualTo(person.getGxbm());
        List<Person> people = personMapper.selectByExample(example);
        if (people.size() > 0){
            return people.get(0);
        }
        return null;
    }
}
