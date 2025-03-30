package co.edu.uceva.ovaservice.model.services;


import co.edu.uceva.ovaservice.model.entities.OVA;
import co.edu.uceva.ovaservice.model.repositories.IOVARepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OVAServiceImpl implements IOVAService {

    IOVARepository OVARepository;

    public OVAServiceImpl(IOVARepository ovaRepository) {this.OVARepository = ovaRepository;}

    @Override
    public OVA save(OVA ova) {return OVARepository.save(ova); }

    @Override
    public void delete(OVA ova) { OVARepository.delete(ova);
    }

    @Override
    public OVA findById(Long id) { return OVARepository.findById(id).orElse(null);
    }

    @Override
    public OVA update(OVA ova) { return OVARepository.save(ova);
    }

    @Override
    public List<OVA> findAll() {
        return (List<OVA>) OVARepository.findAll();
    }
}
