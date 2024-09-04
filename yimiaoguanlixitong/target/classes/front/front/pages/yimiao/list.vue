<template>
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">

	<view class="cu-bar bg-white search" :style="[{top:CustomBar + 'px'}]">
        <view class="search-form round">
				<input :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
						v-model="searchForm.yimiaoName" type="text" placeholder="疫苗名称"></input>
		</view>

        <view class="action">
            <view :style="{width:'40rpx',borderRadius:'40rpx 0 0 40rpx',height:'80rpx',backgroundColor:btnColor[0],borderColor:btnColor[0]}">
            </view>
            <view @tap="search" :style="{padding:'0 10rpx',lineHeight:'80rpx',width:'auto',height:'80rpx',fontSize:'28rpx',color:'rgba(239, 239, 239, 1)',backgroundColor:btnColor[0],borderColor:btnColor[0]}">
                搜索
            </view>
            <view v-if="isAuth('yimiao','新增')" @click="onAddTap()"
                  :style="{padding:'0 10rpx',lineHeight:'80rpx',width:'auto',height:'80rpx',fontSize:'28rpx',color:'rgba(239, 239, 239, 1)',backgroundColor:btnColor[0],borderColor:btnColor[0]}">
                新增
            </view>
            <view @click="showDrawer()"
                  :style="{padding:'0 10rpx',lineHeight:'80rpx',width:'auto',height:'80rpx',fontSize:'28rpx',color:'rgba(239, 239, 239, 1)',backgroundColor:btnColor[0],borderColor:btnColor[0]}">
                类型
            </view>
            <view :style="{width:'40rpx',borderRadius:'0 40rpx 40rpx 0',height:'80rpx',backgroundColor:btnColor[0],borderColor:btnColor[0]}">
            </view>
        </view>
	</view>
	<view style="display: flex;flex-wrap: wrap">
            <uni-drawer ref="showRight" mode="left" :maskClick="true" :width="100" :mask-click="true">
                <scroll-view scroll-y="true" class="tabView" :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"10rpx 0 0 ","borderColor":"rgba(0,0,0,1)","borderRadius":"0","borderWidth":"0","borderStyle":"solid","height":"auto"}'>
                    <view style="display: flex;">
                        <view style="display: flex;flex-direction: column;width: 100%;">
                            <view :style='yimiaoTypes===item.codeIndex?sactiveItem:sitem' class="tab"
                                  v-for="(item,index) in yimiaoTypesList" :key="index" :class="yimiaoTypes===item.codeIndex?'tabActive':''"
                                  @tap="yimiaoTypesCategoryClick(item.codeIndex)">
                                {{item.indexName}}
                            </view>
                        </view>
                    </view>
                </scroll-view>
            </uni-drawer>
