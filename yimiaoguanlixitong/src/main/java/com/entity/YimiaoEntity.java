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
 * 疫苗
 *
 * @author 
 * @email
 */
@TableName("yimiao")
public class YimiaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YimiaoEntity() {

	}

	public YimiaoEntity(T t) {
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
     * 疫苗名称
     */
    @ColumnInfo(comment="疫苗名称",type="varchar(200)")
    @TableField(value = "yimiao_name")

    private String yimiaoName;


    /**
     * 疫苗编号
     */
    @ColumnInfo(comment="疫苗编号",type="varchar(200)")
    @TableField(value = "yimiao_uuid_number")

    private String yimiaoUuidNumber;


    /**
     * 疫苗照片
     */
    @ColumnInfo(comment="疫苗照片",type="varchar(200)")
    @TableField(value = "yimiao_photo")

    private String yimiaoPhoto;


    /**
     * 疫苗类型
     */
    @ColumnInfo(comment="疫苗类型",type="int(11)")
    @TableField(value = "yimiao_types")

    private Integer yimiaoTypes;


    /**
     * 疫苗库存
     */
    @ColumnInfo(comment="疫苗库存",type="int(11)")
    @TableField(value = "yimiao_kucun_number")

    private Integer yimiaoKucunNumber;


    /**
     * 疫苗介绍
     */
    @ColumnInfo(comment="疫苗介绍",type="text")
    @TableField(value = "yimiao_content")

    private String yimiaoContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "yimiao_delete")

    private Integer yimiaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：疫苗名称
	 */
    public String getYimiaoName() {
        return yimiaoName;
    }
    /**
	 * 设置：疫苗名称
	 */

    public void setYimiaoName(String yimiaoName) {
        this.yimiaoName = yimiaoName;
    }
    /**
	 * 获取：疫苗编号
	 */
    public String getYimiaoUuidNumber() {
        return yimiaoUuidNumber;
    }
    /**
	 * 设置：疫苗编号
	 */

    public void setYimiaoUuidNumber(String yimiaoUuidNumber) {
        this.yimiaoUuidNumber = yimiaoUuidNumber;
    }
    /**
	 * 获取：疫苗照片
	 */
    public String getYimiaoPhoto() {
        return yimiaoPhoto;
    }
    /**
	 * 设置：疫苗照片
	 */

    public void setYimiaoPhoto(String yimiaoPhoto) {
        this.yimiaoPhoto = yimiaoPhoto;
    }
    /**
	 * 获取：疫苗类型
	 */
    public Integer getYimiaoTypes() {
        return yimiaoTypes;
    }
    /**
	 * 设置：疫苗类型
	 */

    public void setYimiaoTypes(Integer yimiaoTypes) {
        this.yimiaoTypes = yimiaoTypes;
    }
    /**
	 * 获取：疫苗库存
	 */
    public Integer getYimiaoKucunNumber() {
        return yimiaoKucunNumber;
    }
    /**
	 * 设置：疫苗库存
	 */

    public void setYimiaoKucunNumber(Integer yimiaoKucunNumber) {
        this.yimiaoKucunNumber = yimiaoKucunNumber;
    }
    /**
	 * 获取：疫苗介绍
	 */
    public String getYimiaoContent() {
        return yimiaoContent;
    }
    /**
	 * 设置：疫苗介绍
	 */

    public void setYimiaoContent(String yimiaoContent) {
        this.yimiaoContent = yimiaoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getYimiaoDelete() {
        return yimiaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setYimiaoDelete(Integer yimiaoDelete) {
        this.yimiaoDelete = yimiaoDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Yimiao{" +
            ", id=" + id +
            ", yimiaoName=" + yimiaoName +
            ", yimiaoUuidNumber=" + yimiaoUuidNumber +
            ", yimiaoPhoto=" + yimiaoPhoto +
            ", yimiaoTypes=" + yimiaoTypes +
            ", yimiaoKucunNumber=" + yimiaoKucunNumber +
            ", yimiaoContent=" + yimiaoContent +
            ", yimiaoDelete=" + yimiaoDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
