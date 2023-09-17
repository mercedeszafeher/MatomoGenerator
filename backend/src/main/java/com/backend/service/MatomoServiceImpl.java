package com.backend.service;

import com.backend.entity.Matomo;
import com.backend.dao.MatomoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MatomoServiceImpl {
    private final MatomoRepository matomoRepository;

    @Autowired
    public MatomoServiceImpl(MatomoRepository matomoRepository) {
        this.matomoRepository = matomoRepository;
    }

    public Matomo createMatomoResource(Matomo matomo) {
        // You can set the apiVersion and kind here if not provided in the request body
        return matomoRepository.save(matomo);
    }

    public Matomo getMatomoResource(String namespace, String name) {
        return matomoRepository.findByNameAndNamespace(name, namespace)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matomo resource not found"));
    }

    public Matomo saveMatomoResource(Matomo matomo) {
        return matomoRepository.save(matomo);
    }

    public List<Matomo> getAllMatomoResources() {
        return matomoRepository.findAll();
    }
}