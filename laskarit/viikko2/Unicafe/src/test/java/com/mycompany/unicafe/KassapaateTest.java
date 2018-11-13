package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa; 
    Maksukortti kortti500;
    Maksukortti kortti100;
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti500 = new Maksukortti(500);
        kortti100 = new Maksukortti(100);
    }
    
    @Test
    public void alussaRahaaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void alussaEdullisiaLounaitaNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void alussaMaukkaitaLounaitaNolla() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEdullisestiRahamaaraKasvaa() {
        kassa.syoEdullisesti(500);
        assertEquals(100000 + 240, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisostoEdullisestiTakaisinOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }
    
    @Test
    public void kateisostoMaukkaastiRahamaaraKasvaa() {
        kassa.syoMaukkaasti(500);
        assertEquals(100000 + 400, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisostoMaukkaastiTakaisinOikein() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void kateisostoEdullisestiRahaEiRiita() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisostoEdullisestiRahaEiRiita2() {
        assertEquals(200, kassa.syoMaukkaasti(200));
    }
    
    @Test
    public void kateisostoMaukkaastiRahaEiRiita() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisostoMaukkaastiRahaEiRiita2() {
        assertEquals(200, kassa.syoMaukkaasti(200));
    }
    
    @Test
    public void kateisostoEdullisetLisaantyy() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoMaukkaatLisaantyy() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaTarpeeksiTrueEdullinen() {
        assertEquals(true, kassa.syoEdullisesti(kortti500));
    }
    
    @Test
    public void josKortillaTarpeeksiVeloitusEdullinen() {
        kassa.syoEdullisesti(kortti500);
        assertEquals(260, kortti500.saldo());
    }
    
    @Test
    public void kortillaTarpeeksiEdullisetKasvaa() {
        kassa.syoEdullisesti(kortti500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaTarpeeksiTrueMaukas() {
        assertEquals(true, kassa.syoMaukkaasti(kortti500));
    }
    
    @Test
    public void josKortillaTarpeeksiVeloitusMaukas() {
        kassa.syoMaukkaasti(kortti500);
        assertEquals(100, kortti500.saldo());
    }
    
    @Test
    public void kortillaTarpeeksiMaukkaatKasvaa() {
        kassa.syoMaukkaasti(kortti500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiTarpeeksiEdullinenFalse() {
        assertEquals(false, kassa.syoEdullisesti(kortti100));
    }
    
    @Test
    public void kortillaEiTarpeeksiMaukasFalse() {
        assertEquals(false, kassa.syoMaukkaasti(kortti100));
    }
    
    @Test
    public void kortillaEiTarpeeksiEdullisetEiKasva() {
        kassa.syoEdullisesti(kortti100);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiTarpeeksiMaukkaatEiKasva() {
        kassa.syoMaukkaasti(kortti100);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiTarpeeksiEdullinenRahaEiMuutu() {
        kassa.syoEdullisesti(kortti100);
        assertEquals(100, kortti100.saldo());
    }
    
    @Test
    public void kortillaEiTarpeeksiMaukasRahaEiMuutu() {
        kassa.syoMaukkaasti(kortti100);
        assertEquals(100, kortti100.saldo());
    }
    
    @Test
    public void kassanRahaEiMuutuKortillaEdullinen() {
        kassa.syoEdullisesti(kortti500);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void kassanRahaEiMuutuKortillaMaukas() {
        kassa.syoMaukkaasti(kortti500);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleLatausKortinSaldoMuuttuu() {
        kassa.lataaRahaaKortille(kortti500, 500);
        assertEquals(1000, kortti500.saldo());
    }
    
    @Test
    public void kortilleLatausKassanSaldoMuuttuu() {
        kassa.lataaRahaaKortille(kortti500, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiLadataJosAlleNolla() {
        kassa.lataaRahaaKortille(kortti500, -1);
        assertEquals(500, kortti500.saldo());
    }
}
