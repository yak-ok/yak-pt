package com.yak.controller;

import com.alibaba.fastjson.JSONObject;
import com.yak.utils.DateUtil;
import com.yak.utils.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/license")
@RestController
public class CheckLicenseController {

    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public String modifyLicense() {
        JSONObject json = new JSONObject();
        json.put("status", "200");
        json.put("msg", "验证过过");
        return json.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String checkLicense(@RequestParam(name = "license") String license) throws Exception {
        JSONObject json = new JSONObject();
        if (StringUtils.isBlank(license)) {
            json.put("status", "500");
            json.put("msg", "序列号错误");
            return json.toJSONString();
        }
        String date = EncryptUtil.decrypt(license);
        Date endDate = DateUtil.str2Date(date, "yyyyMMdd");
        if (endDate.before(DateUtil.getDate())) {
            json.put("status", 201);
            json.put("msg", "license已过期");
        }
        json.put("status", "200");
        json.put("msg", "验证过过");
        json.put("endDate", DateUtil.formatDate(endDate));
        return json.toJSONString();
    }

}
