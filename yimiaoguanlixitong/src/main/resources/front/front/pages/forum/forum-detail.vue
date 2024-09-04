<template>
	<view>
		<view class="title">
			{{forum.forumName}}
		</view>
        <view class="username">
			<text v-if="forum.yonghuId != null">
				 发布人：{{forum.yonghuName}}（用户）
			</text>
            <text v-if="forum.usersId!= null">
                发布人：管理员
            </text>
        </view>
		<view class="content">
			<rich-text :nodes="forum.forumContent"></rich-text>
		</view>
		<view class="uni-article__comment">
			<view class="uni-article__comment-box" v-for="comment in commentData" :key="comment.id" >
				<view class="article-meta">
					<view class="article-header-image">
                        <image v-if="comment.yonghuId != null" class="image" :src="baseUrl+comment.yonghuPhoto" mode="widthFix"></image>
						<image v-if="comment.usersId != null" class="image" src="/static/center/face.jpeg" mode="widthFix"></image>

					</view>
					<view class="article-header-content">
						<view class="article-header-title">
							<text v-if="comment.yonghuId != null">{{comment.yonghuName}}</text>
							<text v-if="comment.usersId != null">管理员</text>
						</view>
						<view class="article-header-info"> {{comment.insertTime||''}}</view>
					</view>
				</view>
				<view class="uni-article__comment-centent">
					<user-reply style="display: none;" v-if="commentData" :digest="commentData"></user-reply>
					{{comment.forumContent}}
				</view>
			</view>
			
			<view class="bottom-content">
				<button @tap="onChatTap" class="bg-red margin-tb-sm">评论</button>
			</view>
			
			<uni-popup class="popup-content" ref="popup" type="center">
				<form class="popup-form">
			
					<view class="uni-flex uni-column" style="background-color: #FFFFFF;border-radius: 10rpx;width: 500rpx;">
						<view class="flex-item flex-item-V" style="text-align: center;font-size: 40upx;font-weight: bold;margin: 20upx;">评论内容</view>
						<view class="flex-item flex-item-V">
							<textarea style="width: 400rpx;margin: 10rpx 0 10rpx 50rpx;border-style:dotted;border-width:1rpx;border-radius: 10rpx" v-model="forumContent" placeholder="评论内容"></textarea>
						</view>
						<view class="flex-item flex-item-V" style="text-align: right;background: #FFFFFF;">
							<button @tap="onCloseWinTap" style="margin: 0 0 10rpx 50rpx;" class="cu-btn bg-cyan">取消</button>
							<button @tap="onFinishTap" style="float: right;margin: 0 50rpx 10rpx 0;" class="cu-btn bg-red">确认</button>
						</view>
					</view>
				</form>
			</uni-popup>
			
			
		</view>
	</view>
</template>
<script>
	import userReply from '@/components/forum-reply/forum-reply.vue'
	export default {
		components: {
			userReply
		},
		data() {
			return {
				commentData: [],
				forum: {},
				user: {},
				forumPhoto:null,
				forumContent:null,
				yonghuId:null,
				id: '',
			}
		},
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
		async onLoad(options) {
			this.id = options.id;
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let user = await this.$api.session(table);
			this.user = user.data;
			 
			let res = await this.$api.info('forum',options.id)
            res.data.forumContent = res.data.forumContent.replace("<img","<img  class=\"daxiao\"").replace("src=\"upload/","src=\""+this.$base.url+"upload/");

            this.forum = res.data;
			let params = {
				superIds: this.forum.id,
				forumStateTypes: 2,
			}
			let commentDataList = await this.$api.list('forum',params)
			this.commentData = commentDataList.data.list;
		},
		async onShow() {
		},
		methods: {
			onChatTap () {
				this.$refs.popup.open()
				this.forumContent=null
			},
			// 完成审核
			async onFinishTap() {
				let _this = this
				if (_this.forumContent==null || _this.forumContent == "") {
					_this.$utils.msg('请填写评论内容');
					return
				}
				let params = {
					superIds: _this.forum.id,
					forumStateTypes: 2,
					yonghuId:_this.user.id,
					forumContent:_this.forumContent,
				}
				await _this.$api.save('forum', params);
				_this.$utils.msg('评论成功');
				_this.$refs.popup.close();
				uni.redirectTo({
				    url: '/pages/forum/forum-detail?id='+_this.forum.id
				});
			},
			// 关闭窗口
			onCloseWinTap() {
				this.$refs.popup.close()
			},
		}
	}
</script>

<style lang="scss">
    .daxiao {
        width: 600rpx;
    }
	page {
		background: #FFFFFF;
	}

	.title {
		text-align: center;
		font-size: 40upx;
		font-weight: bold;
		margin: 20upx;
	}
	
	.username{
		text-align: center;
		font-size: 20upx;
		font-weight: bold;
		color: #888888;
		margin: 20upx;
	}
	
	.content {
		margin: 40upx;
		font-size: 30upx;
		line-height: 50upx;
		letter-spacing: 5upx;
	}

	.article-meta {
		padding: 20rpx 20rpx;
		display: flex;
		align-items: center;
		flex-direction: row;
		justify-content: flex-start;
		color: gray;
	}

	.article-header-image {
		width: 60rpx;
		height: 60rpx;
		flex-shrink: 0;
		/* border-radius: 50%; */
		overflow: hidden;
	}

	.article-header-image .image {
		width: 100%;
		height: 100%;
	}

	.article-header-content {
		display: flex;
		flex-direction: column;
		justify-content: center;
		padding-left: 10px;
		font-size: 24rpx;
		line-height: 1;
		width: 100%;
		/* height: 60rpx; */
	}

	.article-header-title {
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 26rpx;
		line-height: 2;
		color: #000;
	}

	.article-header-info {
		font-size: 24rpx;
		line-height: 1;
	}

	.article-top {
		display: flex;
		align-items: flex-end;
		font-size: 26rpx;
		line-height: 1;
		color: #999;
	}

	.article-top .image {
		width: 30rpx;
		margin-left: 5rpx;
	}

	.uni-article__comment {
		margin-bottom: 160upx;
	}

	.uni-article__comment-title {
		width: 100%;
		line-height: 3;
		font-size: 28rpx;
		color: #666;
		background: #f5f5f5;
		padding-left: 20rpx;
	}

	.uni-article__comment-centent {
		border-bottom: 1px #eee solid;
		padding: 0 20rpx;
		padding-left: 95rpx;
		padding-bottom: 20rpx;
		font-size: 14px;
		color: #333;
	}

	.btn-replay {
		color: red;
		float: right;
	}
	
	.bottom-content{
		position: fixed;
		bottom: 0;
		padding: 20upx;
		width: 100%;
	}
</style>
