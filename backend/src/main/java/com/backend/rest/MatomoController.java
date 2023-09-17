package com.backend.rest;

import com.backend.entity.Matomo;
import com.backend.request.MatomoRequest;
import com.backend.service.MatomoServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/matomo")
public class MatomoController {
    private final MatomoServiceImpl matomoServiceImpl;

    @Autowired
    public MatomoController(MatomoServiceImpl matomoServiceImpl) {
        this.matomoServiceImpl = matomoServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<Matomo> createMatomoResource(@RequestBody MatomoRequest matomoRequest) {
        // Access properties from matomoRequest to create Matomo resource
        Matomo matomo = new Matomo();
        matomo.setName(matomoRequest.getMetadata().getName());
        matomo.setNamespace(matomoRequest.getMetadata().getNamespace());
        matomo.setHost(matomoRequest.getSpec().getHost());

        Matomo savedMatomo = matomoServiceImpl.saveMatomoResource(matomo);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMatomo);
    }


    @GetMapping("/{namespace}/{name}")
    public ResponseEntity<Matomo> getMatomoResource(@PathVariable String namespace, @PathVariable String name) {
        Matomo matomo = matomoServiceImpl.getMatomoResource(namespace, name);
        return ResponseEntity.ok(matomo);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Matomo>> getAllMatomoResources() {
        List<Matomo> matomoList = matomoServiceImpl.getAllMatomoResources();
        return ResponseEntity.ok(matomoList);
    }

    @GetMapping("/generate-yaml")
    public ResponseEntity<String> generateYaml() throws JsonProcessingException {
        Matomo matomo = new Matomo();
        matomo.setName("matomo");
        matomo.setNamespace("matomo");
        matomo.setHost("matomo.minikube");

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        String yamlString = objectMapper.writeValueAsString(matomo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/yaml"));
        return new ResponseEntity<>(yamlString, headers, HttpStatus.OK);
    }
}