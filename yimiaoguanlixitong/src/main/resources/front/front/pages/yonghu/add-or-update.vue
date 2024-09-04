<template>
	<view class="content">
		<form class="app-update-pv">
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					class="title">账户</view>
				<input disabled
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					:disabled="ro.username" type="text" v-model="ruleForm.username" placeholder="账户"></input>
			</view>
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					class="title">用户姓名</view>
				<input disabled
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					:disabled="ro.yonghuName" type="text" v-model="ruleForm.yonghuName" placeholder="用户姓名"></input>
			</view>
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					class="title">用户手机号</view>
				<input disabled
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					:disabled="ro.yonghuPhone" type="text" v-model="ruleForm.yonghuPhone" placeholder="用户手机号"></input>
			</view>
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					class="title">用户身份证号</view>
				<input disabled
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					:disabled="ro.yonghuIdNumber" type="text" v-model="ruleForm.yonghuIdNumber"
					placeholder="用户身份证号"></input>
			</view>
			<view
				:style='{"boxShadow":"0 0 0px rgba(0,0,0,.3)","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(218, 220, 219, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"108rpx"}'
				class="cu-form-group" @tap="yonghuPhotoTap">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					class="title">用户头像</view>
				<view class="right-input" style="padding:0;textAlign:left">
					<image
						:style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
						class="avator" v-if="ruleForm.yonghuPhoto" :src="baseUrl+ruleForm.yonghuPhoto"
						mode="aspectFill">
					</image>
					<image
						:style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
						class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
				</view>
			</view>
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					class="title">性别</view>
				<picker @change="sexTypesChange" :value="sexTypesIndex" :range="sexTypesOptions" range-key="indexName">
					<view
						:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
						class="uni-input">{{ruleForm.sexTypes?ruleForm.sexValue:"请选择性别"}}</view>
				</picker>
			</view>
			<view
				:style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
				class="cu-form-group">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					class="title">用户邮箱</view>
				<input disabled
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					:disabled="ro.yonghuEmail" type="text" v-model="ruleForm.yonghuEmail" placeholder="用户邮箱"></input>
			</view>

			<view class="btn">
				<button
					:style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"#f5f5f5","borderColor":"#409EFF","borderRadius":"8rpx","color":"rgba(255, 255, 255, 1)","borderWidth":"0","width":"70%","fontSize":"32rpx","borderStyle":"solid","height":"80rpx"}'
					@tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>

		<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="createTimeConfirm"
			ref="createTime" themeColor="#333333"></w-picker>


	</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";

	export default {
		data() {
			return {
				cross: '',
				ro: {
					username: false,
					password: false,
					yonghuName: false,
					yonghuPhone: false,
					yonghuIdNumber: false,
					yonghuPhoto: false,
					sexTypes: false,
					yonghuEmail: false,
					createTime: false,
				},
				ruleForm: {
					username: '',
					password: '',
					yonghuName: '',
					yonghuPhone: '',
					yonghuIdNumber: '',
					yonghuPhoto: '',
					sexTypes: '', //数字
					sexValue: '', //数字对应的值
					yonghuEmail: '',
					createTime: '',
				},
				// 登陆用户信息
				user: {},
				sexTypesOptions: [],
				sexTypesIndex: 0,

			}
		},
		components: {
			wPicker
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
				dicCode: 'sex_types',
			}
			let sexTypes = await this.$api.page(`dictionary`, sexTypesParams);
			this.sexTypesOptions = sexTypes.data.list


			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				let res = await this.$api.info(`yonghu`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			if (options.yonghuId) {
				this.ruleForm.yonghuId = options.yonghuId;
			}
			// 跨表
			// this.styleChange()
		},
		methods: {
			// 下拉变化
			sexTypesChange(e) {
				this.sexTypesIndex = e.target.value
				this.ruleForm.sexValue = this.sexTypesOptions[this.sexTypesIndex].indexName
				this.ruleForm.sexTypes = this.sexTypesOptions[this.sexTypesIndex].codeIndex
			},


			// styleChange() {
			// 	this.$nextTick(() => {
			// 		// document.querySelectorAll('.app-update-pv .cu-form-group .uni-yaoxianStyle-yaoxianStyle').forEach(el=>{
			// 		//   el.style.backgroundColor = this.addUpdateForm.yaoxianStyle.content.backgroundColor
			// 		// })
			// 	})
			// },


			yonghuPhotoTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.yonghuPhoto = 'upload/' + res.file;
					_this.$forceUpdate();
					_this.$nextTick(() => {
						// _this.styleChange()
					})
				});
			},
			// 日期控件
			createTimeConfirm(val) {
				this.ruleForm.createTime = val.result;
				this.$forceUpdate();
			},




			getUUID() {
				return new Date().getTime();
			},
			async onSubmitTap() {
				if ((!this.ruleForm.username)) {
					this.$utils.msg(`账户不能为空`);
					return
				}
				if ((!this.ruleForm.yonghuName)) {
					this.$utils.msg(`用户姓名不能为空`);
					return
				}
				if ((!this.ruleForm.yonghuPhone) && (!this.$validate.isMobile(this.ruleForm.yonghuPhone))) {
					this.$utils.msg(`用户手机号不能为空并且需要输入正确格式`);
					return
				}
				if ((!this.ruleForm.yonghuIdNumber) && (!this.$validate.checkIdCard(this.ruleForm.yonghuIdNumber))) {
					this.$utils.msg(`用户身份证号不能为空并且需要输入正确格式`);
					return
				}
				if ((!this.ruleForm.yonghuPhoto)) {
					this.$utils.msg(`用户头像不能为空`);
					return
				}
				if ((!this.ruleForm.sexTypes)) {
					this.$utils.msg(`性别不能为空`);
					return
				}
				if ((!this.ruleForm.yonghuEmail) && (!this.$validate.isEmail(this.ruleForm.yonghuEmail))) {
					this.$utils.msg(`用户邮箱不能为空并且需要输入正确格式`);
					return
				}
				if (this.ruleForm.id) {
					await this.$api.update(`yonghu`, this.ruleForm);
				} else {
					await this.$api.save(`yonghu`, this.ruleForm);
				}
				uni.setStorageSync('pingluenStateState', true);
				this.$utils.msgBack('提交成功');
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			toggleTab(str) {
				this.$refs[str].show();
			}
		}
	}
