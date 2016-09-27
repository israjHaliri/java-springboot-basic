package com.mywork.springboot1.dao;

import com.mywork.springboot1.Springboot1Application;
import com.mywork.springboot1.entity.Peserta;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by israj on 9/22/2016.
 */

//spring boot 1.3
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Springboot1Application.class)

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "/data/peserta.sql")
public class PesertaDaoTest {

    @Autowired
    private PesertaDao pesertaDao;

    @Autowired
    private DataSource dataSource;

    @Test
    public  void testInsert() throws SQLException{
        Peserta peserta = new Peserta();

        peserta.setName("israj");
        peserta.setEmail("israj.haliri@gmail.com");
        peserta.setTanggalLahir(new Date());

        pesertaDao.save(peserta);

        String sql =  "select count(*) as jumlah from peserta where email = 'israj.haliri@gmail.com'";

        try (Connection c = dataSource.getConnection()) {

            ResultSet rs = c.createStatement().executeQuery(sql);
            Assert.assertTrue(rs.next());

            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());

            c.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void testhitung(){
        Long jumlah = pesertaDao.count();
        Assert.assertEquals(3L,jumlah.longValue());
    }

    @Test
    public void testCariById(){
        Peserta p = pesertaDao.findOne("1");
        Assert.assertNotNull(p);
        Assert.assertEquals("israj1",p.getName());
        Assert.assertEquals("israj1@yahoo.com",p.getEmail());

        Peserta px = pesertaDao.findOne("xx");
        Assert.assertNull(px);
    }

    @After
    public void hapusData() throws SQLException{
        String sql = "delete from peserta where email =  'israj.haliri@gmail.com'";
        try(Connection c = dataSource.getConnection()){
            c.createStatement().executeUpdate(sql);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
