package com.works.restcontrollers;

import com.works.useprofile.IConfig;
import com.works.utils.REnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ProfileRestController {

    final IConfig iConfig;
    public ProfileRestController(IConfig iConfig) {
        this.iConfig = iConfig;
    }

    @GetMapping("/profile")
    public Map<REnum, Object> profile() {
        Map<REnum, Object> hm = new LinkedHashMap<>();

        hm.put(REnum.status, true);
        hm.put(REnum.message, "status success");
        hm.put(REnum.result, iConfig.call() );

        return hm;
    }


}
