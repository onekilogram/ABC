package com.netease.kg.pojo;

import lombok.Data;

import java.util.Objects;

/**
 * @author likeguo
 * @since 2019-09-23
 */
@Data
public class DependencyPO {

    private String groupId;

    private String artifactId;

    private String version;

    private String packaging;

    private String modelVersion;

    private String type;

    private String appName;

    private String scope;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DependencyPO that = (DependencyPO) o;
        return Objects.equals(groupId, that.groupId) &&
                Objects.equals(artifactId, that.artifactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, artifactId);
    }
}
