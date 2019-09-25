package com.netease.kg.pojo;

import java.util.List;

/**
 * @author likeguo
 * @since 2019-09-23
 */

@lombok.Data
public class RootPO
{
    private List<ProjectPO> data;
    private int status;
}