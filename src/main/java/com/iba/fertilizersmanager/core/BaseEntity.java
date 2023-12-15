package com.iba.fertilizersmanager.core;







public interface BaseEntity<ID> {
    public abstract ID getId();
    public  abstract void setId(ID email);
}
