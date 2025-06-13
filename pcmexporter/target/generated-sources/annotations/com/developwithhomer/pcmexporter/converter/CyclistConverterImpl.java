package com.developwithhomer.pcmexporter.converter;

import com.developwithhomer.pcmexporter.controller.bean.CyclistViewBean;
import com.developwithhomer.pcmexporter.repository.model.Cyclist;
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
public class CyclistConverterImpl implements CyclistConverter {

    @Override
    public CyclistViewBean fromService(Cyclist cyclist) {
        if ( cyclist == null ) {
            return null;
        }

        CyclistViewBean cyclistViewBean = new CyclistViewBean();

        cyclistViewBean.setIdCyclist( cyclist.getIdCyclist() );
        cyclistViewBean.setLastName( cyclist.getLastName() );
        cyclistViewBean.setFirstName( cyclist.getFirstName() );
        cyclistViewBean.setBirthdate( cyclist.getBirthdate() );
        cyclistViewBean.setPopularity( cyclist.getPopularity() );
        cyclistViewBean.setSize( cyclist.getSize() );
        cyclistViewBean.setWeight( cyclist.getWeight() );
        cyclistViewBean.setCharacPlain( cyclist.getCharacPlain() );
        cyclistViewBean.setLimitPlain( cyclist.getLimitPlain() );
        cyclistViewBean.setCharacMountain( cyclist.getCharacMountain() );
        cyclistViewBean.setLimitMountain( cyclist.getLimitMountain() );
        cyclistViewBean.setCharacMediumMountain( cyclist.getCharacMediumMountain() );
        cyclistViewBean.setLimitMediumMountain( cyclist.getLimitMediumMountain() );
        cyclistViewBean.setCharachDownhill( cyclist.getCharachDownhill() );
        cyclistViewBean.setLimitDownhill( cyclist.getLimitDownhill() );
        cyclistViewBean.setCharacCobble( cyclist.getCharacCobble() );
        cyclistViewBean.setLimitCobble( cyclist.getLimitCobble() );
        cyclistViewBean.setCharacTimetrial( cyclist.getCharacTimetrial() );
        cyclistViewBean.setLimitTimetrial( cyclist.getLimitTimetrial() );
        cyclistViewBean.setCharacPrologue( cyclist.getCharacPrologue() );
        cyclistViewBean.setLimitPrologue( cyclist.getLimitPrologue() );
        cyclistViewBean.setCharacSprint( cyclist.getCharacSprint() );
        cyclistViewBean.setLimitSprint( cyclist.getLimitSprint() );
        cyclistViewBean.setCharacAcceleration( cyclist.getCharacAcceleration() );
        cyclistViewBean.setLimitAcceleration( cyclist.getLimitAcceleration() );
        cyclistViewBean.setCharacEndurance( cyclist.getCharacEndurance() );
        cyclistViewBean.setLimitEndurance( cyclist.getLimitEndurance() );
        cyclistViewBean.setCharacResistance( cyclist.getCharacResistance() );
        cyclistViewBean.setLimitResistance( cyclist.getLimitResistance() );
        cyclistViewBean.setCharacRecuperation( cyclist.getCharacRecuperation() );
        cyclistViewBean.setLimitRecuperation( cyclist.getLimitRecuperation() );
        cyclistViewBean.setCharacHill( cyclist.getCharacHill() );
        cyclistViewBean.setLimitHill( cyclist.getLimitHill() );
        cyclistViewBean.setCharacBaroudeur( cyclist.getCharacBaroudeur() );
        cyclistViewBean.setLimitBaroudeur( cyclist.getLimitBaroudeur() );

        return cyclistViewBean;
    }

    @Override
    public List<CyclistViewBean> fromServiceList(List<Cyclist> cyclists) {
        if ( cyclists == null ) {
            return null;
        }

        List<CyclistViewBean> list = new ArrayList<CyclistViewBean>( cyclists.size() );
        for ( Cyclist cyclist : cyclists ) {
            list.add( fromService( cyclist ) );
        }

        return list;
    }
}
