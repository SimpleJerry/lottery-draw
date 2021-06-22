package com.jerry.lottery_draw.mapper;

import com.jerry.lottery_draw.domain.TJobResult;
import com.jerry.lottery_draw.domain.TJobResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TJobResultMapper {
    long countByExample(TJobResultExample example);

    int deleteByExample(TJobResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TJobResult record);

    int insertSelective(TJobResult record);

    List<TJobResult> selectByExample(TJobResultExample example);

    TJobResult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TJobResult record, @Param("example") TJobResultExample example);

    int updateByExample(@Param("record") TJobResult record, @Param("example") TJobResultExample example);

    int updateByPrimaryKeySelective(TJobResult record);

    int updateByPrimaryKey(TJobResult record);
}