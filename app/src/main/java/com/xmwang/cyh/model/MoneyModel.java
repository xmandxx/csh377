package com.xmwang.cyh.model;

import chihane.jdselector.ISelectAble;

/**
 * @Description:
 * @autour: CTS
 * @date: 2017/12/27
 */

public class MoneyModel implements ISelectAble {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public MoneyModel(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Object getArg() {
        return null;
    }
}
