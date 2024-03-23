package com.ems.pecheems;

import com.ems.pecheems.DTO.*;
import com.ems.pecheems.Entities.AssociationEntity;
import com.ems.pecheems.Mappers.AssociationMapper;
import com.ems.pecheems.Repositories.AssociationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PecheEmsApplicationTests {


    @Mock
    private AssociationRepository competitionRepository;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }



}
