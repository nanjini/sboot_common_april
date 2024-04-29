package com.sboot.backend.common.api.controller;

import com.sboot.backend.common.business.model.UserProfile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Tag(name = "API", description = "Swagger API")
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private static final Logger logger = LogManager.getLogger(ProfileController.class);

    @Operation(summary = "01. Formal Parameters", description = "@RequestParam 어노테이션으로 컨트롤러에서 요청을 처리.")
    @Parameter(name = "uid", description = "UID")
    @GetMapping("/detail")
    public String getDetail(@RequestParam String uid) {
        logger.info("uid:{}", uid);
        return "ok";
    }

    @Operation(summary = "02. Entity Parameters", description = "Entity Class로부터 매개변수를 받을 때 동작X")
    @GetMapping("/detail-entity")
    public String getDetailEntity(UserProfile userProfile) {
        logger.info("UserProfile:{}", userProfile);
        return "ok";
    }

    @Operation(summary = "03. HttpServletRequest", description = "HttpServletRequest를 컨트롤러에서 처리.")
    @GetMapping("/detail-http-servlet") //postman body x-www-form-urlencoded
    public String getDetailHttpServlet(HttpServletRequest request) {
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        logger.info("name:{}", name);
        logger.info("sex:{}", sex);
        return "ok";
    }

    @Operation(summary = "04. @PathVariable annotation", description = "@PathVariable 어노테이션으로 컨트롤러에서 처리.")
    @GetMapping("detail/{name}/{sex}")
    public String getDetailPathVariable(@PathVariable String name, @PathVariable String sex) {
        logger.info("name:{}", name);
        logger.info("sex:{}", sex);
        return "ok";
    }

    @Operation(summary = "05. Array Parameters", description = "Array Parameter 처리.")
    @GetMapping("/detail-array") // Params key : names 반복 value 입력
    public String getDetailArray(String[] names) {
        Arrays.asList(names).forEach(name -> {
            System.out.println(name);
        });
        return "ok";
    }

    @Operation(summary = "06. Set Parameters", description = "Array와 요청은 동일하지만, @RequestParam List 형태로 처리.")
    @GetMapping("/detail-list") // array 동일
    public String getDetailList(@RequestParam List<String> names) {
        names.forEach(name -> {
            System.out.println(name);
        });
        return "ok";
    }

    @Operation(summary = "07.@RequestBody annotation", description = "@RequestBody 어노테이션으로 컨트롤러에서 처리.")
    @PostMapping("/save-requestBody")
    public String getDetailSave(@RequestBody UserProfile userProfile) {
        logger.info("name:{}", userProfile.getName());
        logger.info("phone:{}", userProfile.getPhone());
        return "ok";
    }

    @Operation(summary = "08. Map_RequestParam", description = "@RequestParam map형태로 처리.")
    @PostMapping("/save-map-requestParam")
    public String getDetailSaveMap(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        System.out.println(map.get("name"));
        return "ok";
    }

    @Operation(summary = "09. Map_RequestBody", description = "@RequestBody map형태로 처리.")
    @PostMapping("/save-map-requestBody")
    public String getDetailSaveMapbody(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        System.out.println(map.get("name"));
        return "ok";
    }
}
