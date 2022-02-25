package com.wildan.yutisna;

import org.springframework.stereotype.Component;

@Component
public class luaslmpl implements KelasMainService{

    @Override
    public double hitungLuas(double panjang, double lebar) {
        return (panjang*lebar)/3;
    }

    @Override
    public double hitungLuasDua() {
        return 0;
    }
}
