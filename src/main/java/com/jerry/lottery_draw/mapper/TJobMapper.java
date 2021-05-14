package com.jerry.lottery_draw.mapper;

import com.jerry.lottery_draw.domain.TJob;
import com.jerry.lottery_draw.domain.TJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TJobMapper {
    long countByExample(TJobExample example);

    int deleteByExample(TJobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TJob record);

    int insertSelective(TJob record);

    List<TJob> selectByExample(TJobExample example);

    TJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TJob record, @Param("example") TJobExample example);

    int updateByExample(@Param("record") TJob record, @Param("example") TJobExample example);

    int updateByPrimaryKeySelective(TJob record);

    int updateByPrimaryKey(TJob record);

    List<String> getJobIds(String groupId);
}