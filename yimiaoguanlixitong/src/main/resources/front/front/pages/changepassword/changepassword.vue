<template>
	<view class="content">
		<view class="uni-form-item uni-column">
			旧密码<input v-model="ruleForm.jiumima" class="uni-input" name="" placeholder="请输入旧密码" />
		</view>
		<view class="uni-form-item uni-column">
			新密码<input v-model="ruleForm.xinmima" class="uni-input" name="" placeholder="请输入新密码" />
		</view>
		<view class="uni-form-item uni-column">
			再次输入密码<input v-model="ruleForm.querenmima" class="uni-input" name="" placeholder="请再次输入密码" />
		</view>
		<button type="primary" @tap="tijiaoxuigai">提交</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				ruleForm:{
					jiumima:'',
					xinmima:'',
					querenmima:'',
				}
			}
		},
		async onShow() {
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			 this.user = res.data
		},
		methods: {
			async tijiaoxuigai() {
				if(this.user.password!=this.ruleForm.jiumima){
					this.$utils.msg(`旧密码不能和原始密码一样`);
					return 
				}
				if(this.user.password==this.ruleForm.xinmima){
					this.$utils.msg(`新密码不能和旧密码一样`);
					return 
				}
				if(this.ruleForm.xinmima!=this.ruleForm.querenmima){
					this.$utils.msg(`新密码和确认密码不一致`);
					return 
				}
				this.user.password=this.ruleForm.querenmima
			        await this.$api.update(`yonghu`, this.user);
			   
			    uni.setStorageSync('pingluenStateState', true);
			    this.$utils.msgBack('密码修改成功,下次登录时生效');
			},
		}
	}
</script>

<style lang="scss" scoped>
	$color-primary: #b49950;

	.content {
		padding: 100upx;
	}

	.uni-form-item {
		margin-bottom: 40upx;
		padding: 0;
		border-bottom: 1px solid #e3e3e3;

		.uni-input {
			font-size: 30upx;
			padding: 2px 0;
		}
	}

	.column-with-btn {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;

		button {
			font-size: 24upx;
			margin: 0;
			width: 180upx;
			text-align: center;

			&:after {
				border: none
			}

			&.active {
				background-color: $color-primary;
				color: $uni-text-color-inverse;
			}
		}
	}

	.img-captcha {
		width: 150upx;
		height: 60upx;
	}

	button[type="primary"] {
		background-color: $color-primary;
		border-radius: 0;
		font-size: 34upx;
		margin-top: 60upx;
	}

	.text-reset {
		text-align: center;
		margin-bottom: 100upx;
		font-size: 36upx;
	}
</style>