<view class="list-box4"
      :style='{"padding":"24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(255,0,0,0)","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"auto"}'
      style="display:flex;flex-wrap:wrap;box-sizing: border-box;align-content: flex-start;">
    <block v-for="(product,index) in list" :key="index">
        <view v-if="index%6==0" @tap="onDetailTap(product)" class="list-item"
              :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0 0 20rpx 0","borderColor":"red","backgroundColor":"rgba(0, 206, 209, 0)","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"auto"}'>
            <image
                    :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0 0 8rpx 0","borderColor":"darkcyan","backgroundColor":"rgba(255,0,0,0)","borderRadius":"200rpx 200rpx 20rpx 200rpx","borderWidth":"6rpx","width":"100%","borderStyle":"solid","height":"200rpx"}'
                    mode="aspectFill" :src="product.yimiaoPhoto?baseUrl+product.yimiaoPhoto.split(',')[0]:''">
            </image>
            <view class="list-item-body" style="display: flex;flex-wrap: wrap;">
                <view
                        :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                        class="title hide1">{{product.yimiaoName}}
                </view>
                <view v-if="product.yimiaoNewMoney"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"red","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"34rpx","borderStyle":"solid"}'
                      class="title hide1">￥{{product.yimiaoNewMoney}}
                </view>
                <view v-else-if="product.yimiaoValue"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                      class="title hide1">{{product.yimiaoValue}}
                </view>
            </view>
            <view style="display: flex;justify-content: space-between;">
                <text v-if="isAuth('yimiao','修改')" class="cuIcon-edit"
                      @click.stop="onUpdateTap(product.id)">修改
                </text>
                <text v-if="isAuth('yimiao','删除')" class="cuIcon-delete"
                      @click.stop="onDeleteTap(product.id)">删除
                </text>
            </view>
        </view>
        <view v-if="index%6==1" @tap="onDetailTap(product)"
              :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0 2% 20rpx 0","borderColor":"red","backgroundColor":"rgba(255,0,0,0)","borderRadius":"0","borderWidth":"0","width":"48%","borderStyle":"solid","height":"auto"}'>
            <image
                    :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"darkcyan","backgroundColor":"rgba(255,0,0,0)","borderRadius":"20rpx 100rpx 100rpx 100rpx","borderWidth":"10rpx","width":"100%","borderStyle":"solid","height":"200rpx"}'
                    mode="aspectFill" :src="product.yimiaoPhoto?baseUrl+product.yimiaoPhoto.split(',')[0]:''">
            </image>
            <view class="list-item-body" style="display: flex;flex-wrap: wrap;">
                <view
                        :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                        class="title hide1">{{product.yimiaoName}}
                </view>
                <view v-if="product.yimiaoNewMoney"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"red","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"34rpx","borderStyle":"solid"}'
                      class="title hide1">￥{{product.yimiaoNewMoney}}
                </view>
                <view v-else-if="product.yimiaoValue"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                      class="title hide1">{{product.yimiaoValue}}
                </view>
            </view>
            <view style="display: flex;justify-content: space-between;">
                <text v-if="isAuth('yimiao','修改')" class="cuIcon-edit"
                      @click.stop="onUpdateTap(product.id)">修改
                </text>
                <text v-if="isAuth('yimiao','删除')" class="cuIcon-delete"
                      @click.stop="onDeleteTap(product.id)">删除
                </text>
            </view>
        </view>
        <view v-if="index%6==2" @tap="onDetailTap(product)"
              :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0 0 20rpx 2%","borderColor":"red","backgroundColor":"rgba(255,0,0,0)","borderRadius":"0","borderWidth":"0","width":"48%","borderStyle":"solid","height":"auto"}'>
            <image
                    :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"darkcyan","backgroundColor":"rgba(255,0,0,0)","borderRadius":"100rpx 20rpx 100rpx 100rpx","borderWidth":"10rpx","width":"100%","borderStyle":"solid","height":"200rpx"}'
                    mode="aspectFill" :src="product.yimiaoPhoto?baseUrl+product.yimiaoPhoto.split(',')[0]:''">
            </image>
            <view class="list-item-body" style="display: flex;flex-wrap: wrap;">
                <view
                        :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                        class="title hide1">{{product.yimiaoName}}
                </view>
                <view v-if="product.yimiaoNewMoney"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"red","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"34rpx","borderStyle":"solid"}'
                      class="title hide1">￥{{product.yimiaoNewMoney}}
                </view>
                <view v-else-if="product.yimiaoValue"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                      class="title hide1">{{product.yimiaoValue}}
                </view>
            </view>
            <view style="display: flex;justify-content: space-between;">
                <text v-if="isAuth('yimiao','修改')" class="cuIcon-edit"
                      @click.stop="onUpdateTap(product.id)">修改
                </text>
                <text v-if="isAuth('yimiao','删除')" class="cuIcon-delete"
                      @click.stop="onDeleteTap(product.id)">删除
                </text>
            </view>
        </view>
        <view v-if="index%6==3" @tap="onDetailTap(product)" class="list-item"
              :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0 0 20rpx 0","borderColor":"darkcyan","backgroundColor":"darkcyan","borderRadius":"0 0 30rpx 30rpx","borderWidth":"6rpx","width":"100%","borderStyle":"solid","height":"auto"}'>
            <image
                    :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(255,0,0,0)","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"200rpx"}'
                    mode="aspectFill" :src="product.yimiaoPhoto?baseUrl+product.yimiaoPhoto.split(',')[0]:''">
            </image>
            <view class="list-item-body" style="display: flex;flex-wrap: wrap;">
                <view
                        :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                        class="title hide1">{{product.yimiaoName}}
                </view>
                <view v-if="product.yimiaoNewMoney"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"red","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"34rpx","borderStyle":"solid"}'
                      class="title hide1">￥{{product.yimiaoNewMoney}}
                </view>
                <view v-else-if="product.yimiaoValue"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                      class="title hide1">{{product.yimiaoValue}}
                </view>
            </view>
            <view style="display: flex;justify-content: space-between;">
                <text v-if="isAuth('yimiao','修改')" class="cuIcon-edit"
                      @click.stop="onUpdateTap(product.id)">修改
                </text>
                <text v-if="isAuth('yimiao','删除')" class="cuIcon-delete"
                      @click.stop="onDeleteTap(product.id)">删除
                </text>
            </view>
        </view>
        <view v-if="index%6==4" @tap="onDetailTap(product)"
              :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0 2% 0 0","borderColor":"red","backgroundColor":"rgba(255,0,0,0)","borderRadius":"0","borderWidth":"0","width":"48%","borderStyle":"solid","height":"auto"}'>
            <image
                    :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"darkcyan","backgroundColor":"rgba(255,0,0,0)","borderRadius":"100rpx 100rpx 100rpx 20rpx","borderWidth":"10rpx","width":"100%","borderStyle":"solid","height":"200rpx"}'
                    mode="aspectFill" :src="product.yimiaoPhoto?baseUrl+product.yimiaoPhoto.split(',')[0]:''">
            </image>
            <view class="list-item-body" style="display: flex;flex-wrap: wrap;">
                <view
                        :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                        class="title hide1">{{product.yimiaoName}}
                </view>
                <view v-if="product.yimiaoNewMoney"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"red","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"34rpx","borderStyle":"solid"}'
                      class="title hide1">￥{{product.yimiaoNewMoney}}
                </view>
                <view v-else-if="product.yimiaoValue"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                      class="title hide1">{{product.yimiaoValue}}
                </view>
            </view>
            <view style="display: flex;justify-content: space-between;">
                <text v-if="isAuth('yimiao','修改')" class="cuIcon-edit"
                      @click.stop="onUpdateTap(product.id)">修改
                </text>
                <text v-if="isAuth('yimiao','删除')" class="cuIcon-delete"
                      @click.stop="onDeleteTap(product.id)">删除
                </text>
            </view>
        </view>
        <view v-if="index%6==5" @tap="onDetailTap(product)"
              :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0 0 0 2%","borderColor":"red","backgroundColor":"rgba(255,0,0,0)","borderRadius":"0","borderWidth":"0","width":"48%","borderStyle":"solid","height":"auto"}'>
            <image
                    :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"darkcyan","backgroundColor":"rgba(255,0,0,0)","borderRadius":"100rpx 100rpx 20rpx 100rpx","borderWidth":"10rpx","width":"100%","borderStyle":"solid","height":"200rpx"}'
                    mode="aspectFill" :src="product.yimiaoPhoto?baseUrl+product.yimiaoPhoto.split(',')[0]:''">
            </image>
            <view class="list-item-body" style="display: flex;flex-wrap: wrap;">
                <view
                        :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                        class="title hide1">{{product.yimiaoName}}
                </view>
                <view v-if="product.yimiaoNewMoney"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"red","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"34rpx","borderStyle":"solid"}'
                      class="title hide1">￥{{product.yimiaoNewMoney}}
                </view>
                <view v-else-if="product.yimiaoValue"
                      :style='{"padding":"0 24rpx","boxShadow":"0 0 12rpx rgba(255,0,0,0)","margin":"0","borderColor":"red","backgroundColor":"rgba(163, 169, 228, 0)","color":"#333","textAlign":"center","borderRadius":"0","borderWidth":"0","width":"100%","lineHeight":"36rpx","fontSize":"28rpx","borderStyle":"solid"}'
                      class="title hide1">{{product.yimiaoValue}}
                </view>
            </view>
            <view style="display: flex;justify-content: space-between;">
                <text v-if="isAuth('yimiao','修改')" class="cuIcon-edit"
                      @click.stop="onUpdateTap(product.id)">修改
                </text>
                <text v-if="isAuth('yimiao','删除')" class="cuIcon-delete"
                      @click.stop="onDeleteTap(product.id)">删除
                </text>
            </view>
        </view>
    </block>
