package com.lickvalue.change.service;


import com.lickvalue.change.model.VWapRequest;
import com.lickvalue.change.model.Vwap;
import com.lickvalue.change.repository.VWapRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VWapService {

    private VWapRepository vWapRepository;


    public List<Vwap> findAllVWap() {
        return vWapRepository.findAll();
    }

    public Vwap findVWapById(final Long id) throws NotFoundException {
        return vWapRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(""));
    }


    public Vwap createVWap(final VWapRequest vWapRequest) {

        Vwap vwap = new Vwap();
        vwap.setLongOff(vWapRequest.getLongSet());
        vwap.setShortOff(vWapRequest.getShortSet());
        vwap.setEmail("");
        vwap.setSymbol(vWapRequest.getSymbol());
        return vWapRepository.save(vwap);
    }



}
