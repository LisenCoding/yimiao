<template>
    <view class="content">
        <view @tap="onPageTap('../user-info/userinfo')" class="header" v-bind:class="{'status':isH5Plus}"
              :style='{"backgroundColor":"darkcyan","height":"200rpx"}'>

            <view v-if="tableName=='yonghu'" class="userinfo">
                <view class="face"
                      :style='{"padding":"0","margin":"20rpx 20rpx 20rpx 0rpx","width":"150rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","height":"150rpx"}'>
                    <image :src="user.yonghuPhoto?baseUrl+user.yonghuPhoto:'/static/center/face.jpeg'"></image>
                </view>
                <view class="info">
                    <view class="username" style="background-color: #f5f5f5;width:100%;margin-bottom: 10px;padding:10rpx"
                          :style='{"boxShadow":"0 0 16rpx rgba(255,255,255,0)","borderColor":"rgba(255,255,255,.3)","borderRadius":"90rpx","color":"rgba(255, 255, 255, 1.0)","textAlign":"left","borderWidth":"0","lineHeight":"36rpx","fontSize":"35rpx","borderStyle":"solid"}'>
                        {{user.yonghuName}}
                    </view>
                </view>
            </view>

            <view class="setting" :style='{"color":"#fff","fontSize":"50rpx","fontWeight":"10rpx"}'>
                <view class="cuIcon-settings"></view>
            </view>
        </view>
        <view class="list">

            <view @tap="onPageTap('../changepassword/changepassword')" :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","backgroundColor":"#fff","borderColor":"rgba(216, 216, 216, 1)","borderRadius":"0","borderWidth":"0 0 2rpx","width":"100%","borderStyle":"solid","height":"80rpx"}' class="li" hover-class="hover">
                <view v-if="true" :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"#fff","color":"#f5f5f5","isShow":true,"borderRadius":"0","borderWidth":"0","width":"30rpx","lineHeight":"30rpx","fontSize":"40rpx","borderStyle":"solid"}' class="cuIcon-moneybag"></view>
                <view class="text" :style='{"padding":"0 20rpx","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"rgba(0,0,0,0)","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"0","borderWidth":"0","width":"calc(100% - 92rpx)","lineHeight":"80rpx","fontSize":"28rpx","borderStyle":"solid"}'>修改密码</view>
                <view v-if="true" class="cuIcon-right" :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","isShow":true,"borderRadius":"0","borderWidth":"0","width":"28rpx","lineHeight":"28rpx","fontSize":"28rpx","borderStyle":"solid"}'></view>
            </view>

            <block v-for="item in menuList" v-bind:key="item.roleName">
                <block v-if="role==item.roleName" v-bind:key="index" v-for=" (menu,index) in item.backMenu">
                    <block v-for=" (child,sort) in menu.child">
                        <view :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","backgroundColor":"#fff","borderColor":"rgba(216, 216, 216, 1)","borderRadius":"0","borderWidth":"0 0 2rpx","width":"100%","borderStyle":"solid","height":"80rpx"}'
                              @tap="onPageTap('../'+child.tableName+'/list?userId='+user.id)" class="li" hover-class="hover">
                            <view :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"#fff","color":"#f5f5f5","isShow":true,"borderRadius":"0","borderWidth":"0","width":"30rpx","lineHeight":"30rpx","fontSize":"40rpx","borderStyle":"solid"}' :class="child.appFrontIcon"></view>
                            <view :style='{"padding":"0 20rpx","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"rgba(0,0,0,0)","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"0","borderWidth":"0","width":"calc(100% - 92rpx)","lineHeight":"80rpx","fontSize":"28rpx","borderStyle":"solid"}' class="text">{{child.menu}}</view>
                            <view v-if="true" class="cuIcon-right" :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","isShow":true,"borderRadius":"0","borderWidth":"0","width":"28rpx","lineHeight":"28rpx","fontSize":"28rpx","borderStyle":"solid"}'></view>
                        </view>
                    </block>
                </block>
            </block>

            <view @tap="onPageTap('../forum/forum-my')" :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","backgroundColor":"#fff","borderColor":"rgba(216, 216, 216, 1)","borderRadius":"0","borderWidth":"0 0 2rpx","width":"100%","borderStyle":"solid","height":"80rpx"}' class="li" hover-class="hover">
                <view v-if="true" :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"#fff","color":"#f5f5f5","isShow":true,"borderRadius":"0","borderWidth":"0","width":"30rpx","lineHeight":"30rpx","fontSize":"40rpx","borderStyle":"solid"}' class="cuIcon-moneybag"></view>
                <view class="text" :style='{"padding":"0 20rpx","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"rgba(0,0,0,0)","color":"rgba(161, 161, 161, 1)","textAlign":"left","borderRadius":"0","borderWidth":"0","width":"calc(100% - 92rpx)","lineHeight":"80rpx","fontSize":"28rpx","borderStyle":"solid"}'>论坛信息</view>
                <view v-if="true" class="cuIcon-right" :style='{"padding":"0","boxShadow":"0 0 12rpx rgba(0,0,0,0)","margin":"0","borderColor":"rgba(0,0,0,0)","backgroundColor":"#fff","color":"rgba(161, 161, 161, 1)","isShow":true,"borderRadius":"0","borderWidth":"0","width":"28rpx","lineHeight":"28rpx","fontSize":"28rpx","borderStyle":"solid"}'></view>
            </view>


        </view>
    </view>
