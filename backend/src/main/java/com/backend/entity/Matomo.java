package com.backend.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "matomo_resource")
public class Matomo {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "api_version")
    private String apiVersion = "glasskube.eu/v1alpha1";

    @Column(name = "kind")
    private String kind = "Matomo";

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "namespace")
    private String namespace;

    @Column(name = "host")
    private String host;

    public Matomo() {
    }

    public Matomo( String name, String namespace, String host) {
        this.name = name;
        this.namespace = namespace;
        this.host = host;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Matomo{" +
                "id=" + id +
                ", apiVersion='" + apiVersion + '\'' +
                ", kind='" + kind + '\'' +
                ", name='" + name + '\'' +
                ", namespace='" + namespace + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
