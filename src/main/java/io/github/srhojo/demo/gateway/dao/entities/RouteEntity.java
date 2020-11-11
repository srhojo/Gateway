package io.github.srhojo.demo.gateway.dao.entities;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "GW_ROUTES")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true, length = 200, nullable = false)
    private String name;

    @Column(name = "path", length = 150)
    private String path;

    @Column(name = "host", length = 300)
    private String host;

    @Column(name = "uri", length = 300, nullable = false)
    private String uri;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "GW_ROUTES_HEADER",
            joinColumns = {@JoinColumn(name = "route_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "header")
    @Column(name = "value")
    private Map<String,String> headers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
