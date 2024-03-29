package com.example;

import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.services.ChambreServiceImp;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ChambreTest {

    @InjectMocks
    private ChambreServiceImp chambreService; // Injecte la classe à tester

    @Mock
    private QRCodeWriter qrCodeWriter; // Mock pour le QRCodeWriter

    @Test
    void generateQRCodeTest() throws IOException, WriterException {
        Chambre chambre = new Chambre(); // Créez une instance de Chambre à utiliser pour le test

        // Appeler la méthode à tester
        byte[] qrCode = chambreService.generateQRCode(chambre);

        // Vérifier que le résultat n'est pas nul
        assertNotNull(qrCode);
        // Autres assertions à ajouter si nécessaire
    }
}
