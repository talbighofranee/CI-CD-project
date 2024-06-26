package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.repository.BlocRepository;
import com.example.sprinprojet.repository.ChambreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class BlocServiceImp implements IBlocService{
    BlocRepository blocRepository;
     ChambreRepository chambreRepository;
    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save( b);
    }

    @Override
    public Bloc updateBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public void removeBloc(Long idBloc) {
blocRepository.deleteById(idBloc);
    }

  /*  public Bloc affecterChambresABloc (List<Long> numChambre, String nomBloc)
    {

        int i = 0;
        Bloc b = blocRepository.findByNomBloc(nomBloc);
        while(numChambre != null)
        {
            Chambre cc = chambreRepository.findByNumeroChambre(numChambre.get(i));
            cc.setBloc(b);
            i++;
            chambreRepository.save(cc);
        }
        return b;

    }*/
  public Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) {
      Bloc b = blocRepository.findByNomBloc(nomBloc);
      if (numChambre != null) {
          for (Long chambreId : numChambre) {
              Chambre cc = chambreRepository.findByNumeroChambre(chambreId);
              if (cc != null) {
                  cc.setBloc(b);
                  chambreRepository.save(cc);
              }
          }
      }
      return b;
  }
    @Scheduled(fixedRate = 60000)


    public void listeChambresParBloc() {
        List<Bloc> blocs = blocRepository.findAll();

        for (int i = 0; i < blocs.size(); i++) {
            log.info(blocs.get(i).getNomBloc() + ": " + blocs.get(i).getCapacitebloc());
            Set<Chambre> chambres = blocs.get(i).getChambres();

            chambres.stream().forEach(chambre -> log.info("num chambre est : " + chambre.getNumeroChambre() + " est de type " + chambre.getTypeC()));
        }
    }

  @Override
  public void trierBlocsParNomBloc(List<Bloc> blocs) {
    Collections.sort(blocs, Comparator.comparing(Bloc::getNomBloc));
  }
  /*@Scheduled(fixedRate = 20000) // Définir le taux en millisecondes (ici, chaque minute)
  public void checkAvailableRooms() {
    List<Bloc> blocs = blocRepository.findAll();

    for (Bloc bloc : blocs) {
      long capaciteBloc = bloc.getCapacitebloc();
      long nombreChambresCreees = bloc.getChambres().size();
      long chambresDisponibles = capaciteBloc - nombreChambresCreees;

      if (chambresDisponibles > 0) {
        log.info("Pour le bloc " + bloc.getNomBloc() + ", il reste " +
          chambresDisponibles + " chambre(s) disponible(s).");
      } else {
        log.info("Pour le bloc " + bloc.getNomBloc() + ", toutes les chambres sont occupées.");
      }
    }
  }*/

}

