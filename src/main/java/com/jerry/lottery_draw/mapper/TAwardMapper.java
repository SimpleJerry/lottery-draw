package com.jerry.lottery_draw.mapper;

import com.jerry.lottery_draw.domain.TAward;
import com.jerry.lottery_draw.domain.TAwardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAwardMapper {
    long countByExample(TAwardExample example);

    int deleteByExample(TAwardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAward record);

    int insertSelective(TAward record);

    List<TAward> selectByExample(TAwardExample example);

    TAward selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAward record, @Param("example") TAwardExample example);

    int updateByExample(@Param("record") TAward record, @Param("example") TAwardExample example);

    int updateByPrimaryKeySelective(TAward record);

    int updateByPrimaryKey(TAward record);
}