package com.entity.model;

import com.entity.YimiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 疫苗
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YimiaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 疫苗名称
     */
    private String yimiaoName;


    /**
     * 疫苗编号
     */
    private String yimiaoUuidNumber;


    /**
     * 疫苗照片
     */
    private String yimiaoPhoto;


    /**
     * 疫苗类型
     */
    private Integer yimiaoTypes;


    /**
     * 疫苗库存
     */
    private Integer yimiaoKucunNumber;


    /**
     * 疫苗介绍
     */
    private String yimiaoContent;


    /**
     * 逻辑删除
     */
    private Integer yimiaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
