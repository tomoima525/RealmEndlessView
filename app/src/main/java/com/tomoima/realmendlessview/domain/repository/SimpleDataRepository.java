package com.tomoima.realmendlessview.domain.repository;

import com.tomoima.realmendlessview.domain.models.SimpleData;

import java.util.List;

/**
 * Created by tomoaki on 2015/06/11.
 */
public interface SimpleDataRepository {

    List<SimpleData> getInitSimpleData();

    List<SimpleData> getNextData();

    void close();

}
