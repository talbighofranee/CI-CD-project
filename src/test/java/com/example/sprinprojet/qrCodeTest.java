package com.example.sprinprojet;

import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.services.ChambreServiceImp;
import com.example.sprinprojet.services.IChambreService;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class qrCodeTest {

@Autowired
    private IChambreService chambreService;

    @Test
    public void testGenerateQRCode() {
        // Créer une chambre fictive pour le test
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(101L); // Utiliser 101L au lieu de 101

        try {
            byte[] qrCode = chambreService.generateQRCode(chambre);

            // Vérifier si le code QR généré n'est pas vide
            assertNotNull(qrCode);

            // Ajoutez d'autres assertions selon vos besoins
        } catch (IOException | WriterException e) {
            fail("Une exception ne devrait pas être levée lors de la génération du code QR : " + e.getMessage());
        }
    }
}
