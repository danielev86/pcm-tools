package com.developwithhomer.pcmexporter.service.impl;

import com.developwithhomer.pcmexporter.constants.PCMConstants;
import com.developwithhomer.pcmexporter.controller.bean.GAMCarrerDataViewBean;
import com.developwithhomer.pcmexporter.controller.bean.UserDataViewBean;
import com.developwithhomer.pcmexporter.converter.GAMCarrerConverter;
import com.developwithhomer.pcmexporter.converter.TeamConverter;
import com.developwithhomer.pcmexporter.repository.GAMCareerDataRepository;
import com.developwithhomer.pcmexporter.repository.GAMUserDataRepository;
import com.developwithhomer.pcmexporter.repository.TeamRepository;
import com.developwithhomer.pcmexporter.repository.model.GAMCareerData;
import com.developwithhomer.pcmexporter.repository.model.GAMUserData;
import com.developwithhomer.pcmexporter.repository.model.Team;
import com.developwithhomer.pcmexporter.service.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.developwithhomer.pcmexporter.constants.PCMConstants.*;
import static java.util.Arrays.asList;

@Service("userDataService")
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GAMCareerDataRepository gamCareerDataRepository;

    @Autowired
    private GAMUserDataRepository gamUserDataRepository;

    @Autowired
    private TeamConverter teamConverter;

    @Autowired
    private GAMCarrerConverter gamCarrerConverter;

    @Value("${pcmuser}")
    private String userPcm;

    public UserDataViewBean getUserDetail(){
        GAMUserData userData = gamUserDataRepository.findByUserLogin(userPcm);
        UserDataViewBean user = new UserDataViewBean();
        user.setUserData(userPcm);
        if (userData != null){
            Optional<Team> team = teamRepository.findById(userData.getTeamId());
            user.setTeam(team.isPresent() ? teamConverter.serviceTo(team.get()) : null);
            List<GAMCareerData> gamCareerDataList = gamCareerDataRepository.findGAMCareerDataByParameterCodeIn(asList(SOLDE_GAM_DATA_CODE
                    , COUNTER_VICTORIES_GAM_DATA_CODE, COUNTER_MONUMENTS_GAM_DATA_CODE
                    , COUNTER_CLASSICS_GAM_DATA_CODE, DOTATION_GAM_DATA_CODE, PROPOINTS_GAM_DATA_CODE));
            List<GAMCarrerDataViewBean> gamCarrerList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(gamCareerDataList)){
                gamCarrerList = gamCarrerConverter.fromServiceList(gamCareerDataList);
            }
            user.setGamCarrerData(gamCarrerList);
        }
        return user;
    }

}
