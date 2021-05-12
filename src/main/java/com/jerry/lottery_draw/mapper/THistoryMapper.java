package com.jerry.lottery_draw.mapper;

import com.jerry.lottery_draw.domain.THistory;
import com.jerry.lottery_draw.domain.THistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface THistoryMapper {
    long countByExample(THistoryExample example);

    int deleteByExample(THistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(THistory record);

    int insertSelective(THistory record);

    List<THistory> selectByExample(THistoryExample example);

    THistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") THistory record, @Param("example") THistoryExample example);

    int updateByExample(@Param("record") THistory record, @Param("example") THistoryExample example);

    int updateByPrimaryKeySelective(THistory record);

    int updateByPrimaryKey(THistory record);
}