</view>
	</view>

	</mescroll-uni>
</template>

<script>
	export default {
		data() {
			return {
                user:{},//当前登录表的所有参数
                sessionTable : uni.getStorageSync("nowTable"),//登录账户所在表名
                role : uni.getStorageSync("role"),//权限
                userId:uni.getStorageSync("userid"),//当前登录人的id
                btnColor: ['#67c23a', '#909399', '#e6a23c', '#f56c6c', '#356c6c', '#3498db', '#f093a9',
                    '#a7c23a', '#104eff', '#90f794', '#a21233', '#8e44ad'
                ],
				queryList: [{
						queryName: "疫苗名称",
					}
				],
                sactiveItem: {
                    "padding": "0",
                    "boxShadow": "0 0 0px rgba(0,0,0,.3)",
                    "margin": "0 10rpx",
                    "borderColor": "rgba(0,0,0,1)",
                    "backgroundColor": "#f5f5f5",
                    "color": "#fff",
                    "borderRadius": "0 40rpx 40rpx 0",
                    "borderWidth": "0",
                    "width": "auto",
                    "lineHeight": "160rpx",
                    "fontSize": "28rpx",
                    "borderStyle": "solid"
                },
                sitem: {
                    "padding": "0",
                    "boxShadow": "0 0 0px rgba(0,0,0,.3)",
                    "margin": "0 10rpx",
                    "borderColor": "rgba(0,0,0,1)",
                    "backgroundColor": "darkcyan",
                    "color": "#fff",
                    "borderRadius": "0 40rpx 40rpx 0",
                    "borderWidth": "0",
                    "width": "auto",
                    "lineHeight": "160rpx",
                    "fontSize": "28rpx",
                    "borderStyle": "solid"
                },

				list: [],
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				searchForm: {},
				/*1111*/
				yimiaoTypesList: [],
                yimiaoTypes: 0,

				CustomBar: '0'
			};
		},
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
		async onShow() {
			this.btnColor = this.btnColor.sort(() => {
				return (0.5 - Math.random());
			});

            let _this = this
            let table = uni.getStorageSync("nowTable");
            // 获取用户信息
            let resA = await _this.$api.session(table);
            _this.user = resA.data;
            _this.btnColor = _this.btnColor.sort(() => {
                return (0.5 - Math.random());
            });

                //当前表的 疫苗类型 字段 字典表查询方法
                this.yimiaoTypesSelect()



			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		onLoad() {
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		methods: {
		    //类型条件查询
            async showDrawer() {
                this.$refs.showRight.open();
            },
            async closeDrawer() {
                this.$refs.showRight.close();
            },
            //下载
            download(url) {
                let _this = this;
                uni.downloadFile({
                    url: url,
                    success: (res) => {
                        if (res.statusCode === 200) {
                            _this.$utils.msg('下载成功');
                            window.open(url);
                        }
                    }
                });
            },
            //当前表的 疫苗类型 字段 字典表查询方法
            async yimiaoTypesSelect(){
                let yimiaoTypes = await this.$api.page('dictionary', {
                    page: 1,
                    limit: 100,
                    dicCode:'yimiao_types',
                });
                yimiaoTypes.data.list.splice(0, 0, {codeIndex: 0, indexName: '全部'})
                this.yimiaoTypesList = yimiaoTypes.data.list
            },
            //当前表的 疫苗类型字段类别搜索
			yimiaoTypesCategoryClick(categoryName) {
                this.yimiaoTypes = categoryName;
                this.mescroll.resetUpScroll();
            },
            //查询条件切换
            queryChange(e) {
                this.searchForm.yimiaoName = "";
            },
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let params = {
					page: mescroll.num,
					limit: mescroll.size,
                    yimiaoDelete: 1,
				}

                if (this.yimiaoTypes != 0) {
                    params.yimiaoTypes = this.yimiaoTypes
                }
                let _this = this
                let res = await this.$api.list(`yimiao`, params);
                res.data.list.forEach(function(item,index){
                    if(item.yimiaoContent!=null){
                        item.yimiaoContent = item.yimiaoContent.replace("<img","<img  class=\"daxiao\"").replace("src=\"upload/","src=\""+_this.$base.url+"upload/");
                    }
                })

                if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			//添加地址
            onSelectTap(item) {
                uni.setStorageSync('address', item);
                uni.navigateBack({
                    delta: 1
                })
            },
			// 详情

			onDetailTap(item) {
				this.$utils.jump(`./detail?id=${item.id}`)
			},
			// 修改
			onUpdateTap(id) {
				this.$utils.jump(`./add-or-update?id=${id}`)
			},
			// 添加
			onAddTap() {
				this.$utils.jump(`./add-or-update`)
			},
			onDeleteTap(id) {
				var _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await _this.$api.del('yimiao', JSON.stringify([id]));
							_this.hasNext = true
							// 重置分页参数页数为1
							_this.mescroll.resetUpScroll()
						}
					}
				});
			},
            onShenhe(data,shenheTypes) {
                var _this = this;
                uni.showModal({
                    title: '提示',
                    content: '确定要这个审核状态么?',
                    success: async function(res) {
                        if (res.confirm) {
                            var currentTime = new Date();
                            var year = currentTime.getFullYear();
                            var month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1;
                            var day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate();
                            var hour = currentTime.getHours();
                            var minute = currentTime.getMinutes();
                            var second = currentTime.getSeconds();
                            var shijian=year + "-" + month + "-" + day + " " +hour +":" +minute+":"+second;

                            data.yimiaoYesnoTypes=shenheTypes;
                            data.yimiaoShenheTime=shijian;
                            await _this.$api.update(`yimiao`, data);
                            _this.hasNext = true
                            // 重置分页参数页数为1
                            _this.mescroll.resetUpScroll()
                        }
                    }
                });
            },
			// 搜索
			async search() {
				this.mescroll.num = 1
				let searchForm = {
					page: this.mescroll.num,
					limit: this.mescroll.size
                    ,yimiaoDelete: 1
				}
				if (this.searchForm.yimiaoName) {
                    searchForm['yimiaoName'] = this.searchForm.yimiaoName
                }

                let res = await this.$api.list(`yimiao`, searchForm);
                let _this = this
                res.data.list.forEach(function(item,index){
                    if(item.yimiaoContent!=null){
                        item.yimiaoContent = item.yimiaoContent.replace("<img","<img  class=\"daxiao\"").replace("src=\"upload/","src=\""+_this.$base.url+"upload/");
                    }
                })
				// 如果是第一页数据置空
				if (this.mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				if (res.data.list.length == 0) this.hasNext = false;
				this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
			}
		}
	};
