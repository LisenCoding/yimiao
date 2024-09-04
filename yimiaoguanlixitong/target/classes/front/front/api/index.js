import http from './http'
import base from './base'

/**
 * 自定义问号传参
 */
export const requestCondition = (tableName, functionName,value) => {
	var params = null
	if(value != null && value.length>0){
		value.forEach(function(item,index){
			if(index == 0){
				params = "?"+item.key+"="+item.val
			}else{
				params += "&"+item.key+"="+item.val
			}
		})
	}
	if(params == null || params == ""){
		return http.request({
			url: `${tableName}/${functionName}`,
			method: 'GET'
		});
	}else{
		return http.request({
			url: `${tableName}/${functionName}${params}`,
			method: 'GET'
		});
	}
}

/**
 * 自定义问号传参和数据集合
 */
export const requestConditionDataGet = (tableName, functionName,value,data) => {
	var params = null
	if(value != null && value.length>0){
		value.forEach(function(item,index){
			if(index == 0){
				params = "?"+item.key+"="+item.val
			}else{
				params += "&"+item.key+"="+item.val
			}
		})
	}
	if(params == null || params == ""){
		return http.request({
			url: `${tableName}/${functionName}`,
			method: 'GET',
			data
		});
	}else{
		return http.request({
			url: `${tableName}/${functionName}${params}`,
			method: 'GET',
			data
		});
	}
}
/**
 * 自定义问号传参和数据集合
 */
export const requestConditionDataPost = (tableName, functionName,value,data) => {
	var params = null
	if(value != null && value.length>0){
		value.forEach(function(item,index){
			if(index == 0){
				params = "?"+item.key+"="+item.val
			}else{
				params += "&"+item.key+"="+item.val
			}
		})
	}
	if(params == null || params == ""){
		return http.request({
			url: `${tableName}/${functionName}`,
			method: 'POST',
			data
		});
	}else{
		return http.request({
			url: `${tableName}/${functionName}${params}`,
			method: 'POST',
			data
		});
	}
}
/* 
	自定义map传参方法
 */
export const requestMap = (tableName, functionName, data) => {
	return http.request({
		url: `${tableName}/${functionName}`,
		method: 'GET',
		data
	});
}
/* 
 queryScore
	评分查询
 */
export const queryScore = (data) => {
	return http.request({
		url: `queryScore`,
		method: 'GET',
		data
	});
}
/**
 * 登陆校验
 */
export const auth = () => {
	let token = uni.getStorageSync("token");
	if (!uni.getStorageSync("token")) {
		uni.navigateTo({
			url: '../login/login'
		})
		return
	}
}
/**
 * 登陆
 */
export const login = (tableName = 'defaultuser', data) => {
	return http.request({
		url: `${tableName}/login`,
		method: 'GET',
		data
	})
}
/**
 * 注册
 */
export const register = (tableName = 'defaultuser', data) => {
	return http.request({
		url: `${tableName}/register`,
		method: 'POST',
		data
	})
}
/**
 * 重置密码
 */
export const resetPass = (tableName = 'defaultuser', username) => {
	let data = {
		username: username
	}
	return http.request({
		url: `${tableName}/resetPass`,
		method: 'GET',
		data
	})
}
/**
 * 获取登陆用户信息
 */
export const session = (tableName = 'defaultuser') => {
	return http.request({
		url: `${tableName}/session`,
		method: 'GET'
	})
}
/**
 * 查询 page
 */
export const page = (tableName, data) => {
	return http.request({
		url: `${tableName}/page`,
		method: 'GET',
		data
	});
}
/**
 * 查询 list
 */
export const list = (tableName, data) => {
	return http.request({
		url: `${tableName}/list`,
		method: 'GET',
		data
	});
}
/**
 * 保存 add
 */
export const add = (tableName, data) => {
	return http.request({
		url: `${tableName}/add`,
		method: 'POST',
		data
	});
}
// 保存 save
export const save = (tableName, data) => {
	return http.request({
		url: `${tableName}/save`,
		method: 'POST',
		data
	});
}
/**
 * 更新 update
 */
export const update = (tableName, data) => {
	return http.request({
		url: `${tableName}/update`,
		method: 'POST',
		data
	});
}
/**
 * 删除 del
 */ 
export const del = (tableName, data) => {
	return http.request({
		url: `${tableName}/delete`,
		method: 'POST',
		data
	});
}
/**
 * 单条信息查询 info
 */
export const info = (tableName, id) => {
	return http.request({
		url: `${tableName}/info/${id}`,
		method: 'GET'
	});
}
/**
 * 单条信息查询 detail
 */
export const detail = (tableName, id) => {
	return http.request({
		url: `${tableName}/detail/${id}`,
		method: 'GET'
	});
}
/**
 * 智能推荐
 */
export const recommend = (tableName, page = 1, limit = 10) => {
	let data = {
		page: page,
		limit: limit
	}
	return http.request({
		url: `${tableName}/autoSort?limit=5`,
		method: 'get',
		data,
	});
}
/**
 * 上传
 */
export const upload = (callback) => {
	uni.chooseImage({
		count: 4, //默认9
		sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
		sourceType: ['album'], //从相册选择
		success: (res) => {
			uni.uploadFile({
				url: `${base.url}file/upload`, //仅为示例，非真实的接口地址
				filePath: res.tempFilePaths[0],
				name: 'file',
				header: {
					'Token': uni.getStorageSync("token")
				},
				success: (uploadFileRes) => {
					// console.log(uploadFileRes)
					let result = JSON.parse(uploadFileRes.data);
					if (result.code == 0) {
						callback(result);
					} else {
						uni.showToast({
							title: result.msg,
							icon: 'none',
							duration: 2000
						});
					}
				}
			});
		}
	});
}
export const uploadMedia = (callback) => {
	uni.chooseVideo({
		count: 1,
		sourceType: ['camera', 'album'],
		success: function(res) {
			console.log(res)
			uni.uploadFile({
				url: `${base.url}file/upload`, //仅为示例，非真实的接口地址
				filePath: res.tempFilePath,
				name: 'file',
				header: {
					'Token': uni.getStorageSync("token")
				},
				success: (uploadFileRes) => {
					// console.log(uploadFileRes)
					let result = JSON.parse(uploadFileRes.data);
					if (result.code == 0) {
						callback(result);
					} else {
						uni.showToast({
							title: result.msg,
							icon: 'none',
							duration: 2000
						});
					}
				}
			});
		}
	});
}
export default {
	requestCondition,//自定义条件查
	requestConditionDataPost,//自定义条件查和集合
	requestConditionDataGet,//自定义条件查和集合
	requestMap,//自定义条件为map
	login, // 登陆
	register, //注册
	resetPass, // 重置
	auth, // 校验
	session, // 个人信息
	list, // 查询
	page, // 查询 page
	add, // 添加
	update, // 修改
	del, // 删除
	info, // 单条信息查询,
	recommend, // 智能推荐
	save, // 保存
	upload, // 上传
	queryScore,//评分查询
}
