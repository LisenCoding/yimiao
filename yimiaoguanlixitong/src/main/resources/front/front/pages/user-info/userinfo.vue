<template>
	<view class="content">
        <view class="box"
              :style='{"padding":"0","boxShadow":"0 0 0px rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"auto"}'>
		 <view v-if="tableName=='yonghu'" class="cu-form-group">
			 <view class="title"
				   :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'>账户
			 </view>
			 <input
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					v-model="ruleForm.username" placeholder="账户"></input>
		 </view>
		 <view v-if="tableName=='yonghu'" class="cu-form-group">
			 <view class="title"
				   :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'>用户姓名
			 </view>
			 <input
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					v-model="ruleForm.yonghuName" placeholder="用户姓名"></input>
		 </view>
		 <view v-if="tableName=='yonghu'" class="cu-form-group">
			 <view class="title"
				   :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'>用户手机号
			 </view>
			 <input
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					v-model="ruleForm.yonghuPhone" placeholder="用户手机号"></input>
		 </view>
		 <view v-if="tableName=='yonghu'" class="cu-form-group">
			 <view class="title"
				   :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'>用户身份证号
			 </view>
			 <input
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					v-model="ruleForm.yonghuIdNumber" placeholder="用户身份证号"></input>
		 </view>
			<view v-if="tableName=='yonghu'" @tap="yonghuPhotoTap" class="cu-form-group">
				<view class="title"
					  :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'>用户头像
				</view>
				<image
						:style='{"width":"76rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"0","textAlign":"left","height":"76rpx"}'
						v-if="ruleForm.yonghuPhoto" class="avator" :src="baseUrl+ruleForm.yonghuPhoto" mode=""></image>
				<image
						:style='{"width":"76rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"0","textAlign":"left","height":"76rpx"}'
						v-else class="avator" src="../../static/center/face.jpeg" mode=""></image>
			</view>
			<view v-if="tableName=='yonghu'" class="cu-form-group select">
				<view :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'
					  class="title">性别</view>
				<picker @change="yonghusexTypesChange" :value="sexTypesIndex" :range="sexTypesOptions" range-key="indexName">
					<view :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
						  class="uni-input picker-select-input">{{ruleForm.sexValue?ruleForm.sexValue:"请选择性别"}}</view>
				</picker>
			</view>
		 <view v-if="tableName=='yonghu'" class="cu-form-group">
			 <view class="title"
				   :style='{"width":"160rpx","fontSize":"28rpx","color":"#f5f5f5","textAlign":"left"}'>用户邮箱
			 </view>
			 <input
					:style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","margin":"0","borderColor":"#f5f5f5","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"40rpx","borderWidth":"3rpx","fontSize":"28rpx","borderStyle":"dashed ","height":"88rpx","marginTop":"7rpx"}'
					v-model="ruleForm.yonghuEmail" placeholder="用户邮箱"></input>
		 </view>

		<view class="btn" style="margin-top:40rpx">
			<view class="box" :style="{width: 'auto'}">
				<button @tap="update()" class="cu-btn lg"
					:style='{"margin":"0 30rpx 0 0","boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"#f5f5f5","borderColor":"#f5f5f5","borderRadius":"8rpx","color":"#fff","borderWidth":"0","width":"auto","fontSize":"32rpx","borderStyle":"solid","height":"80rpx"}'>保存</button>
			</view>
		
			<view class="box" :style="{width: 'auto'}">
				<button @tap="logout()" class="cu-btn lg"
					:style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"#f5f5f5","borderColor":"#E6A23C","borderRadius":"8rpx","color":"#fff","borderWidth":"0px","width":"auto","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'>退出登录</button>
			</view>
		</view>
	</view>
			<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="createTimeConfirm"
					  ref="createTime" themeColor="#333333"></w-picker>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				ruleForm: {},
				tableName: "",
				sexTypesOptions: [],
				sexTypesIndex: 0,
			}
		},
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
		async onLoad() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			this.ruleForm = res.data;
			this.tableName = table;
			// 自定义下拉框值

			if (this.tableName == 'yonghu') {
            let sex_typesParams = {
                page: 1,
                limit: 100,
                dicCode: 'sex_types',
            }
				let sex_typesRes = await this.$api.page(`dictionary`, sex_typesParams);

				this.sexTypesOptions = sex_typesRes.data.list
			}
			// this.styleChange()
		},
		methods: {
			// 下拉变化
			yonghusexTypesChange(e) {
				this.sexTypesIndex = e.target.value
				this.ruleForm.sexValue = this.sexTypesOptions[this.sexTypesIndex].indexName
				this.ruleForm.sexTypes = this.sexTypesOptions[this.sexTypesIndex].codeIndex
			},
			// 日期控件
			createTimeConfirm(val) {
				this.ruleForm.createTime = val.result;
				this.$forceUpdate();
			},
			// 获取uuid
			getUUID() {
				return new Date().getTime();
			},
			logout() {
				uni.setStorageSync('token', '');
				this.$utils.jump('../login/login');
			},
			// 注册
			async update() {
					if ((!this.ruleForm.username) && `yonghu` == this.tableName) {
						this.$utils.msg(`账户不能为空`);
						return
					}
					if ((!this.ruleForm.yonghuName) && `yonghu` == this.tableName) {
						this.$utils.msg(`用户姓名不能为空`);
						return
					}
					if (`yonghu` == this.tableName && this.ruleForm.yonghu && (!this.$validate.isMobile(this
									.ruleForm.yonghuPhone))) {
						this.$utils.msg(`用户手机号应输入正确格式`);
						return
					}
					if (`yonghu` == this.tableName && this.ruleForm.yonghu && (!this.$validate.checkIdCard(this
									.ruleForm.yonghuIdNumber))) {
						this.$utils.msg(`用户身份证号应输入正确格式`);
						return
					}
					if ((!this.ruleForm.yonghuPhoto) && `yonghu` == this.tableName) {
						this.$utils.msg(`用户头像不能为空`);
						return
					}
					if (`yonghu` == this.tableName && this.ruleForm.yonghu && (!this.$validate.isEmail(this
                                    .ruleForm.yonghuEmail))) {
                        this.$utils.msg(`用户邮箱应输入正确格式`);
                        return
                    }

					let table = uni.getStorageSync("nowTable");
					await this.$api.update(table, this.ruleForm);
					this.$utils.msgBack('修改成功');;
			},
			yonghuPhotoTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.yonghuPhoto = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},
            toggleTab(str) {
                this.$refs[str].show();
            }

		}
	}
</script>
<style lang="scss" scoped>
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

    .avator {
        width: 110 upx;
        height: 110 upx;
        border-radius: 50%;
        margin-left: 30 upx;
    }

    .cu-form-group.active {
        justify-content: space-between;
    }

    .cu-btn {
        width: 100%;
    }

    .cu-form-group .title {
        height: auto;
        line-height:30rpx
    }

    .right-input {
        flex: 1;
        text-align: left;
        line-height: 52 rpx;
    }

    .btn {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-wrap: wrap;
    }

    .box {
        width: auto;
        padding: 0 10 upx;
        box-sizing: border-box;
        margin-bottom: 20 upx;
    }

    .picker-select-input {
        line-height: 52 rpx;
    }
</style>
