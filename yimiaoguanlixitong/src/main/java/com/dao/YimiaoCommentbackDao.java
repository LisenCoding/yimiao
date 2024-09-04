package com.dao;

import com.entity.YimiaoCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YimiaoCommentbackView;

/**
 * 疫苗反馈 Dao 接口
 *
 * @author 
 */
public interface YimiaoCommentbackDao extends BaseMapper<YimiaoCommentbackEntity> {

   List<YimiaoCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
