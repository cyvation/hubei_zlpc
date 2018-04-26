package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface GradeMapper {

    void getMarkSheet(Map map);
}
