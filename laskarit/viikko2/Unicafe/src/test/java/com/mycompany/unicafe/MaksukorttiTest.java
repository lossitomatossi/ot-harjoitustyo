package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void rahanLatausKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        assertTrue(kortti.saldo()== 20);
    }
    
    @Test
    public void saldoVaheneeJosRahaaOn() {
        kortti.otaRahaa(5);
        assertTrue(kortti.saldo() == 5);
    }
    
    @Test
    public void saldoEiVaheneJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(15);
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void josRahatRiittaTrue() {
        Kassapaate kassa = new Kassapaate();
        Boolean riittiko = kassa.syoEdullisesti(kortti);
        if (riittiko == true) {
            assertEquals(true, riittiko);
        } else {
            assertEquals(false, riittiko);
        }
    }
    
    @Test
    public void oikeaToString() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
