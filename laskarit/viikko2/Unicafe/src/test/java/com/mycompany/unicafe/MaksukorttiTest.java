package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertTrue(kortti.saldo() == 1000);
    }
    
    @Test
    public void rahanLatausKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        assertTrue(kortti.saldo()== 2000);
    }
    
    @Test
    public void saldoVaheneeJosRahaaOn() {
        kortti.otaRahaa(500);
        assertTrue(kortti.saldo() == 500);
    }
    
    @Test
    public void saldoEiVaheneJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(1500);
        assertTrue(kortti.saldo() == 1000);
    }
    
    @Test
    public void josRahatRiittaTrue() {
       
        Boolean riittiko = kortti.otaRahaa(2000);
        if (riittiko == true) {
            assertEquals(true, riittiko);
        } else {
            assertEquals(false, riittiko);
        }
    }
    
    @Test
    public void oikeaToString() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
}
