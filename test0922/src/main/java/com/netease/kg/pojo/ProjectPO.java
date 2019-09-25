package com.netease.kg.pojo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author likeguo
 * @since 2019-09-23
 */
@Data
public class ProjectPO {

    private String projectName;

    private String branchName;

    private String path;

    private String fileName;

    private List<DependencyPO> data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectPO projectPO = (ProjectPO) o;
        return Objects.equals(projectName, projectPO.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName);
    }
}
