package com.telRehber.web;


import com.telRehber.Interceptor.Interceptor;
import com.telRehber.model.*;
import com.telRehber.service.MainService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.SplittableRandom;

@Controller
@RequestMapping(value = "/*")
public class MainController {

    @Autowired
    private MainService mainService;

    Locale locale = new Locale("tr", "TR");
    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    String par=null;
    private void log(String metodIsmi, String parametre, String geriDönüs){
        logger.info("MainController/"+metodIsmi+" çalıştı! \n " + "Gelen parametreler ->"+parametre+"\n Geri Dönüş Parametresi = "+geriDönüs);
    }


    @PostMapping(value = "/saveOrUpdatePersonel.ajax")
    public @ResponseBody
    String saveOrUpdatePersonel(@RequestParam int personelId,
                               @RequestParam String adi,@RequestParam String soyadi,
                               @RequestParam int unvanId, @RequestParam int birimId,
                               @RequestParam String telNo, @RequestParam int kaydedenId) {


        Boolean success = mainService.saveOrUpdatePersonel(personelId, adi,soyadi,
                unvanId,birimId,telNo,kaydedenId);
        par= "PersonelD="+
        personelId+", Personel Adı="+adi+"Personel Soyadı="+soyadi+"" +
                ", Personel Ünvan ID="+unvanId
                +", Personel Brim ID="+birimId+", Personel Telefon No="+telNo+
                ", Personeli Kaydeden ID="+kaydedenId;
        log("saveOrUpdatePersonel",par,success.toString());

        return success.toString();
    }

    @PostMapping(value = "/saveOrUpdateLogin.ajax")
    public @ResponseBody
    String saveOrUpdateLogin(@RequestParam int loginId,
                                @RequestParam String kullaniciAdi,@RequestParam String sifre) {
        Boolean success = mainService.saveOrUpdateLogin(loginId, kullaniciAdi,sifre);
        par="Login ID="+loginId+", Kullanıcı Adı="+kullaniciAdi+", Kullanıcı Şifresi="+sifre;
        log("saveOrUpdateLogin",par,success.toString());

        return success.toString();
    }

    @PostMapping(value = "/savaOrUpdateBirim.ajax")
    public @ResponseBody
    String savaOrUpdateBirim(@RequestParam int birimId,
                             @RequestParam String birimAdi){
        Boolean success = mainService.saveOrUpdateBirim(birimId, birimAdi);
        par="Birim ID="+birimId+", Birim Adı="+birimAdi;
        log("savaOrUpdateBirim",par,success.toString());
        return success.toString();
    }


    @PostMapping(value = "/savaOrUpdateUnvan.ajax")
    public @ResponseBody
    String savaOrUpdateUnvan(@RequestParam int unvanId,
                             @RequestParam String unvanAdi){
        Boolean success = mainService.saveOrUpdateUnvan(unvanId, unvanAdi);
        par="Unvan ID="+unvanId+", Unvan Adı="+unvanAdi;
        logger.info("MainController/savaOrUpdateUnvan çalıştı! \n " + "Gelen parametreler ->"+"Unvan ID="+unvanId+", Unvan Adı="+unvanAdi+"\n Geri Dönüş Parametresi = "+success.toString());

        return success.toString();
    }


    @PostMapping(value = "/deletePersonel.ajax")
    public @ResponseBody
    String deletePersonel(@RequestParam int personelId) {
        Boolean success = mainService.deletePersonel(personelId);
        par="Personel ID="+personelId;
        log("deletePersonel",par,success.toString());
        return success.toString();
    }

    @PostMapping(value = "/deleteBirim.ajax")
    public @ResponseBody
    String deleteBirim(@RequestParam int birimId) {
        Boolean success = mainService.deleteBirim(birimId);
        par="Birim ID="+birimId;
        log("deleteBirim",par,success.toString());
        return success.toString();
    }

    @PostMapping(value = "/deleteUnvan.ajax")
    public @ResponseBody
    String deleteUnvan(@RequestParam int unvanId) {
        Boolean success = mainService.deleteUnvan(unvanId);
        par="Unvan ID="+unvanId;
        log("deleteUnvan",par,success.toString());
        return success.toString();
    }

    @PostMapping(value = "/deleteLogin.ajax")
    public @ResponseBody
    String deleteLogin(@RequestParam int loginId) {
        Boolean success = mainService.deleteLogin(loginId);
        par="Login ID="+loginId;
        log("deleteLogin",par,success.toString());
        return success.toString();
    }

