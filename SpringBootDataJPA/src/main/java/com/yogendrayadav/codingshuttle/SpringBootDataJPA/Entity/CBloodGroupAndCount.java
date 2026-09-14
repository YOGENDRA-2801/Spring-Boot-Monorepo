package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.type.BloodGroupType;
import lombok.Data;

@Data
public class CBloodGroupAndCount
{
    private final BloodGroupType bloodGroupType ;
    private final Long count ;
}
