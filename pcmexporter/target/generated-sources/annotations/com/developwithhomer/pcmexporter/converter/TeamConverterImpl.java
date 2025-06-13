package com.developwithhomer.pcmexporter.converter;

import com.developwithhomer.pcmexporter.controller.bean.CyclistViewBean;
import com.developwithhomer.pcmexporter.controller.bean.TeamViewBean;
import com.developwithhomer.pcmexporter.repository.model.Cyclist;
import com.developwithhomer.pcmexporter.repository.model.Team;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-13T06:49:05+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.11 (Azul Systems, Inc.)"
)
@Component
public class TeamConverterImpl implements TeamConverter {

    @Autowired
    private CyclistConverter cyclistConverter;
    @Autowired
    private ScoutConverter scoutConverter;

    @Override
    public TeamViewBean serviceTo(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamViewBean teamViewBean = new TeamViewBean();

        teamViewBean.setId( team.getId() );
        teamViewBean.setShortName( team.getShortName() );
        teamViewBean.setName( team.getName() );
        teamViewBean.setLicensed( team.getLicensed() );
        teamViewBean.setScout( scoutConverter.convertToSet( team.getScout() ) );
        teamViewBean.setCyclists( cyclistSetToCyclistViewBeanSet( team.getCyclists() ) );

        return teamViewBean;
    }

    @Override
    public List<TeamViewBean> serviceToList(List<Team> team) {
        if ( team == null ) {
            return null;
        }

        List<TeamViewBean> list = new ArrayList<TeamViewBean>( team.size() );
        for ( Team team1 : team ) {
            list.add( serviceTo( team1 ) );
        }

        return list;
    }

    protected Set<CyclistViewBean> cyclistSetToCyclistViewBeanSet(Set<Cyclist> set) {
        if ( set == null ) {
            return null;
        }

        Set<CyclistViewBean> set1 = new HashSet<CyclistViewBean>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Cyclist cyclist : set ) {
            set1.add( cyclistConverter.fromService( cyclist ) );
        }

        return set1;
    }
}
