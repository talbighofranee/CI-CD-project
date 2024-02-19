package com.example.sprinprojet.contollers;


import com.example.sprinprojet.entity.Universite;
import com.example.sprinprojet.services.IUniversiteService;

import com.example.sprinprojet.services.UniversiteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200") // Replace with the actual origin of your Angular app
@RestController
@AllArgsConstructor
@RequestMapping("/universite")

public class UniversiteController {
    private IUniversiteService universiteService;

    @Operation(description = "Get a list of all universities")
    @GetMapping("/retrieve-all-universities")
    public List<Universite> getUniversities() {
        return  universiteService.retrieveAllUniversites();

    }

    @Operation(description = "Get a university by ID")
    @GetMapping("/retrieve-university/{university-id}")
    public Universite retrieveUniversite(@PathVariable("university-id") Long universityId) {
        return universiteService.retrieveUniversites(universityId);
    }

    @Operation(description = "Add a new university")
    @PostMapping("/add-university")
    public Universite addUniversite(@RequestBody Universite e) {
     return universiteService.addUniversites(e);

    }

    @Operation(description = "Remove a university by ID")
    @DeleteMapping("/remove-university/{university-id}")
    public void removeUniversite(@PathVariable("university-id") Long foyerId) {
        universiteService.removeUniversites(foyerId);
    }

    @Operation(description = "Update a university")
    @PutMapping("/update-university")
    public Universite updateUniversite(@RequestBody Universite u) {
        return  universiteService.updateUniversites(u);

    }

    @Operation(description = "Set a foyer for a university")
    @PutMapping("/setfoyer/{idfoyer}/{nomuniversite}")
    public Universite setFoyer(@PathVariable ("idfoyer") Long foyerId,@PathVariable("nomuniversite") String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(foyerId,nomUniversite);

    }

    @Operation(description = "unSet a foyer for a university")
    @PutMapping("/unsetfoyer/{idfoyer}")
    public Universite unsetFoyer(@PathVariable ("idfoyer") Long foyerId) {
        return  universiteService.desaffecterFoyerAUniversite(foyerId);

    }

    @GetMapping("/rechercheParNom/{nomuniversite}")
    List<Universite> rechercheParNom(@PathVariable("nomuniversite") String nomUniversite){
        return universiteService.rechercheParNom(nomUniversite);
    }

    @GetMapping("/{id}/qr-code")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable Long id) {
        Universite universite = universiteService.retrieveUniversites(id);
        byte[] qrCode = universiteService.generateQRCode(universite);

        if (qrCode != null) {
            return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=qr-code.png").body(qrCode);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
