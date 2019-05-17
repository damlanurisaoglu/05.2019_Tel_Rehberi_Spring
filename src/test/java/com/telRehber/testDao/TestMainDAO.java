package com.telRehber.testDao;


import com.telRehber.dao.MainDAO;
import com.telRehber.model.*;
import com.telRehber.spring.config.AppConfig;
import com.telRehber.spring.config.WebApplicationInitializer;
import com.telRehber.spring.config.WebConfig;
import com.telRehber.web.MainController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebApplicationInitializer.class, AppConfig.class,
        WebConfig.class})
@Transactional
@WebAppConfiguration

public class TestMainDAO {
    @Autowired
    private MainDAO mainDAO;


    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Test
    public void testLoadObject(){
        tblPersonel personel = (tblPersonel) mainDAO.loadObject(tblPersonel.class,4);
        logger.info("testLoadObject çalıştı");
        Assert.assertTrue(personel !=null);
    }

    @Test
    public void testRemoveObject(){
        tblPersonel personel = (tblPersonel) mainDAO.loadObject(tblPersonel.class,4);
        logger.info("testRemoveObject çalıştı");
        boolean success= mainDAO.removeObject(personel);
        Assert.assertTrue(success);
    }


    @Test
    public void testSaveOrObject(){
        tblPersonel personel = new tblPersonel();
        tblUnvan unvan =(tblUnvan) mainDAO.loadObject(tblUnvan.class,2);
        tblBirim birim=(tblBirim)mainDAO.loadObject(tblBirim.class,3);
        tblLogin login=(tblLogin)mainDAO.loadObject(tblLogin.class,1);
        personel.setUnvani(unvan);
        personel.setAdi("test");
        personel.setSoyadi("test");
        personel.setTelefonNo("05079999999");
        personel.setBirimi(birim);
        personel.setKaydedenId(login);
        logger.info("testSaveOrObject çalıştı");
        boolean success= mainDAO.saveOrUpdateObject(personel);
        Assert.assertTrue(success);

    }

    @Test
    public void testLoadUnvan() {

        logger.info("testLoadUnvan çalıştı");
        List<tblUnvan> unvanList = mainDAO.loadUnvan();
        Assert.assertTrue(unvanList.size() > 0);
    }
    @Test
    public void testLoadBirim() {

        logger.info("testLoadBirim çalıştı");
        List<tblBirim> list = mainDAO.loadBirim();
        Assert.assertTrue(list.size() > 0);
    }


    @Test
    public void testLoadPersonel() {
        logger.info("testLoadPersonel çalıştı");
        List<tblPersonel> list = mainDAO.loadPersonel(1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testLoadPersonelUnvanOrBirim() {
        logger.info("testLoadPersonelUnvanOrBirim çalıştı");
        List<tblPersonel> list = mainDAO.loadPersonelUnvanOrBirim("birimi",3,1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testloadPersonelAdaGore() {
        logger.info("testloadPersonelAdaGore çalıştı");
        List<tblPersonel> list = mainDAO.loadPersonelAdaGore("damla");
        Assert.assertTrue(list.size() > 0);
    }
    @Test
    public void testloadTelefonAdaGore() {
        logger.info("testloadTelefonAdaGore çalıştı");
        List<String> list = mainDAO.loadTelefonAdaGore("rukiye");
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testloadAdTelefonaGore() {
        logger.info("testloadAdTelefonaGore çalıştı");
        List<String> list = mainDAO.loadAdTelefonaGore("3845");
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testLoadLogin() {

        try{
            List<tblLogin> loginList = mainDAO.loadLogin("denemee","deneme");
            Assert.assertTrue(loginList.size() > 0);

            logger.info("testLoadLogin çalıştı.");
        }
        catch (Exception e) {
            logger.error("testLoadLogin çalışamadı.");
        }



    }

}