</script>

<style>
    /* product */
    page {
        background: #EEEEEE;
    }

    view {
        font-size: 28upx;
    }

    .daxiao{
        width: 100%;
        padding: 0 10rpx;
    }

    /* 按钮条件查询样式------------------------开始 */
    .tabView {
        display: flex;
        align-items: center;
        justify-content: flex-start;
        height: 60upx;
        margin-bottom: 20upx;
        white-space: nowrap;
        box-shadow: 0px 1px 14px 0px rgba(38, 38, 35, 0.07);
        margin-top: 6upx;
    }
    .tab {
        text-align: center;
        display: inline-block;
        margin: 0 12rpx 8rpx 0;
        padding: 0 10rpx;
        width: auto;
        line-height: 160rpx;
        color: #fff;
        font-size: 28rpx;
        border-radius: 0 40rpx 40rpx 0;
        border-width: 0;
        border-style: solid;
        border-color: rgba(0,0,0,1);
        background-color: darkcyan;
        box-shadow: 0 0 0px rgba(0,0,0,.3);
    }
    .tabActive{
        margin: 0 12rpx 8rpx 0;
        padding: 0 10rpx;
        width: auto;
        line-height: 160rpx;
        color: #fff;
        font-size: 28rpx;
        border-radius: 0 40rpx 40rpx 0;
        border-width: 0;
        border-style: solid;
        border-color: rgba(0,0,0,1);
        background-color: #f5f5f5;
        box-shadow: 0 0 0px rgba(0,0,0,.3);
    }
    /* 按钮条件查询样式------------------------结束 */

    .tab:last-of-type {
        border: none;
    }

    .uni-product-list {
        display: flex;
        width: 100%;
        flex-wrap: wrap;
        flex-direction: row;
        margin-top: 0upx;
    // justify-content: space-around;
    }

    .uni-product {
        padding: 10upx;
        margin: 10upx;
        display: flex;
        flex-direction: column;
        background: #FFFFFF;
    }

    .image-view {
        height: 330upx;
        width: 330upx;
        margin: 12upx 0;
    }

    .uni-product-image {
        height: 330upx;
        width: 330upx;
    }

    .uni-product-title {
        width: 300upx;
        word-break: break-all;
        display: -webkit-box;
        overflow: hidden;
        line-height: 1.5;
        text-overflow: ellipsis;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
    }

    .uni-product-price {
        margin-top: 10upx;
        font-size: 28upx;
        line-height: 1.5;
        position: relative;
    }

    .uni-product-price-original {
        color: #e80080;
    }

    .uni-product-price-favour {
        color: #888888;
        text-decoration: line-through;
        margin-left: 10upx;
    }

    .uni-product-tip {
        position: absolute;
        right: 10upx;
        background-color: #ff3333;
        color: #ffffff;
        padding: 0 10upx;
        border-radius: 5upx;
    }

    uni-image > div, uni-image > img {
        width: 100%;
        height: 140px;
        object-fit: cover;
    }

    .add-btn {
        position: fixed;
        left: 30upx;
        right: 30upx;
    // #ifndef MP
    bottom: 106upx;
    // #endif
    // #ifdef MP-WEIXIN
    bottom: 16upx;
    // #endif
    z-index: 95;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 690upx;
        height: 80upx;
        font-size: 32upx;
        color: #fff;
        background-color: red;
        border-radius: 10upx;
        box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
    }

    .list {
        padding: 20upx 20upx 20upx;
    }

    .listm {
        background: #fff;
        border-radius: 15upx;
        box-shadow: 0 0 2upx rgba(0, 0, 0, 0.1);
        margin-bottom: 20upx;
        padding: 30upx;
    }

    .listmpic {
        border-radius: 10upx;
        width: 166upx;
        margin-right: 20upx;
    }

    .listmr {
    // width: 460upx;
        display: inline-block;
        flex: 1;
        display: flex;
        justify-content: space-between;
        flex-direction: column;
    }

    .news-box6 .dian::before {
        content: "";
        display: block;
        width: 8px;
        height: 8px;
        background-color: red;
        position: absolute;
        top: -4px;
        left: 50%;
        transform: translateX(-50%);
        border-radius: 100%;
        z-index: 1;
    }


    .hide1 {
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        line-clamp: 1;
        -webkit-box-orient: vertical;
    }

    .hide2 {
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        line-clamp: 2;
        -webkit-box-orient: vertical;
    }

    .hide3 {
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 3;
        line-clamp: 3;
        -webkit-box-orient: vertical;
    }

    .hide4 {
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 4;
        line-clamp: 4;
        -webkit-box-orient: vertical;
    }
</style>
