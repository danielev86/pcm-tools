package com.developwithhomer.pcmexporter.converter;

import com.developwithhomer.pcmexporter.controller.bean.ScoutViewBean;
import com.developwithhomer.pcmexporter.repository.model.Scout;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-13T06:49:05+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.11 (Azul Systems, Inc.)"
)
@Component
public class ScoutConverterImpl implements ScoutConverter {

    @Override
    public ScoutViewBean convertToFront(Scout scout) {
        if ( scout == null ) {
            return null;
        }

        ScoutViewBean scoutViewBean = new ScoutViewBean();

        scoutViewBean.setId( scout.getId() );
        scoutViewBean.setFirstName( scout.getFirstName() );
        scoutViewBean.setLastName( scout.getLastName() );
        scoutViewBean.setWage( scout.getWage() );
        scoutViewBean.setContractEnd( scout.getContractEnd() );

        return scoutViewBean;
    }

    @Override
    public Scout convertToBack(Scout scout) {
        if ( scout == null ) {
            return null;
        }

        Scout scout1 = new Scout();

        scout1.setId( scout.getId() );
        scout1.setFirstName( scout.getFirstName() );
        scout1.setLastName( scout.getLastName() );
        scout1.setWage( scout.getWage() );
        scout1.setContractEnd( scout.getContractEnd() );
        scout1.setTeam( scout.getTeam() );

        return scout1;
    }

    @Override
    public Set<ScoutViewBean> convertToSet(Set<Scout> scout) {
        if ( scout == null ) {
            return null;
        }

        Set<ScoutViewBean> set = new HashSet<ScoutViewBean>( Math.max( (int) ( scout.size() / .75f ) + 1, 16 ) );
        for ( Scout scout1 : scout ) {
            set.add( convertToFront( scout1 ) );
        }

        return set;
    }
}
