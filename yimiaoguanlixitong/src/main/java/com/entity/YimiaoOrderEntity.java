package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 疫苗订单
 *
 * @author 
 * @email
 */
@TableName("yimiao_order")
public class YimiaoOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YimiaoOrderEntity() {

	}

	public YimiaoOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 疫苗
     */
    @ColumnInfo(comment="疫苗",type="int(11)")
    @TableField(value = "yimiao_id")

    private Integer yimiaoId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="预约时间",type="timestamp")
    @TableField(value = "yimiao_order_time")

    private Date yimiaoOrderTime;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "yimiao_order_types")

    private Integer yimiaoOrderTypes;


    /**
     * 接种地点
     */
    @ColumnInfo(comment="接种地点",type="int(11)")
    @TableField(value = "yimiao_didian_types")

    private Integer yimiaoDidianTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：疫苗
	 */
    public Integer getYimiaoId() {
        return yimiaoId;
    }
    /**
	 * 设置：疫苗
	 */

    public void setYimiaoId(Integer yimiaoId) {
        this.yimiaoId = yimiaoId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getYimiaoOrderTime() {
        return yimiaoOrderTime;
    }
    /**
	 * 设置：预约时间
	 */

    public void setYimiaoOrderTime(Date yimiaoOrderTime) {
        this.yimiaoOrderTime = yimiaoOrderTime;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getYimiaoOrderTypes() {
        return yimiaoOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setYimiaoOrderTypes(Integer yimiaoOrderTypes) {
        this.yimiaoOrderTypes = yimiaoOrderTypes;
    }
    /**
	 * 获取：接种地点
	 */
    public Integer getYimiaoDidianTypes() {
        return yimiaoDidianTypes;
    }
    /**
	 * 设置：接种地点
	 */

    public void setYimiaoDidianTypes(Integer yimiaoDidianTypes) {
        this.yimiaoDidianTypes = yimiaoDidianTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "YimiaoOrder{" +
            ", id=" + id +
            ", yimiaoId=" + yimiaoId +
            ", yonghuId=" + yonghuId +
            ", yimiaoOrderTime=" + DateUtil.convertString(yimiaoOrderTime,"yyyy-MM-dd") +
            ", yimiaoOrderTypes=" + yimiaoOrderTypes +
            ", yimiaoDidianTypes=" + yimiaoDidianTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
