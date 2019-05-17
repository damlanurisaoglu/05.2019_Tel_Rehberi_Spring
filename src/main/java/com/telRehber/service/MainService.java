package com.telRehber.service;

import com.telRehber.dao.MainDAO;
import com.telRehber.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

;import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MainService {
    @Autowired
    private MainDAO mainDAO;

    @Transactional
    public Boolean saveOrUpdateBirim(int birimId,String birimAdi)
    {
        tblBirim birim = (tblBirim) mainDAO.loadObject(tblBirim.class,birimId);
        if (birim==null) {
            birim = new tblBirim();
            birim.setId(birimId);
        }
        birim.setBirimAdi(birimAdi);
        return mainDAO.saveOrUpdateObject(birim);

    }

    @Transactional
    public Boolean saveOrUpdateUnvan(int unvanId,String unvanAdi)
    {
        tblUnvan unvan = (tblUnvan) mainDAO.loadObject(tblUnvan.class,unvanId);
        if (unvan==null) {
            unvan = new tblUnvan();
            unvan.setId(unvanId);
        }
        unvan.setUnvanAdi(unvanAdi);
        return mainDAO.saveOrUpdateObject(unvan);

    }

    @Transactional
    public Boolean saveOrUpdateLogin(int loginId,String kullaniciAdi, String sifre)
    {
        tblLogin login = (tblLogin) mainDAO.loadObject(tblLogin.class,loginId);
        if (login==null) {
            login = new tblLogin();
            login.setId(loginId);
        }
        login.setKullaniciAdi(kullaniciAdi);
        login.setSifre(sifre);
        return mainDAO.saveOrUpdateObject(login);

    }

    @Transactional
    public Boolean saveOrUpdatePersonel(int personelId,String adi, String soyadi, int unvanId, int birimId, String telefon, int kaydedeId)
    {
        tblPersonel personel = (tblPersonel) mainDAO.loadObject(tblPersonel.class,personelId);
        if (personel==null) {
            personel = new tblPersonel();
            personel.setId(personelId);
        }

        tblUnvan unvan =(tblUnvan) mainDAO.loadObject(tblUnvan.class,unvanId);
        tblBirim birim=(tblBirim)mainDAO.loadObject(tblBirim.class,birimId);
        tblLogin login=(tblLogin)mainDAO.loadObject(tblLogin.class,kaydedeId);

        personel.setAdi(adi);
        personel.setSoyadi(soyadi);
        personel.setUnvani(unvan);
        personel.setBirimi(birim);
        personel.setTelefonNo(telefon);
        personel.setKaydedenId(login);
        return mainDAO.saveOrUpdateObject(personel);

    }

    @Transactional
    public Boolean deleteBirim(int birimId) {
        tblBirim birim = (tblBirim) mainDAO.loadObject(tblBirim.class, birimId);
        return mainDAO.removeObject(birim);
    }

    @Transactional
    public Boolean deleteUnvan(int unvanId) {
        tblUnvan unvan = (tblUnvan) mainDAO.loadObject(tblUnvan.class, unvanId);
        return mainDAO.removeObject(unvan);
    }

    @Transactional
    public Boolean deleteLogin(int loginId) {
        tblLogin login = (tblLogin) mainDAO.loadObject(tblLogin.class, loginId);
        return mainDAO.removeObject(login);
    }

    @Transactional
    public Boolean deletePersonel(int personelId) {
        tblPersonel personel = (tblPersonel) mainDAO.loadObject(tblPersonel.class, personelId);
        return mainDAO.removeObject(personel);
    }

    public List<tblPersonel> loadPersonel(int kaydedenId) {
        List<tblPersonel> list = mainDAO.loadPersonel(kaydedenId);
        return list;
    }

    public List<tblBirim> loadBirim() {
        List<tblBirim> list = mainDAO.loadBirim();
        return list;
    }

    public List<tblUnvan> loadUnvan() {
        List<tblUnvan> list = mainDAO.loadUnvan();
        return list;
    }

    public List<tblLogin> loadLogin(String adi, String sifre) {
        List<tblLogin> list = mainDAO.loadLogin(adi,sifre);
        return list;
    }
    public List<tblPersonel> loadPersonelUnvanOrBirim(String tip,int tipId,int loginId) {
        List<tblPersonel> list = mainDAO.loadPersonelUnvanOrBirim(tip,tipId,loginId);
        return list;
    }
    public List<tblPersonel> loadPersonelAdaGore(String ad) {
        List<tblPersonel> list = mainDAO.loadPersonelAdaGore(ad);
        return list;
    }
    public List<String> loadTelefonAdaGore(String ad) {
        List<String> list = mainDAO.loadTelefonAdaGore(ad);
        return list;
    }
    public List<String> loadAdTelfoneGore(String telNo) {
        List<String> list = mainDAO.loadAdTelefonaGore(telNo);
        return list;
    }
}
