package co.edu.uceva.ovaservice.domain.service;


import co.edu.uceva.ovaservice.domain.model.OVA;
import co.edu.uceva.ovaservice.domain.repository.IOVARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OVAServiceImpl implements IOVAService {

    IOVARepository OVARepository;

    @Autowired
    public OVAServiceImpl(IOVARepository ovaRepository) {this.OVARepository = ovaRepository;}

    @Override
    @Transactional
    public OVA save(OVA ova) {return OVARepository.save(ova); }

    @Override
    @Transactional
    public void delete(OVA ova) { OVARepository.delete(ova);
    }

    @Override
    @Transactional(readOnly = true)
    public OVA findById(Long id) { return OVARepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public OVA update(OVA ova) { return OVARepository.save(ova);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OVA> findAll() { return (List<OVA>) OVARepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OVA> findAll(Pageable pageable) { return OVARepository.findAll(pageable);
    }
}
