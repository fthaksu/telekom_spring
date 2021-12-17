package com.works.restcontrollers;

import com.works.props.SecurData;
import com.works.repositories.SecurDataRepository;
import com.works.useTink.TinkEncDec;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class SecurDataRestController {

    String key128Bit = "ThWmZq4t7w!z%C*F";
    final SecurDataRepository sRepo;
    public SecurDataRestController(SecurDataRepository sRepo) {
        this.sRepo = sRepo;
    }


    @PostMapping("/securAdd")
    public Map<String, Object> studentAdd(@RequestBody SecurData securData) {
        Map<String, Object> hm = new LinkedHashMap<>();
        String chiperText = TinkEncDec.encrypt(key128Bit, securData.getCipherText(), securData.getAssociatedData() );
        securData.setCipherText(chiperText);
        securData.setAssociatedData("");
        hm.put("obj", sRepo.save(securData) );
        return hm;
    }

    @PostMapping("/securData")
    public Map<String, Object> securData(@RequestBody SecurData securData) {
        Map<String, Object> hm = new LinkedHashMap<>();

        Optional<SecurData> oSecur = sRepo.findById(securData.getSid());
        if (oSecur.isPresent() ) {
            String plainText = TinkEncDec.decrypt(key128Bit, oSecur.get().getCipherText(), securData.getAssociatedData() );
            hm.put("plainText", plainText);
        }else {
            hm.put("error", "Data Fail");
        }
        return hm;
    }

}
