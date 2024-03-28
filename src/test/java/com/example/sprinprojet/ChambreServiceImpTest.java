package com.example.sprinprojet;

import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.services.ChambreServiceImp;
import com.example.sprinprojet.services.IChambreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ChambreServiceImpTest {

@Autowired
    private IChambreService chambreService;

    @Test
    public void testGenerateQRCode() {
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(101L); // Utilisez 101L au lieu de 101

        try {
            byte[] qrCode = chambreService.generateQRCode(chambre);
            assertNotNull(qrCode);
        } catch (Exception e) {
            // Gérer l'exception
        }
    }




}
