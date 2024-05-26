package com.ray.common.model;

import java.io.Serializable;

/**
 * @Author: RayMind
 * @Date: 2024/5/25
 * @Description:
 */
public class User implements Serializable {
    private static final long serialVersionUID = 6422499406435868838L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
