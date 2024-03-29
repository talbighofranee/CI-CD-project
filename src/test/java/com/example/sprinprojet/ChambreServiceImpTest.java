package com.example.sprinprojet;

import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.services.ChambreServiceImp;
import com.example.sprinprojet.services.IChambreService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class ChambreServiceImpTest {

    @InjectMocks
    private ChambreServiceImp chambreService;

   /* @Test
    public void testGenerateQRCode() {
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(101L); // Utilisez 101L au lieu de 101

        try {
            byte[] qrCode = chambreService.generateQRCode(chambre);
            assertNotNull(qrCode);
        } catch (Exception e) {
            fail("Une exception ne devrait pas être levée lors de la génération du code QR : " + e.getMessage());
        }
    }*/
}

