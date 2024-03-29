package com.example.sprinprojet;

import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.services.ChambreServiceImp;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ChambreJUnitTest {

    @Test
    void generateQRCodeTest() {
        // Créer une instance de ChambreServiceImp
        ChambreServiceImp chambreService = new ChambreServiceImp(null, null, null, null, null);

        // Créer une instance de Chambre à utiliser pour le test
        Chambre chambre = new Chambre();

        try {
            // Appeler la méthode à tester
            byte[] qrCode = chambreService.generateQRCode(chambre);

            // Vérifier que le résultat n'est pas nul
            assertNotNull(qrCode);

            // Vérifier que le tableau de bytes n'est pas vide
            assertTrue(qrCode.length > 0);

            // Autres assertions à ajouter si nécessaire
        } catch (IOException | WriterException e) {
            // Gérer les exceptions, par exemple en échouant le test si une exception est lancée
            fail("Erreur lors de la génération du QR code : " + e.getMessage());
        }
    }
}
