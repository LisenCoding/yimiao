package com.dao;

import com.entity.YimiaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YimiaoView;

/**
 * 疫苗 Dao 接口
 *
 * @author 
 */
public interface YimiaoDao extends BaseMapper<YimiaoEntity> {

   List<YimiaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
