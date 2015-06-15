package com.tomoima.realmendlessview.event;

import com.tomoima.realmendlessview.domain.models.SimpleData;

import java.util.List;

/**
 * Created by tomoaki on 2015/06/12.
 */
public class OnSimpleDataLoadedEvent {
    public List<SimpleData> simpleDataList;
    public int response;
    public OnSimpleDataLoadedEvent(List<SimpleData> simpleDataList, int response){
        this.simpleDataList = simpleDataList;
        this.response = response;
    }
}
