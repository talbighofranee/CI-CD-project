package com.example.sprinprojet.contollers;

import com.example.sprinprojet.entity.Etudiant;
import com.example.sprinprojet.entity.Universite;
import com.example.sprinprojet.services.EtudiantServiceImp;
import com.example.sprinprojet.services.IEtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantController {
    EtudiantServiceImp iEtudiantService;

    @GetMapping("/retrieve-all-etudiants")

    public List<Etudiant>getEtudiantList(){
        return iEtudiantService.retrieveAllEtudiants();

    }


    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return iEtudiantService.addEtudiant(e);

    }
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    @Operation(description="recuperer etudiants par id")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200",description="found etudiant",
                    content={ @Content(mediaType  ="application/json" ,
                            schema = @Schema(implementation=Etudiant.class))}),
            @ApiResponse(responseCode="400",description=" non valid id", content=@Content),
            @ApiResponse(responseCode="404",description=" not found", content=@Content)
    }
    )

    public Etudiant retrieveEtudiant(    @Parameter(description = "id") @PathVariable("etudiant-id") Long etudiantId) {
        return iEtudiantService.retrieveEtudiant(etudiantId);
    }
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long idEtudiant) {
        iEtudiantService.removeEtudiant(idEtudiant);
    }

    @PutMapping("/update-etudiant/{id}")
    public Etudiant updateEtudiantByID(@PathVariable("id") Long idEtudiant, @RequestBody Etudiant updatedEtudiant) {
        return iEtudiantService.updateEtudiantAndReservations(idEtudiant, updatedEtudiant);
    }

    @PostMapping("/add-all-etudiants")
    public List<Etudiant> addAllEtudiants(@RequestBody List<Etudiant>etudiants){
        return iEtudiantService.addEtudiants(etudiants);

    }
    @PutMapping("/affecterEtRe/{idreservation}/{nom}/{prenom}")
    public Etudiant affecter(@PathVariable("prenom") String prenom ,@PathVariable("idreservation") String idReservation, @PathVariable("nom") String nom){
        return iEtudiantService.affecterEtudiantAReservation( nom,  prenom,  idReservation);



    }


}