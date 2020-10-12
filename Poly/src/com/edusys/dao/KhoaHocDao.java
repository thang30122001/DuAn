/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.entity.KhoaHoc;
import com.edusys.helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhoaHocDao extends EduSysDao<KhoaHoc, String> {

    @Override
    public void insert(KhoaHoc entity) {
        String sql = "INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?,?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV());
    }

    @Override
    public void update(KhoaHoc entity) {
        String sql="UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHERE MaKH=?";
 JdbcHelper.executeUpdate(sql,entity.getMaCD(),
 entity.getHocPhi(),
 entity.getThoiLuong(),
 entity.getNgayKG(),
 entity.getGhiChu(),
 entity.getMaNV(),
 entity.getMaKH());
    }

    @Override
    public void delete(String MaKH) {
        
    }
public void delete(Integer MaKH) {
        String sql="DELETE FROM KhoaHoc WHERE MaKH=?";
 JdbcHelper.executeUpdate(sql, MaKH);
    }
    @Override
    public List<KhoaHoc> selectAll() {
        return selectBySql("SELECT * FROM KhoaHoc");
    }

    @Override
    public KhoaHoc selectById(String id) {
        List<KhoaHoc> list = this.selectBySql("SELECT * FROM KhoaHoc WHERE MaKH=?", id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> list = this.selectBySql("SELECT * FROM KhoaHoc WHERE MaKH=?", id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
 entity.setHocPhi(rs.getDouble("HocPhi"));
 entity.setThoiLuong(rs.getInt("ThoiLuong"));
 entity.setNgayKG(rs.getDate("NgayKG"));
 entity.setGhiChu(rs.getString("GhiChu"));
 entity.setMaNV(rs.getString("MaNV"));
 entity.setNgayTao(rs.getDate("NgayTao"));
 entity.setMaCD(rs.getString("MaCD"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();

        } catch (Exception e) {
        }
        return list;
    }

}
