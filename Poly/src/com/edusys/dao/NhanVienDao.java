/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienDao extends EduSysDao<NhanVien,String>{
    String Isert_sql="insert into nhanvien(manv,matkhau,hoten,vaitro) values(?,?,?,?)";
    String Update_sql="UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?";
    String Delete_sql="DELETE FROM NhanVien WHERE MaNV=?";
    String Select_all_sql="SELECT * FROM NhanVien";
    String Select_id_sql="SELECT * FROM NhanVien WHERE MaNV=?";
    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.executeUpdate(Isert_sql,entity.getMaNV(),entity.getMatKhau(),entity.getHoTen(),entity.getVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.executeUpdate(Update_sql,entity.getMatKhau(),entity.getHoTen(),entity.getVaiTro(),entity.getMaNV());
    }

    @Override
    public void delete(String Manv) {
        JdbcHelper.executeUpdate(Delete_sql,Manv);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(Select_all_sql);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list=this.selectBySql(Select_id_sql,id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
       List<NhanVien> list=new ArrayList<NhanVien>();
        try {
            ResultSet rs=JdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                NhanVien entity=new NhanVien();
                entity.setMaNV(rs.getString("manv"));
                entity.setHoTen(rs.getString("hoten"));
                entity.setMatKhau(rs.getString("matkhau"));
                entity.setVaiTro(rs.getBoolean("vaitro"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            
        } catch (Exception e) {
        }
        return list;
    }
    
    
}