</script>
<style lang="scss" scoped>
	.container {
		padding: 20upx;
	}

	.content:after {
		position: fixed;
		top: 0;
		right: 0;
		left: 0;
		bottom: 0;
		content: '';
		background-attachment: fixed;
		background-size: cover;
		background-position: center;
	}

	textarea {
		border: 1upx solid #EEEEEE;
		border-radius: 20upx;
		padding: 20upx;
	}

	.title {
		width: 180upx;
	}

	.avator {
		width: 150upx;
		height: 60upx;
	}

	.right-input {
		flex: 1;
		text-align: left;
		padding: 0 24upx;
	}

	.cu-form-group.active {
		justify-content: space-between;
	}

	.cu-form-group .title {
		height: auto;
		line-height: 30rpx
	}

	.btn {
		display: flex;
		align-items: center;
		justify-content: center;
		flex-wrap: wrap;
		padding: 20upx 0;
	}

	.cu-form-group {
		padding: 0 24upx;
		background-color: transparent;
		min-height: inherit;
	}

	.cu-form-group+.cu-form-group {
		border: 0;
	}

	.cu-form-group uni-input {
		padding: 0 30upx;
	}

	.uni-input {
		padding: 0 30upx;
	}

	.cu-form-group uni-textarea {
		padding: 30upx;
		margin: 0;
	}

	.cu-form-group uni-picker::after {
		line-height: 80rpx;
	}

	.select .uni-input {
		line-height: 80rpx;
	}

	.input .right-input {
		line-height: 88rpx;
	}
</style>
