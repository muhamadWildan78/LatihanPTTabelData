package com.wildan;

import com.wildan.dao.MasterDataDao;
import com.wildan.facade.Konfigurasi;
import com.wildan.model.Departemen;
import com.wildan.services.VolumeServices;
import com.wildan.yutisna.KelasMain;
import com.wildan.yutisna.KelasMainService;
import com.wildan.yutisna.luaslmpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;
import java.util.List;

public class MainKelas {

    public String tes(String param, int nilai){
        return nilai+" ==> "+param;
    }
    public int nilai(){
        return 0;
    }
    public static void main(String[] abcd) {
//        MainKelas mainkelas = new MainKelas();
//        System.out.println(" tes"+mainkelas.tes("parameter",10));
//
//        KelasMain kelasMain = new KelasMain(20, 10);
//        System.out.println(" Luas = " + kelasMain.hitungLuas(20,30));
//
//        System.out.println(" Luas Dua = " + kelasMain.hitungLuasDua());
//
//        KelasMainService kelasMainService = new luaslmpl();
//        System.out.println(" Luas Segitiga Service = " + kelasMainService.hitungLuas(23,22));

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.register(Konfigurasi.class);
        ctx.refresh();

//        VolumeServices kelasMainService1 = ctx.getBean(VolumeServices.class);
//        System.out.println(" Volume = " + kelasMainService1.hitungVolume(10, 20, 10));


        MasterDataDao mdao = ctx.getBean(MasterDataDao.class);
//        mdao.getKoneksi();

        //insert departmen
        List<Departemen> listData = mdao.getDeplistJdbc();
        for (int i = 0; i < listData.size(); i++){
            System.out.println(listData.get(i).getId()+" : "+ listData.get(i).getName()
                    +" : "+listData.get(i).getDeskripsi());
        }

        //savedepartmen
//        Departemen dept = new Departemen();
//        dept.setName("Nama Muhamad Wildan");
//        dept.setDeskripsi("Ketua Bandung");
//        mdao.saveDepartmen(dept);
//       int iddept = mdao.saveDepartmen(dept);
//        System.out.println("id  = "+iddept);

//        //updatedepartmen
//        Departemen deptupdate = new Departemen();
//        deptupdate.setDeskripsi("POLISI");
//        deptupdate.setId(8);
//        deptupdate.setName("Wiil");
//        mdao.updateDepartmen(deptupdate);

        //delete
        mdao.delete(14);

        Departemen deptObject = mdao.getDeptListById(3);
        System.out.println(deptObject.getName()+" | "+deptObject.getDeskripsi());
    }
}