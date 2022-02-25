package com.wildan.services;

import com.wildan.yutisna.KelasMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class Volume implements VolumeServices {

    @Autowired
    private KelasMainService kelasMainService;

    @Override
    public double hitungVolume(double panjang, double lebar, double tinggi) {
        return kelasMainService.hitungLuas(panjang, lebar)*tinggi;
    }
}
