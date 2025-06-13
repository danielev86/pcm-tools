package com.developwithhomer.pcmexporter.converter;

import com.developwithhomer.pcmexporter.controller.bean.GAMCarrerDataViewBean;
import com.developwithhomer.pcmexporter.repository.model.GAMCareerData;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-13T06:49:05+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.11 (Azul Systems, Inc.)"
)
@Component
public class GAMCarrerConverterImpl implements GAMCarrerConverter {

    @Override
    public List<GAMCarrerDataViewBean> fromServiceList(List<GAMCareerData> sourceList) {
        if ( sourceList == null ) {
            return null;
        }

        List<GAMCarrerDataViewBean> list = new ArrayList<GAMCarrerDataViewBean>( sourceList.size() );
        for ( GAMCareerData gAMCareerData : sourceList ) {
            list.add( gAMCareerDataToGAMCarrerDataViewBean( gAMCareerData ) );
        }

        return list;
    }

    protected GAMCarrerDataViewBean gAMCareerDataToGAMCarrerDataViewBean(GAMCareerData gAMCareerData) {
        if ( gAMCareerData == null ) {
            return null;
        }

        GAMCarrerDataViewBean gAMCarrerDataViewBean = new GAMCarrerDataViewBean();

        gAMCarrerDataViewBean.setUid( gAMCareerData.getUid() );
        gAMCarrerDataViewBean.setParameterCode( gAMCareerData.getParameterCode() );
        gAMCarrerDataViewBean.setParameterValue( gAMCareerData.getParameterValue() );

        return gAMCarrerDataViewBean;
    }
}
