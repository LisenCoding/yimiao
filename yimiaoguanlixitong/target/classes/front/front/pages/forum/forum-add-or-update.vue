<template>
    <view class="container my-publish-pv">
        <form>
            <view class="cu-form-group margin-top">
                <input
                        :style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"#f5f5f5","borderRadius":"0px","color":"rgba(14, 183, 34, 1)","textAlign":"left","borderWidth":"0 0 6rpx","fontSize":"24rpx","borderStyle":"solid","height":"88rpx"}'
                        v-model="forum.forumName" placeholder="标题"></input>
            </view>

            <view class="cu-form-group margin-top">
				<textarea
                        :style='{"boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 0)","borderColor":"rgba(227, 227, 227, 1)","borderRadius":"8rpx","color":"#333","borderWidth":"6rpx","fontSize":"28rpx","borderStyle":"solid","height":"300rpx"}'
                        style="height: 500upx;" v-model="forum.forumContent" placeholder="内容"></textarea>
            </view>
            <view class="cu-form-group margin-top" style="text-align:center">
                <button
                        :style='{"boxShadow":"0 0 0px rgba(0,0,0,0) inset","backgroundColor":"#f5f5f5","borderColor":"rgba(255, 255, 255, 1)","borderRadius":"8rpx","color":"#fff","borderWidth":"1","width":"100%","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}'
                        @tap="onSubmitTap" class="bg-red margin-tb-sm">确认提交</button>
            </view>
        </form>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                forum: {
                    forumContent: '',
                    id: '',
                    forumName: '',
                    forumTypes: null,
                    forumStateTypes:1,
                },
                username: '',
                user: {}
            }
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
        async onLoad(options) {
            let _this = this
            let table = uni.getStorageSync("nowTable");
            // 获取用户信息
            let res = await _this.$api.session(table);
            _this.user = res.data;
            if (table == `yonghu`) {
                _this.yonghuId = _this.user.id
            }
            if (options.id) {
                _this.id = options.id;
                let res = await _this.$api.info('forum', _this.id);
                _this.forum = res.data
            }
            _this.styleChange()
        },
        methods: {
            styleChange() {
                this.$nextTick(() => {
                    // document.querySelectorAll('.my-publish-pv .uni-yaoxianStyle-yaoxianStyle').forEach(el=>{
                    //   el.style.backgroundColor = this.myPublishForm.content.yaoxianStyle.backgroundColor
                    // })
                })
            },
            async onSubmitTap() {
                this.forum.username = this.username;
                if (this.id) {
                    await this.$api.update('forum', this.forum);
                } else {
                    await this.$api.save('forum', this.forum);
                }
                this.$utils.msgBack('操作成功');
            },
        }
    }
</script>

<style>
    page {
        background-color: #fff;
    }

    .container {
        padding: 20upx 24upx;
    }

    .container:after {
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

    .cu-form-group {
        padding: 0;
        background-color: transparent;
        min-height: inherit;
    }

    .cu-form-group+.cu-form-group {
        border: 0;
    }

    .cu-form-group uni-input {
        padding: 0 30upx;
    }

    .cu-form-group uni-textarea {
        padding: 30upx;
        margin: 0;
    }

    .cu-form-group uni-picker::after {
        line-height: 80rpx;
    }

    .bg-red {
        line-height: 80rpx;
    }
</style>
