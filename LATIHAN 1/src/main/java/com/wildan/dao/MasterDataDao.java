package com.wildan.dao;


import com.wildan.model.Departemen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MasterDataDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void getKoneksi(){
        Connection con = null;
        try {
            con = DriverManager
                    .getConnection("jdbc:postgresql://localhost/online_shop",
                            "postgres","123123");
            PreparedStatement ps = dataSource.getConnection()
                    .prepareStatement( "SELECT department_id, name, description FROM public.department ORDER BY department_id ASC ;");

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("department_id")+
                        " : "+rs.getString("name")+
                        " : "+rs.getString("description"));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Departemen> getDeplist() {
        String sql = "SELECT department_id, name, description FROM public.department ORDER BY department_id ASC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Departemen dept = new Departemen();
            dept.setDeskripsi(rs.getString("description"));
            dept.setName(rs.getString("name"));
            dept.setId(rs.getInt("department_id"));
            return dept;
        });
    }
    public Departemen getDeptListById(int id){
        String sql =
                " SELECT department_id, name, description FROM department where department_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Departemen departemen = new Departemen();
            departemen.setDeskripsi(rs.getString("description"));
            departemen.setName(rs.getString("name"));
            departemen.setId(rs.getInt("department_id"));
            return departemen;
        },id );
    }

    public Integer saveDepartmen(Departemen dept){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql =
                " INSERT INTO department( name, description) VALUES (:nama, :deskripsi)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", dept.getName());
        map.addValue("deskripsi", dept.getDeskripsi());
        namedParameterJdbcTemplate.update(sql, map, keyHolder);
        return (Integer) keyHolder.getKeys().get("department_id");
    }

//    public Departemen updateDepartmen(Departemen depUp) {
//        String sql =
//                "UPDATE department SET (name, description) WHERE <department id?>";
//        jdbcTemplate.update(sql,
//                depUp.getDeskripsi(),
//                depUp.getName(),
//                depUp.getId());
//        return depUp;
//    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM department WHERE department_id = ?", id);
    }

    public List<Departemen> getDeplistJdbc(){
        PreparedStatement ps = null;
        List<Departemen> depList = null;
        try {
            ps = dataSource.getConnection()
                    .prepareStatement("SELECT department_id, name, description FROM public.department ORDER BY department_id ASC ;");
            ResultSet rs = ps.executeQuery();
            depList = new ArrayList<>();
            while (rs.next()) {
                Departemen dept = new Departemen();
                dept.setDeskripsi(rs.getString("description"));
                dept.setName(rs.getString("name"));
                dept.setId(rs.getInt("department_id"));
                depList.add(dept);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return depList;
    }
}
