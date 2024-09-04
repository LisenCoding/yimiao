
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 疫苗订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yimiaoOrder")
public class YimiaoOrderController {
    private static final Logger logger = LoggerFactory.getLogger(YimiaoOrderController.class);

    private static final String TABLE_NAME = "yimiaoOrder";

    @Autowired
    private YimiaoOrderService yimiaoOrderService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private GongzuorenyuanService gongzuorenyuanService;//工作人员
    @Autowired
    private YimiaoService yimiaoService;//疫苗
    @Autowired
    private YimiaoCommentbackService yimiaoCommentbackService;//疫苗反馈
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("工作人员".equals(role))
            params.put("gongzuorenyuanId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = yimiaoOrderService.queryPage(params);

        //字典表数据转换
        List<YimiaoOrderView> list =(List<YimiaoOrderView>)page.getList();
        for(YimiaoOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YimiaoOrderEntity yimiaoOrder = yimiaoOrderService.selectById(id);
        if(yimiaoOrder !=null){
            //entity转view
            YimiaoOrderView view = new YimiaoOrderView();
            BeanUtils.copyProperties( yimiaoOrder , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yimiaoOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 疫苗
            //级联表
            YimiaoEntity yimiao = yimiaoService.selectById(yimiaoOrder.getYimiaoId());
            if(yimiao != null){
            BeanUtils.copyProperties( yimiao , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYimiaoId(yimiao.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YimiaoOrderEntity yimiaoOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yimiaoOrder:{}",this.getClass().getName(),yimiaoOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yimiaoOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        yimiaoOrder.setCreateTime(new Date());
        yimiaoOrder.setInsertTime(new Date());
        yimiaoOrderService.insert(yimiaoOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YimiaoOrderEntity yimiaoOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yimiaoOrder:{}",this.getClass().getName(),yimiaoOrder.toString());
        YimiaoOrderEntity oldYimiaoOrderEntity = yimiaoOrderService.selectById(yimiaoOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yimiaoOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            yimiaoOrderService.updateById(yimiaoOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YimiaoOrderEntity> oldYimiaoOrderList =yimiaoOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yimiaoOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<YimiaoOrderEntity> yimiaoOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YimiaoOrderEntity yimiaoOrderEntity = new YimiaoOrderEntity();
//                            yimiaoOrderEntity.setYimiaoId(Integer.valueOf(data.get(0)));   //疫苗 要改的
//                            yimiaoOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yimiaoOrderEntity.setYimiaoOrderTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            yimiaoOrderEntity.setYimiaoOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            yimiaoOrderEntity.setYimiaoDidianTypes(Integer.valueOf(data.get(0)));   //接种地点 要改的
//                            yimiaoOrderEntity.setInsertTime(date);//时间
//                            yimiaoOrderEntity.setCreateTime(date);//时间
                            yimiaoOrderList.add(yimiaoOrderEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        yimiaoOrderService.insertBatch(yimiaoOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yimiaoOrderService.queryPage(params);

        //字典表数据转换
        List<YimiaoOrderView> list =(List<YimiaoOrderView>)page.getList();
        for(YimiaoOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YimiaoOrderEntity yimiaoOrder = yimiaoOrderService.selectById(id);
            if(yimiaoOrder !=null){


                //entity转view
                YimiaoOrderView view = new YimiaoOrderView();
                BeanUtils.copyProperties( yimiaoOrder , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(yimiaoOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    YimiaoEntity yimiao = yimiaoService.selectById(yimiaoOrder.getYimiaoId());
                if(yimiao != null){
                    BeanUtils.copyProperties( yimiao , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYimiaoId(yimiao.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YimiaoOrderEntity yimiaoOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yimiaoOrder:{}",this.getClass().getName(),yimiaoOrder.toString());
            YimiaoEntity yimiaoEntity = yimiaoService.selectById(yimiaoOrder.getYimiaoId());
            if(yimiaoEntity == null){
                return R.error(511,"查不到该疫苗");
            }
            // Double yimiaoNewMoney = yimiaoEntity.getYimiaoNewMoney();

            if(false){
            }
//            else if((yimiaoEntity.getYimiaoKucunNumber() -yimiaoOrder.getBuyNumber())<0){
//                return R.error(511,"购买数量不能大于库存数量");
//            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            yimiaoOrder.setYimiaoOrderTypes(101); //设置订单状态为已预约疫苗
            yimiaoOrder.setYonghuId(userId); //设置订单支付人id
            yimiaoOrder.setInsertTime(new Date());
            yimiaoOrder.setCreateTime(new Date());
                yimiaoService.updateById(yimiaoEntity);
                yimiaoOrderService.insert(yimiaoOrder);//新增订单


            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request) throws ParseException {
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");

//            Integer yimiaoDidianTypes = Integer.valueOf(String.valueOf(params.get("yimiaoDidianTypes")));//接种地点

        String data = String.valueOf(params.get("yimiaos"));
        String yimiaoOrderTime = String.valueOf(params.get("yimiaoOrderTime"));
        String yimiaoDidianTypes = String.valueOf(params.get("yimiaoDidianTypes"));
        Integer integer = Integer.valueOf(yimiaoDidianTypes);

        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> yimiaos = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<YimiaoOrderEntity> yimiaoOrderList = new ArrayList<>();
        //商品表
        List<YimiaoEntity> yimiaoList = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : yimiaos) {
           //取值
            Integer yimiaoId = Integer.valueOf(String.valueOf(map.get("yimiaoId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            YimiaoEntity yimiaoEntity = yimiaoService.selectById(yimiaoId);//购买的商品
            String id = String.valueOf(map.get("id"));

            //判断商品的库存是否足够
            if(yimiaoEntity.getYimiaoKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(yimiaoEntity.getYimiaoName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                yimiaoEntity.setYimiaoKucunNumber(yimiaoEntity.getYimiaoKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            YimiaoOrderEntity yimiaoOrderEntity = new YimiaoOrderEntity<>();

            //赋值订单信息
            yimiaoOrderEntity.setYimiaoId(yimiaoId);//疫苗
                        yimiaoOrderEntity.setYonghuId(userId);//用户
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            yimiaoOrderEntity.setYimiaoOrderTime(sdf.parse(yimiaoOrderTime));//预约时间 ？？？？？？
            yimiaoOrderEntity.setYimiaoOrderTypes(101);//订单类型
            yimiaoOrderEntity.setYimiaoDidianTypes(integer);//接种地点 ？？？？？？
            yimiaoOrderEntity.setInsertTime(new Date());//订单创建时间
            yimiaoOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
//            if(yimiaoOrderPaymentTypes == 1){//余额支付
                //计算金额
//                Double money = new BigDecimal(yimiaoEntity.getYimiaoNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

//                if(yonghuEntity.getNewMoney() - money <0 ){
//                    return R.error("余额不足,请充值！！！");
//                }else{
//                    //计算所获得积分
//                    Double buyJifen =0.0;
//
//
//                    yimiaoOrderEntity.setYimiaoOrderTruePrice(money);
//
//                }
//            }
            yimiaoOrderList.add(yimiaoOrderEntity);
            yimiaoList.add(yimiaoEntity);

        }
        yimiaoOrderService.insertBatch(yimiaoOrderList);
        yimiaoService.updateBatchById(yimiaoList);
        yonghuService.updateById(yonghuEntity);

        return R.ok();
    }


    /**
    * 取消预约
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            YimiaoOrderEntity yimiaoOrder = yimiaoOrderService.selectById(id);//当前表service
            Integer yimiaoId = yimiaoOrder.getYimiaoId();
            if(yimiaoId == null)
                return R.error(511,"查不到该疫苗");
            YimiaoEntity yimiaoEntity = yimiaoService.selectById(yimiaoId);
            if(yimiaoEntity == null)
                return R.error(511,"查不到该疫苗");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            Double zhekou = 1.0;

                //计算金额
//                Double money = yimiaoEntity.getYimiaoNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;



//            yimiaoEntity.setYimiaoKucunNumber(yimiaoEntity.getYimiaoKucunNumber() + buyNumber);


            yimiaoOrder.setYimiaoOrderTypes(102);//设置订单状态为已取消预约
            yimiaoOrderService.updateById(yimiaoOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            yimiaoService.updateById(yimiaoEntity);//更新订单中疫苗的信息

            return R.ok();
    }

    /**
    * 反馈
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer yimiaoCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            YimiaoOrderEntity yimiaoOrder = yimiaoOrderService.selectById(id);
        if(yimiaoOrder == null)
            return R.error(511,"查不到该订单");
        Integer yimiaoId = yimiaoOrder.getYimiaoId();
        if(yimiaoId == null)
            return R.error(511,"查不到该疫苗");

        YimiaoCommentbackEntity yimiaoCommentbackEntity = new YimiaoCommentbackEntity();
            yimiaoCommentbackEntity.setId(id);
            yimiaoCommentbackEntity.setYimiaoId(yimiaoId);
            yimiaoCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            yimiaoCommentbackEntity.setYimiaoCommentbackText(commentbackText);
            yimiaoCommentbackEntity.setInsertTime(new Date());
            yimiaoCommentbackEntity.setReplyText(null);
            yimiaoCommentbackEntity.setUpdateTime(null);
            yimiaoCommentbackEntity.setCreateTime(new Date());
            yimiaoCommentbackService.insert(yimiaoCommentbackEntity);

            yimiaoOrder.setYimiaoOrderTypes(105);//设置订单状态为已反馈
            yimiaoOrderService.updateById(yimiaoOrder);//根据id更新
            return R.ok();
    }

    /**
     * 同意预约
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        YimiaoOrderEntity  yimiaoOrderEntity = yimiaoOrderService.selectById(id);
        yimiaoOrderEntity.setYimiaoOrderTypes(103);//设置订单状态为已同意预约
        yimiaoOrderService.updateById( yimiaoOrderEntity);

        return R.ok();
    }


    /**
     * 接种疫苗
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        YimiaoOrderEntity  yimiaoOrderEntity = yimiaoOrderService.selectById(id);
        yimiaoOrderEntity.setYimiaoOrderTypes(104);//设置订单状态为接种疫苗
        yimiaoOrderService.updateById( yimiaoOrderEntity);
        return R.ok();
    }

}

