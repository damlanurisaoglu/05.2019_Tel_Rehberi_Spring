package com.telRehber.testWeb;

import com.telRehber.model.tblPersonel;
import com.telRehber.model.tblUnvan;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebApplicationInitializer.class, AppConfig.class,
        WebConfig.class})
@Transactional
@WebAppConfiguration
public class TestMainController {
    @Autowired
            private MainController mainController;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    @Test
     public void testloadUnvan(){
        logger.info("testloadUnvan çalıştı");
        String list = mainController.loadUnvan();
        Assert.assertTrue(list!=null );
     }
    @Test
    public void testloadBirim(){
        logger.info("testloadBirim çalıştı");
        String list = mainController.loadBirim();
        Assert.assertTrue(list!=null );
    }

    @Test
    public void testloadLogin(){
        logger.info("testloadLogin çalıştı");
        MockHttpServletRequest request = new MockHttpServletRequest();
        String list = mainController.loadLogin("denemee","deneme",request);
        Assert.assertTrue(list!=null );
    }
    @Test
    public void testloadPersonel(){
        logger.info("testloadPersonel çalıştı");
        String list = mainController.loadPersonel(1);
        Assert.assertTrue(list!=null );
    }
    @Test
    public void testloadPersonel2(){
        logger.info("testloadPersonel2 çalıştı");
        String list = mainController.loadPersonel2("birimi",3,1);
        Assert.assertTrue(list!=null );
    }
    @Test
    public void testloadPersonelAdaGore() {
        logger.info("testloadPersonelAdaGore çalıştı");
        String list = mainController.loadPersonelAdaGore("damla");
        Assert.assertTrue(list!=null );
    }
    @Test
    public void testloadTelefonAdaGore() {
        logger.info("testloadTelefonAdaGore çalıştı");
        String list = mainController.loadTelefonAdaGore("rukiye");
        Assert.assertTrue(list!=null );
    }

    @Test
    public void testloadAdTelefonaGore() {
        logger.info("testloadAdTelefonaGore çalıştı");
        String list = mainController.loadAdTelefonaGore("3845");
        Assert.assertTrue(list!=null );
    }

    @Test
    public void testOutLogin() {
        logger.info("testOutLogin çalıştı");
        MockHttpServletRequest request = new MockHttpServletRequest();
        try{mainController.outLogin(request);
            Assert.assertTrue(true );
        }
        catch (Exception e){
            Assert.assertTrue(false );
        }


    }

    @Test
    public void testdeleteBirim(){

        logger.info("testdeleteBirim çalıştı");
        String success = mainController.deleteBirim(3);
        Assert.assertTrue(success=="true" );
    }
    @Test
    public void testdeleteUnvan(){

        logger.info("testdeleteUnvan çalıştı");
        String success = mainController.deleteUnvan(2);
        Assert.assertTrue(success=="true" );
    }

    @Test
    public void testdeleteLogin(){

        logger.info("testdeleteLogin çalıştı");
        String success = mainController.deleteLogin(1);
        Assert.assertTrue(success=="true" );
    }

    @Test
    public void testdeletePersonel(){

        logger.info("testdeletePersonel çalıştı");
        String success = mainController.deletePersonel(4);
        Assert.assertTrue(success=="true" );
    }
    @Test
    public void testSaveOrUpdatePersonel(){

        logger.info("testSaveOrUpdatePersonel çalıştı");
        String success = mainController.saveOrUpdatePersonel(1,"test","test",2,3,"212121",1);
        Assert.assertTrue(success=="true" );
    }
    @Test
    public void testsavaOrUpdateBirim(){

        logger.info("testsavaOrUpdateBirim çalıştı");
        String success = mainController.savaOrUpdateBirim(1,"test");
        Assert.assertTrue(success=="true" );
    }

    @Test
    public void testsavaOrUpdateUnvan(){

        logger.info("testsavaOrUpdateBirim çalıştı");
        String success = mainController.savaOrUpdateUnvan(1,"test");
        Assert.assertTrue(success=="true" );
    }

    @Test
    public void testsaveOrUpdateLogin(){

        logger.info("testsaveOrUpdateLogin çalıştı");
        String success = mainController.saveOrUpdateLogin(1,"test","test");
        Assert.assertTrue(success=="true" );
    }

}
