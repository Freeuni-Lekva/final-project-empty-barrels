package DAO;

import Models.UserInfo;

import java.util.List;

public interface UserInfoDAO {
    /**
     * Gets a single UserInfo using its' id
     * @param id
     * @return
     */
    public UserInfo getUserInfo(int id);

    /**
     * Inserts given UserInfo object in the store
     * @param userInfo
     */
    public void insertUserInfo(UserInfo userInfo);

    /**
     * Gets every UserInfo from the store
     * @return
     */
    public List<UserInfo> getAllUserInfos();
}
