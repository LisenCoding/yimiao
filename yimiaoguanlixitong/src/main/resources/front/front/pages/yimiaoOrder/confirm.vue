<template>
	<view class="content">
		<form>

			<view class="cu-form-group">
				<view class="title">预约清单</view>
			</view>
			<view class="" style="position: relative;z-index: 10000;">
				<uni-datetime-picker type="date" :clear-icon="false" v-model="single" @maskClick="maskClick" />
			</view>
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					class="title">接种地点</view>
				<picker @change="sexTypesChange" :value="sexTypesIndex" :range="sexTypesOptions" range-key="indexName">
					<view style="width: 100%;height: 60rpx;border-radius: 10rpx;border: 1rpx solid #0f0f0f;" class="uni-input">{{yimiaoDidianTypes?yimiaoDidianValue:"请选择接种地点"}}</view>
				</picker>
			</view>
			<view v-for="(item,index) in orderGoods " v-bind:key="index" class="cu-form-group">
				<image class="avator" :src="baseUrl+item.yimiaoPhoto" mode=""></image>
				<view class="title" style="width: 75%;">
					<view style="margin-top: -50rpx;">{{item.yimiaoName}}</view>
				</view>
			</view>

		</form>
		<view class="padding" style="text-align: center;">
			<button @tap="onSubmitTap()" class="bg-red lg">确认预约</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				yimiaoDidianTypes:"",
				yimiaoDidianValue:'',
				sexTypesOptions: [],
				sexTypesIndex: 0,
				single: '',
				user: {}, //登录用户
				orderGoods: [], //展示数据
				maxNewMouey: 0, //总价格
				yimiaoOrderPaymentTypes: 1, //是什么支付类型
				zhi: [{
						id: 1,
						val: "余额"
					},
					{
						id: 2,
						val: "积分"
					},
				],
				zhekou: 1, //折扣
			}
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad(options) {
			/*下拉框*/
			let sexTypesParams = {
				page: 1,
				limit: 100,
				dicCode: 'yimiao_didian_types',
			}
			let sexTypes = await this.$api.page(`dictionary`, sexTypesParams);
			this.sexTypesOptions = sexTypes.data.list
			// 获取订单信息，座位信息
			this.orderGoods = uni.getStorageSync('orderGoods');
			if (this.orderGoods.length > 0) {
				for (let i = 0; i < this.orderGoods.length; i++) {
					this.maxNewMouey = this.maxNewMouey + parseFloat(this.orderGoods[i].yimiaoNewMoney) * this
						.orderGoods[i].buyNumber
				}
			}
			uni.removeStorageSync("orderGoods")
		},
		async onShow() {
			let _this = this
			let table = uni.getStorageSync("nowTable");
			let userRes = await _this.$api.session(table)
			_this.user = userRes.data

			let huiyuandengjiTypesRes = await _this.$api.page("dictionary", {
				dicCode: "huiyuandengji_types",
				dicName: "会员等级类型",
				codeIndexStart: _this.user.huiyuandengjiTypes,
				codeIndexEnd: _this.user.huiyuandengjiTypes,
			})
			if (huiyuandengjiTypesRes.data.list.length > 0) {
				_this.zhekou = Number(huiyuandengjiTypesRes.data.list[0].beizhu);
			}


		},
		methods: {
			
			sexTypesChange(e) {
				this.sexTypesIndex = e.target.value
				this.yimiaoDidianValue = this.sexTypesOptions[this.sexTypesIndex].indexName
				this.yimiaoDidianTypes = this.sexTypesOptions[this.sexTypesIndex].codeIndex
			},
			maskClick(e) {
				console.log('maskClick事件:', e);
			},
			async onSubmitTap() {
				let _this = this;
				let table = uni.getStorageSync("nowTable");
				uni.showModal({
					title: '提示',
					content: '是否确认预约',
					success: async function(res) {
						if (res.confirm) {
							let data = {
								yimiaos: JSON.stringify(_this.orderGoods),
								yonghuId: _this.user.id,
								yimiaoOrderTime: _this.single,
								yimiaoDidianTypes:_this.yimiaoDidianTypes
							}
							await _this.$api.requestConditionDataGet('yimiaoOrder', 'order', null, data);
							_this.$utils.jump('/pages/yimiaoOrder/list');
						}
					}
				});
			},
		}
	}
</script>

<style lang="scss">
	.avator {
		width: 150upx;
		height: 150upx;
		margin: 20upx 0;
	}
</style>
