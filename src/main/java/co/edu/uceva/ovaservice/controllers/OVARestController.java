package co.edu.uceva.ovaservice.controllers;

import co.edu.uceva.ovaservice.model.entities.OVA;
import co.edu.uceva.ovaservice.model.services.IOVAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/OVA-service")

public class OVARestController {
    private IOVAService OVAService;

    @Autowired
    public OVARestController(IOVAService ovaService) {this.OVAService = ovaService;}

    @GetMapping("/ovas")
    public List<OVA> getOVAS() {return OVAService.findAll();}

    @GetMapping("/productos/page/{page}")
    public Page<OVA> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return OVAService.findAll(pageable);
    }

    @PostMapping("/ovas")
    public OVA save(@RequestBody OVA ova) {return OVAService.save(ova);}

    @DeleteMapping("/ovas")
    public void delete(@RequestBody OVA ova) {OVAService.delete(ova);}

    @PutMapping("/ovas")
    public OVA update(@RequestBody OVA ova){
        return OVAService.update(ova);
    }

    @GetMapping("/ovas/{id}")
    public OVA findById(@PathVariable("id") Long id) { return OVAService.findById(id);}
}
