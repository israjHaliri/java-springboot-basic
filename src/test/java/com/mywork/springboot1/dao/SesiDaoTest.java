package com.mywork.springboot1.dao;

import com.mywork.springboot1.entity.Materi;
import com.mywork.springboot1.entity.Peserta;
import com.mywork.springboot1.entity.Sesi;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by israj on 9/22/2016.
 */

//spring boot 1.3
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Springboot1Application.class)

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD ,
        scripts = {"/data/peserta.sql","/data/materi.sql","/data/sesi.sql"}
)
public class SesiDaoTest {

    @Autowired
    private SesiDao sesiDao;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testFindByMateri(){
        Materi m = new Materi();
        m.setId("4");

        PageRequest page = new PageRequest(0,5);

        Page<Sesi> hasilQuery = sesiDao.findByMateri(m,page);
        Assert.assertEquals(1L,hasilQuery.getTotalElements());

        Assert.assertFalse(hasilQuery.getContent().isEmpty());
        Sesi s = hasilQuery.getContent().get(0);
        Assert.assertNotNull(s);
        Assert.assertEquals("Java Fundamental",s.getMateri().getNama());
    }

    @Test
    public void testcariBerdasarkanCustom() throws Exception{
        PageRequest page = new PageRequest(0,5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date Sejak =  simpleDateFormat.parse("2016-01-05");
        Date Sampai =  simpleDateFormat.parse("2016-02-05");

        Page<Sesi> hasil = sesiDao.cariBerdasarkanCustom(Sejak,Sampai,"JF-001",page);
        Assert.assertEquals(1L,hasil.getTotalElements());
        Assert.assertFalse(hasil.getContent().isEmpty());
        Sesi s = hasil.getContent().get(0);
        Assert.assertEquals("Java Fundamental",s.getMateri().getNama());


    }

    @Test
    public void testSave() throws Exception{
        Peserta peserta =  new Peserta();
        peserta.setId("1");


        Peserta peserta1 = new Peserta();
        peserta1.setId("2");


        Materi materi = new Materi();
        materi.setId("4");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date Sejak =  simpleDateFormat.parse("2016-01-05");
        Date Sampai =  simpleDateFormat.parse("2016-02-05");

        Sesi sesi = new Sesi();
        sesi.setMateri(materi);
        sesi.setMulai(Sejak);
        sesi.setSampai(Sampai);

        sesi.getDaftarPeserta().add(peserta);
        sesi.getDaftarPeserta().add(peserta1);

        sesiDao.save(sesi);

        String idSesiBaru = sesi.getId();
        Assert.assertNotNull(idSesiBaru);
        System.out.println("id sesi baru = "+idSesiBaru);

        String sql = "select * from sesi where id_materi='4'";
        String sqlManytoMany = "select count(*) from peserta_pelatihan where id_sesi=?";

        try(Connection c = dataSource.getConnection()) {
//            cek table sesi
            ResultSet resultSet = c.createStatement().executeQuery(sql);
            Assert.assertTrue(resultSet.next());
            Assert.assertEquals(1L,resultSet.getLong(1));

//            cek table relasi dengan peserta
            PreparedStatement ps = c.prepareStatement(sqlManytoMany);
            ps.setString(1,idSesiBaru);

            ResultSet resultSet1 =  ps.executeQuery();
            Assert.assertTrue(resultSet1.next());
            Assert.assertEquals(2L,resultSet1.getLong(1));

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }



}
