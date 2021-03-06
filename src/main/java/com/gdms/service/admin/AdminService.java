package com.gdms.service.admin;

import com.gdms.model.Admin;
import com.gdms.model.GGroup;
import com.gdms.service.common.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService extends BaseService<Admin> {

    Admin getModelByUsernameAndPassword(Admin admin);

    List<Admin> getAlladmin();

    Admin selectUserByUsername(String username);

    Admin selectAdminById(String adminId);

    int updateByAdminId(Admin admin);

    Boolean insertAdmin(Admin admin);

    String findAdminIdByAdminUsername(String kitAdminUsername);

    List<Admin> searchAdminByAdminUsername(String kitAdminUsername);

    int updateAdmin(Admin admin);

    Admin queryAdminByKitAdminUsername(String kitAdminUsername);

    List<Admin> queryAdminByIdentity(Integer identity);
}
