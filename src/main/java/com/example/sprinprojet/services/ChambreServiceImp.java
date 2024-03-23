package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.TypeChambre;
import com.example.sprinprojet.repository.BlocRepository;
import com.example.sprinprojet.repository.ChambreRepository;
import com.example.sprinprojet.repository.FoyerRepository;
import com.example.sprinprojet.repository.ReservationRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


@Service
@AllArgsConstructor
public class ChambreServiceImp implements IChambreService {
   ChambreRepository chambreRepository ;
   BlocRepository blocRepository;
   FoyerRepository foyerRepository;
   ReservationRepository reservationRepository;
  private final JavaMailSender javaMailSender;

   @Override
   public List<Chambre> retrieveAllChambres() {
      return chambreRepository.findAll();
   }

   @Override
   public Chambre addChambre(Chambre ch) {
      return chambreRepository.save(ch);
   }

   @Override
   public Chambre updateChambre(Chambre ch) {
      return chambreRepository.save(ch);
   }

   @Override
   public Chambre retrieveChambre(Long idChambre) {
      return chambreRepository.findById(idChambre).get();
   }

   @Override
   public void removeChambre(Long idChambre) {
      chambreRepository.deleteById(idChambre);

   }

    @Override
    public void pourcentageChambreParTypeChambre(Logger logger) {

    }


    public Set<Chambre>getChambreParNomBloc(String nomb){
      Bloc b =blocRepository.findByNomBloc(nomb);
        return b.getChambres();

}


    public byte[] generateQRCode(Chambre chambre) throws IOException, WriterException {
        String chambreDetails = "Chambre: " + chambre.getNumeroChambre();

        EnumMap<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
        hintMap.put(EncodeHintType.ERROR_CORRECTION, "L");
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(chambreDetails, BarcodeFormat.QR_CODE, 300, 300, hintMap);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }





//  @Scheduled(cron = "0 1 * * * *")
  //public void pourcentageChambreParTypeChambre(Logger logger) {

    //  Map<TypeChambre, List<Chambre>> chambreByType = chambreRepository.findAll()
      //        .stream()
        //      .collect(Collectors.groupingBy(Chambre::getTypeC));


      //long totalchbres = chambreRepository.count();
      //chambreByType.forEach((type, chbres) -> {
        //  double pourcentage = (chbres.size() * 100.0) / totalchbres;
         // logger.info(String.format("Total chambres: %d, Pourcentage chambres de type %s: %.2f%%", totalchbres, type, pourcentage));
     // });
  //}
  public List<Chambre> getChambresParNomBloc(String nomBloc) {
    // Use the BlocRepository to find the Bloc entity by nomBloc
    Bloc bloc = blocRepository.findByNomBloc(nomBloc);

    if (bloc != null) {
      // If the Bloc is found, retrieve the associated Chambres
      Set<Chambre> chambres = bloc.getChambres();
      return new ArrayList<>(chambres); // Convert the Set to a List
    } else {
      // Handle the case where no Bloc is found by the given nomBloc
      return Collections.emptyList(); // Return an empty list or handle the situation as needed
    }
  }

  public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
    return chambreRepository.countByTypeCAndBloc_IdBloc(type, idBloc);
  }





  @Override
  public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);
    javaMailSender.send(message);
  }
  public Chambre affecterChambreABloc(Long chambreId, Long blocId) {
    Chambre chambre = chambreRepository.findById(chambreId).orElse(null);
    Bloc bloc = blocRepository.findById(blocId).orElse(null);

    if (chambre != null && bloc != null) {
      chambre.setBloc(bloc);
      chambreRepository.save(chambre);
      String to = "emna.felfel@esprit.tn"; // replace with the recipient's email
      String subject = "Chambre Assigned to Bloc";
      String body = "Chambre with ID " + chambreId + " has been assigned to Bloc " + bloc.getNomBloc();

      sendEmail(to, subject, body);
    }
    return chambre;
  }
}