</template>


<script>
    import menu from '@/utils/menu'
    export default {
        data() {
            return {
                isH5Plus: true,
                user: {},
                tableName: '',
                role: '',
                menuList: [],
            };
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            }
        },
        async onLoad() {
            this.role = uni.getStorageSync("role");
            let table = uni.getStorageSync("nowTable");
            let res = await this.$api.session(table);
            this.user = res.data;
            this.tableName = table;
            let menus = menu.list();
            this.menuList = menus;
        },
        async onShow() {
            this.role = uni.getStorageSync("role");
            let table = uni.getStorageSync("nowTable");
            let res = await this.$api.session(table);
            this.user = res.data;
            this.tableName = table;
            let menus = menu.list();
            this.menuList = menus;
        },
        methods: {
            chooseLocation() {
                uni.chooseLocation({
                    success: function (data) {
                    }
                })
            },
            onPageTap(url) {
                uni.navigateTo({
                    url: url,
                    fail: function() {
                        uni.switchTab({
                            url: url
                        });
                    }
                });
            }
        }
    }
</script>

<style lang="scss">
        page {
            background-color: #fff;
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

        .header {
        &.status {
             padding-top: var(--status-bar-height);
         }

        background-color: #ff6364;
        width: 100%;
        height: 30vw;
        padding: 0 4%;
        display: flex;
        align-items: center;

        .userinfo {
            width: 100%;
            display: flex;
            align-items: center;

        .face {
            flex-shrink: 0;
            width: 15vw;
            height: 15vw;
            overflow: hidden;

        image {
            width: 100%;
            height: 100%;
        // border-radius: 100%
        }
        }

        .info {
        // display: flex;
            flex-flow: wrap;
        // padding-left: 30upx;

        .username {
            width: 100%;
            color: #fff;
            font-size: 40 upx;
        // margin-bottom: 10upx;
        // margin-left: 10upx;
            margin: 8 upx 0;
        }

        .integral {
            display: flex;
            align-items: center;
            padding: 0 8 upx;
        // height: 40upx;
            color: #fff;
            background-color: rgba(0, 0, 0, 0.1);
            border-radius: 20 upx;
            font-size: 24 upx;
        // margin-bottom: 10upx;
            margin: 8 upx 0;
        }
        }
        }

        .setting {
            flex-shrink: 0;
        // width: 6vw;
        // height: 6vw;

        image {
            width: 100%;
            height: 100%
        }
        }
        }

        .hover {
            background-color: #eee
        }

        .orders {
            background-color: #ff6364;
            width: 100%;
            height: 11vw;
            padding: 0 4%;
            margin-bottom: calc(11vw + 40upx);
            display: flex;
            align-items: flex-start;
            border-radius: 0 0 100% 100%;
            margin-top: -1upx;

        .box {
            width: 100%;
            padding: 0 1%;
            height: 22vw;
            background-color: #fefefe;
            border-radius: 24 upx;
            box-shadow: 0 0 20 upx rgba(0, 0, 0, 0.15);
            margin-bottom: 40 upx;
            display: flex;
            align-items: center;
            justify-content: center;

        .label {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-flow: wrap;
            width: 100%;
            height: 16vw;
            color: #666666;
            font-size: 26 upx;

        .icon {
            position: relative;
            width: 7vw;
            height: 7vw;
            margin: 0 1vw;

        .badge {
            position: absolute;
            width: 4vw;
            height: 4vw;
            background-color: #ec6d2c;
            top: -1vw;
            right: -1vw;
            border-radius: 100%;
            font-size: 20 upx;
            color: #fff;
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 10;
        }

        image {
            width: 7vw;
            height: 7vw;
            z-index: 9;
        }
        }
        }
        }
        }

        .list {
            width: 100%;
            border-bottom: solid 26 upx #f1f1f1;
            display: flex;
            flex-wrap: wrap;

        .li {
            width: 100%;
            height: 100 upx;
            padding: 0 4%;
            border-bottom: solid 1 upx #e7e7e7;
            display: flex;
            align-items: center;
            background: #FFFFFF;
            font-size: 33 upx;
            flex-wrap: wrap;

        &.noborder {
             border-bottom: 0
         }

        .icon {
            flex-shrink: 0;
            width: 50 upx;
            height: 50 upx;

        image {
            width: 50 upx;
            height: 50 upx
        }
        }

        .text {
            padding-left: 20 upx;
            width: 100%;
            color: #666
        }

        .to {
            flex-shrink: 0;
            width: 40 upx;
            height: 40 upx
        }
        }
        }
</style>
