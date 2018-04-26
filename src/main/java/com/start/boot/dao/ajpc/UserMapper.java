package com.start.boot.dao.ajpc;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/16.
 */
@Repository
public interface UserMapper {

    void GetDwbmCombTree(Map map);

    void TyywNewUserList(Map map);

    void GetUserList(Map map);

    void AddUser(Map<String, String> map);

    void UpdateUser(Map<String, String> map);

    void GetUserByGh(Map map);

    void DeleteUser(Map map);

    void ResetUserPwd(Map map);

    void UserSync(Map map);
}