    @GetMapping(value = "/loadPersonel.ajax")
    public @ResponseBody
    String loadPersonel(int kaydedenId) {
        List<tblPersonel> list = mainService.loadPersonel(kaydedenId);
        StringBuilder stringBuilder = new StringBuilder();
        for (tblPersonel personel : list)
            stringBuilder.append("Personel unvani:"+personel.getUnvani().getUnvanAdi()+ ", adi:" +personel.getAdi()
                    + ", soyadi:" + personel.getSoyadi()+ ", telefon numarasi:" + personel.getTelefonNo()
                    + ", birimi:" +personel.getBirimi().getBirimAdi() + "\n");
        par="Kaydeden Id"+kaydedenId;
        log("loadPersonel",par,stringBuilder.toString());
        return stringBuilder.toString();
    }
    @GetMapping(value = "/loadPersonel2.ajax")
    public @ResponseBody
    String loadPersonel2(String tip, int tipId, int loginId) {
        List<tblPersonel> list = mainService.loadPersonelUnvanOrBirim(tip,tipId,loginId);
        StringBuilder stringBuilder = new StringBuilder();
        for (tblPersonel personel : list)
            stringBuilder.append("Personel unvani:"+personel.getUnvani().getUnvanAdi()+ ", adi:" +personel.getAdi()
                    + ", soyadi:" + personel.getSoyadi()+ ", telefon numarasi:" + personel.getTelefonNo()
                    + ", birimi:" +personel.getBirimi().getBirimAdi() + "\n");
        par="Tip:"+tip+", tip id:"+tipId+", login id:"+loginId;
        log("loadPersonel",par,stringBuilder.toString());
        return stringBuilder.toString();
    }
    @GetMapping(value = "/loadPersonelAdaGore.ajax")
    public @ResponseBody
    String loadPersonelAdaGore(String ad) {
        List<tblPersonel> list = mainService.loadPersonelAdaGore(ad);
        StringBuilder stringBuilder = new StringBuilder();
        for (tblPersonel personel : list)
            stringBuilder.append("Personel unvani:"+personel.getUnvani().getUnvanAdi()+ ", adi:" +personel.getAdi()
                    + ", soyadi:" + personel.getSoyadi()+ ", telefon numarasi:" + personel.getTelefonNo()
                    + ", birimi:" +personel.getBirimi().getBirimAdi() + "\n");
        par="Aad:"+ad;
        log("loadPersonel",par,stringBuilder.toString());
        return stringBuilder.toString();
    }

    @GetMapping(value = "/loadAdTelefonaGore.ajax")
    public @ResponseBody
    String loadAdTelefonaGore(String telNo) {
        List<String> list = mainService.loadAdTelfoneGore(telNo);
        StringBuilder stringBuilder = new StringBuilder();

        for (String ad : list)
            stringBuilder.append("Personel adi:" +ad+ "\n");
        par="Telefon numarası:"+telNo;
        log("loadPersonel",par,stringBuilder.toString());
        return stringBuilder.toString();
    }
    @GetMapping(value = "/loadTelefonAdaGore.ajax")
    public @ResponseBody
    String loadTelefonAdaGore(String ad) {
        List<String> list = mainService.loadTelefonAdaGore(ad);
        StringBuilder stringBuilder = new StringBuilder();
        for (String personel : list)
            stringBuilder.append("Personel telefon numarasi:" + personel+ "\n");
        par="AD:"+ad;
        log("loadPersonel",par,stringBuilder.toString());
        return stringBuilder.toString();
    }
  /*  @GetMapping(value = "/loadPersonel2.ajax")
    public @ResponseBody
    String loadPersonel(HttpServletRequest request, HttpServletResponse response) {

        int kaydedenId=Integer.parseInt(request.getParameter("kaydedenId"));
        List<tblPersonel> list = mainService.loadPersonel(kaydedenId);
        StringBuilder stringBuilder = new StringBuilder();
        for (tblPersonel personel : list)
            stringBuilder.append(personel.getUnvani().getUnvanAdi()+ "\n" +personel.getAdi()
                    + "\n" + personel.getSoyadi()+ "\n" + personel.getTelefonNo()
                    + "\n" +personel.getBirimi().getBirimAdi());
        par="Yok";
        log("loadPersonel",par,stringBuilder.toString());
        return stringBuilder.toString();
    }
*/
    @GetMapping(value = "/loadUnvan.ajax")
    public @ResponseBody
    String loadUnvan() {
        List<tblUnvan> list = mainService.loadUnvan();
        StringBuilder stringBuilder = new StringBuilder();
        for (tblUnvan unvan : list)
            stringBuilder.append(unvan.getUnvanAdi() + "\n");
        par="Yok";
        log("loadUnvan",par,stringBuilder.toString());
        return stringBuilder.toString();
    }


    @GetMapping(value = "/loadBirim.ajax")
    public @ResponseBody
    String loadBirim() {

            List<tblBirim> list = mainService.loadBirim();
            StringBuilder stringBuilder = new StringBuilder();
            for (tblBirim birim : list)
                stringBuilder.append(birim.getBirimAdi() + "\n" );
            par="Yok";
            log("loadBirim",par,stringBuilder.toString());
            return stringBuilder.toString();
    }

    @PostMapping(value = "/loadLogin.ajax")
    public @ResponseBody
    String loadLogin(@RequestParam String adi,
                     @RequestParam String sifre, HttpServletRequest request) {
        List<tblLogin> list = mainService.loadLogin(adi,sifre);
        StringBuilder stringBuilder = new StringBuilder();
        for (tblLogin login : list)
            stringBuilder.append("Kullanici adi:"+login.getKullaniciAdi()+", sifre:"+login.getSifre() + "\n");
        par="Yok";
        log("loadLogin",par,stringBuilder.toString());
        String result;
        if ( list.size()>0){
            tblLogin login = (tblLogin)list.get(0);
            int  loginId =login.getId();
            request.getSession().setAttribute("kullaniciAdi",login.getKullaniciAdi());
            result="true";
        }
        else result="false";
        return result;
    }
    @PostMapping(value = "/outLogin")
    public @ResponseBody void outLogin(HttpServletRequest request){
        request.getSession().setAttribute("kullaniciAdi",null);
    }

    @RequestMapping(value = {"/","/login.jsp"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

}
